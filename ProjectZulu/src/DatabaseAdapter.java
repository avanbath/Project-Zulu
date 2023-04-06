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
	
	public DatabaseAdapter(String startMonth, String endMonth, int startYear, int endYear, String region1, String region2) {
		this.startMonth = startMonth;
		this.endMonth = endMonth;
		this.startYear = startYear;
		this.endYear = endYear;
		this.region1 = region1;
		this.region2 = region2;
	}
	
	protected ResultSet callDB(String startMonth, String endMonth, int startYear, int endYear, String region) {
    	try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database","root","root");
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
            return resultSet;
        }
    	
    	catch (Exception ex) {
            System.out.println(ex);
        }
    	
		return null;
    }

	@Override
	public DataTable getFilledDataTable(String date1, String date2, String location) {
		String sy = date1.substring(0, 4);
		String sm = date1.substring(5, 6);
		String ey = date2.substring(0, 4);
		String em = date2.substring(5, 6);
		
		DataTable d = new NHPIDataTable(location, Integer.parseInt(sy), Integer.parseInt(ey), Integer.parseInt(sm), Integer.parseInt(em));
		
		try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database","root","root");
            
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM database.location WHERE location = ? AND LENGTH(location) = LENGTH(?) AND ((year = ? AND month >= ?) OR (year > ? AND year < ?) OR (year = ? AND month <= ?)) ORDER BY location, year, month");
            
            statement.setString(1, location);
            statement.setString(2, location);
            statement.setInt(3, Integer.parseInt(sy));
            statement.setString(4, sm);
            statement.setInt(5, Integer.parseInt(sy));
            System.out.println("End year sent to database is " + ey);
            statement.setInt(6, Integer.parseInt(ey));
            statement.setInt(7, Integer.parseInt(ey));
            statement.setString(8, em);
            
            ResultSet resultSet = statement.executeQuery();
            int count = 1;
            
            // | YEAR | MONTH | LOCATION | NHPI |
            while (resultSet.next()) { 
            	d.addValue(resultSet.getDouble(4));
            	System.out.println("Number of nhpi added to new dataTable" + count);
            	count++;
          }
            //System.out.println("(DATABASE OUTPUT) " + "Region: " + d.getLocation() + ", first NHPI value: " + d.getTable().get(0) + ", second NHPI value: " + d.getTable().get(1));
        }
		
		catch (Exception ex) {
            System.out.println(ex);
        }
		
		return d;
	}
}