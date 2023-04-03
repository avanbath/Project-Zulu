import java.sql.*;
import org.jfree.chart.*;
import org.jfree.data.category.DefaultCategoryDataset;

public class DataManager {
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
            
            // connection.close();
            return resultSet;
        }
    	
    	catch (Exception ex) {
    		System.out.println(ex);
        }
    	
		return null;
    }
}