import java.sql.*;
import org.jfree.chart.*;
import org.jfree.data.category.DefaultCategoryDataset;
public class DatabaseAdapter implements Adapter{
	String startMonth; 
	String endMonth; 
	int startYear; 
	int endYear; 
	String region;
	
	
	public DatabaseAdapter() {
		
	}
	
	public DatabaseAdapter(String startMonth, String endMonth, int startYear, int endYear, String region) {
		this.startMonth = startMonth;
		this.endMonth = endMonth;
		this.startYear = startYear;
		this.endYear = endYear;
		this.region = region;
	}
	
	protected ResultSet callDB(String startMonth, String endMonth, int startYear, int endYear, String region) {
    	try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database","root","Luanamade!");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM database.location WHERE location = ? AND LENGTH(location) = LENGTH(?) AND ((year = ? AND month >= ?) OR (year > ? AND year < ?) OR (year = ? AND month <= ?)) ORDER BY location, year, month");
            statement.setString(1, region);
            statement.setString(2, region);
            statement.setInt(3, startYear);
            statement.setString(4, startMonth);
            statement.setInt(5, startYear);
            statement.setInt(6, endYear);
            statement.setInt(7, endYear);
            statement.setString(8, endMonth);
            ResultSet resultSet = statement.executeQuery();
//            System.out.printf("| YEAR | MONTH | LOCATION | NHPI | %n");
            while (resultSet.next()) { 
            	String startDate = String.format("%-4s--%-2s", this.startMonth,this.startYear);
            	String endDate = String.format("%-4s--%-2s", this.endMonth,this.endYear);
            	getFilledDataTable(startDate, endDate, region);
  //          	System.out.printf("| %-4s | %-2s | %s | %5s %n", resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getDouble(4));
            }
            return resultSet;
        } catch (Exception ex) {
            System.out.println(ex);
        }
		return null;
    }

	@Override
	public DataTable getFilledDataTable(String date1, String date2, String location) {
		//make it so we seperate date1 yyyy-mm into two seperate ints
		//make it so we seperate date2 yyyy-mm into two seperate ints
		//example of the seperated years and months as ints after the seperation
		int sy = 2000;
		int ey = 2005;
		int sm = 1;
		int em = 1; //1 = january
		
		DataTable d = new NHPIDataTable(location, sy, ey, sm, em);
		
		
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database","root","Luanamade!");
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM database.location WHERE location = ? AND LENGTH(location) = LENGTH(?) AND ((year = ? AND month >= ?) OR (year > ? AND year < ?) OR (year = ? AND month <= ?)) ORDER BY location, year, month");
        statement.setString(1, region);
        statement.setString(2, region);
        statement.setInt(3, startYear);
        statement.setString(4, startMonth);
        statement.setInt(5, startYear);
        statement.setInt(6, endYear);
        statement.setInt(7, endYear);
        statement.setString(8, endMonth);
        ResultSet resultSet = statement.executeQuery();
        
        while (resultSet.next()) { 
        	//add the nhpi value from the start of the result set which should be the first year and first month
        	//to the last year and last month, we only need to know the nhpi value
        	d.addValue(0);
        	
        }
        
		
		
	}

}
