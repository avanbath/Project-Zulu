

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class OperationFacade implements OpFacadeInter{

	Operation o;
	
	
	@Override
	public void setStatTest() {
		this.o = new StatTest();
	}

	@Override
	public void setCompareNHPI() {
		// TODO Auto-generated method stub
		this.o = new CompareNHPI();
		System.out.println("Set operation to compare");
	}

	@Override
	public void setMLForecast() {
		// TODO Auto-generated method stub
		this.o = new MLForecast();
	}

	@Override
	public List<String> launchOperation(List<DataTable> l) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Now in launch operation");
		List<String> results = new ArrayList<String>();
		Iterator<DataTable> i = l.iterator();
		List<DataTable> sameSeries = new ArrayList<DataTable>();
		sameSeries.add((DataTable) i.next());
		System.out.println("The first entry in the same series is for " + sameSeries.get(0).getLocation() + ", end date: " + sameSeries.get(0).getEndYear());
		while (i.hasNext()) {
			DataTable current = (DataTable) i.next();
			if (sameSeries.get(sameSeries.size()-1).checkSameSeries(current)){
				sameSeries.add(current);
				System.out.println("Just added " + current.getLocation());
			}
			else {
				System.out.println("Providing tables to " + this.o.toString());
				this.o.provideTables(sameSeries);
				System.out.println("About to execute the operation");
				this.o.execute();
				System.out.println("finished the execute");
				this.o.clearTables();
				sameSeries = new ArrayList<DataTable>();
				sameSeries.add(current);
				System.out.println("The newest entry for op is " + current.getLocation() + ", end year " + current.getEndYear());
			}
		}
		this.o.provideTables(sameSeries);
		results.add(this.o.execute());
		this.o.clearTables();
		
		System.out.println("The number of results we should have is " + results.size());
		return results;
		
	}

	
	
}
