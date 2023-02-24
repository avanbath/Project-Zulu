import java.sql.*;

public class DataManager {
    public static void main(String args[]){  
		try{  
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection(  
		"jdbc:mysql://localhost:3306/NHPIDatabase","root","root");  
		
		Statement stmt=con.createStatement();  
		ResultSet rs=stmt.executeQuery("select * from location");  
		while(rs.next())  
		System.out.println(rs.getString(1));  
		con.close();  
		}catch(Exception e){ System.out.println(e);}  
	}  
}