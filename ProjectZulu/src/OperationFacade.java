import java.util.List;
import java.util.ArrayList;

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
	}

	@Override
	public void setMLForecast() {
		// TODO Auto-generated method stub
		this.o = new MLForecast();
	}

	@Override
	public List<String> launchOperation(List<DataTable> l) throws Exception {
		// TODO Auto-generated method stub
	
		List<String> results = new ArrayList<String>();
		Iterator<DataTable> i = l.iterator();
		List<DataTable> sameSeries = new ArrayList<DataTable>();
		sameSeries.add((DataTable) i.next());
		while (i.hasNext()) {
			DataTable current = (DataTable) i.next();
			if (sameSeries.get(sameSeries.size()-1).checkSameSeries(current)){
				sameSeries.add(current);
			}
			else {
				this.o.provideTables(sameSeries);
				results.add(this.o.execute());
				this.o.clearTables();
				sameSeries = new ArrayList<DataTable>();
				sameSeries.add(current);
			}
		}
		this.o.provideTables(sameSeries);
		results.add(this.o.execute());
		this.o.clearTables();
		
		
		return results;
		
	}

	
	

}
