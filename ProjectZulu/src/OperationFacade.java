package my.custom.area;

import java.util.List;
import java.util.ArrayList;

public class OperationFacade implements OpFacadeInter{
	List<DataTable> l;
	TableManager t;
	
	
	
	@Override
	public void addRegion(String location, String date1, String date2) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void resetDataTables() {
		// TODO Auto-generated method stub
		this.l = new ArrayList<DataTable>();
	}


	@Override
	public void setTableManager(TableManager t) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String LaunchStatTest() {
		Operation sTest = new StatTest();
		sTest.provideTables(this.l);
		//this will provide all tables, even those with different time series
		//however we can have logic class in controller contact the stat test
		//to launch seperate instances and provide the time series in isolation
		//if necessary
		return null;
	}
	
	
	
	
	
}
