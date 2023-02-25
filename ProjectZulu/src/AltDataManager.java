import java.sql.*;
import org.jfree.chart.*;
import org.jfree.data.category.DefaultCategoryDataset;

public class DataManager {

    public static void fillDataSet(DefaultCategoryDataset dataset, String location){
        try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://sql9.freesqldatabase.com:3306/sql9600659","sql9600659","7ypun97xyb");

			PreparedStatement statement = connection.prepareStatement("SELECT * from location WHERE location = ?");
            statement.setString(1, location);
            
          
            ResultSet AllNovaScotiaData = statement.executeQuery();
           

			while(AllNovaScotiaData.next()) {
                System.out.println("Looping");
                dataset.addValue(AllNovaScotiaData.getInt(4), "NHPI", AllNovaScotiaData.getString(2));
            }

			connection.close();
		}

		catch (Exception e) {
			System.out.println(e);
		}


	}
    
    public static void main(String args[]){
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://sql9.freesqldatabase.com:3306/sql9600659","sql9600659","7ypun97xyb");

			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery("select * from location");

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
