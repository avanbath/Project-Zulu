// DatabaseAdapter - Latest Version

import java.sql.*;

public class DatabaseAdapter implements Adapter{
	String startMonth; 
	String endMonth; 
	int startYear; 
	int endYear; 
	String region1;
	String region2;
	
	public DatabaseAdapter() {
	}
	
	// Connection to the database to get a filled data table with supported queries
	@Override
	public DataTable getFilledDataTable(String date1, String date2, String location) {
		// REFACTOR THIS CODE SMELL (RENAME VARIABLE)
		String startYear = date1.substring(0, 4);
		String startMonth = date1.substring(5, 6);
		String endYear = date2.substring(0, 4);
		String endMonth = date2.substring(5, 6);
		
		DataTable d = new NHPIDataTable(location, Integer.parseInt(startYear), Integer.parseInt(endYear), Integer.parseInt(startMonth), Integer.parseInt(endMonth));
		
		try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database","root","root");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM database.location WHERE location = ? AND LENGTH(location) = LENGTH(?) AND ((year = ? AND month >= ?) OR (year > ? AND year < ?) OR (year = ? AND month <= ?)) ORDER BY location, year, month");
            
            statement.setString(1, location);
            statement.setString(2, location);
            statement.setInt(3, Integer.parseInt(startYear));
            statement.setString(4, startMonth);
            statement.setInt(5, Integer.parseInt(startYear));
            statement.setInt(6, Integer.parseInt(endYear));
            statement.setInt(7, Integer.parseInt(endYear));
            statement.setString(8, endMonth);
            
            ResultSet resultSet = statement.executeQuery();
            
            int count = 1;
            
            while (resultSet.next()) { 
            	d.addValue(resultSet.getDouble(4));
            	
            	count++;
          }
        }
		
		catch (Exception ex) {
            System.out.println(ex);
        }
		
		return d;
	}
}