import java.sql.*;
import org.jfree.chart.*;
import org.jfree.data.category.DefaultCategoryDataset;

public class DataManager {
    public static void fillDataSet(DefaultCategoryDataset dataset, String location){
        try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database","root","root");

			PreparedStatement statement = connection.prepareStatement("SELECT * from location");
            statement.setString(1, location); 
          
            ResultSet AllData = statement.executeQuery();

			while(AllData.next()) {
                System.out.println("Looping");
                dataset.addValue(AllData.getInt(4), "NHPI", AllData.getString(2));
            }

			//connection.close();
		}

		catch (Exception e) {
			System.out.println(e);
		}
	}
    
    public static void main(String args[]){
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database","root","root");

			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery("SELECT * from location");

			while(resultset.next()) {
				System.out.println(resultset.getString(1));
			}

			connection.close();
		}

		catch (Exception e) {
			System.out.println(e);
		}
	}
}