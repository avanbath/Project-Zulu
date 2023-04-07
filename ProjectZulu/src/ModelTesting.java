import java.util.List;
import java.util.ArrayList;
public class ModelTesting {
	public static void main(String args[]) {
		
		TableManager tableMan = new TableManager();
		DatabaseAdapter dA = new DatabaseAdapter();
		tableMan.setAdapter(dA);
		
		
		tableMan.addRegion("Manitoba");
		tableMan.addRegion("Ontario");
		
		tableMan.addTimeSeries("2000-01", "2001-01");
		tableMan.addTimeSeries("2005-01", "2010-01");
		
		List<DataTable> d = tableMan.getTables();
        
    
	}
}
