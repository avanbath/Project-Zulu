import java.sql.*;

public class DataManager {
    public static void main(String args[]){
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://sql9.freesqldatabase.com:3306/sql9600659","sql9600659","7ypun97xyb");

			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery("select * from location");
			// Add a new line for each query:
			ResultSet AllNovaScotiaData = statement.executeQuery("select columnIndex:3 = 'Nova Scotia' from location");
			ResultSet AllOntarioData = statement.executeQuery("select columnIndex:3 = 'Ontario' from location");
			// etc etc

			while(resultset.next()) {
				System.out.println(resultset.getString(1) + ", " + resultset.getString(2) + ", " + resultset.getString(3) + ", " + resultset.getString(4));
			}

			connection.close();
		}

		catch (Exception e) {
			System.out.println(e);
		}
	}
}