

import java.util.List;
import java.util.ArrayList;

public class ControllerIMPL implements Controller{
	TableRetriever tableManager;
	OpFacadeInter operations;
	
	public void setTableMan(TableRetriever t) {
		this.tableManager = t;
	}
	
	public void setOpFacade(OpFacadeInter o) {
		this.operations = o;
	}
	@Override
	public void addRegion(String location) {
		this.tableManager.addRegion(location);
		System.out.println("Just added the region: " + location);
	}

	@Override
	public void addTimeSeries(String date1, String date2) {
		this.tableManager.addTimeSeries(date1, date2);
		System.out.println("Just added the time series: " + date1 + ", " + date2);
	}

	@Override
	public void resetData() {
		this.tableManager.reset();
	}


	@Override
	public void setStatTest() {
		this.operations.setStatTest();
	}

	@Override
	public void setCompareNHPI() {
		this.operations.setCompareNHPI();
	}

	@Override
	public void setMLForecast() {
		this.operations.setMLForecast();
	}

	@Override
	public List<String> execute() {
		
		try {
			//this.tableManager.getTables();
			List<String> result1 = this.operations.launchOperation(this.tableManager.getTables());
			//List<String> result1 = new ArrayList<String>();
			//result1.add("This works if there is no operation");
			this.tableManager.reset();
			return result1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String errorString = "An error occured in the operation";
			List<String> result2 = new ArrayList<String>();
			result2.add(errorString);
			return result2;
		}
		
		
		
	}

}
