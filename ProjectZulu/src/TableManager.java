
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
//update to github
public class TableManager implements NHPIPublisher, TableRetriever {
	List<DataTable> listOfTables;
	List<TableManagerSubscriber> listOfSubs;
	List<String> locations;
	List<String> dates;
	int expectedTables;
	Adapter d;
	
	//constructor
	TableManager(){
		this.listOfTables = new ArrayList<DataTable>();
		this.listOfSubs = new ArrayList<TableManagerSubscriber>();
		this.dates = new ArrayList<String>();
		this.locations = new ArrayList<String>();
	}
	
	public void setAdapter(Adapter a) {
		this.d = a;
	}
	
	
	public DataTable requestDataTable(String date1, String date2, String location) {
		DataTable d = this.d.getFilledDataTable(date1, date2, location);
		return d;
	}

	
	
	//Publisher observer pattern related method
	@Override
	public void addSubscriber(TableManagerSubscriber t) {
		this.listOfSubs.add(t);
	}

	@Override
	public void unSubscribe(TableManagerSubscriber t) {
		this.listOfSubs.remove(t);
		
	}

	@Override
	public void notifySubscribers() {
		Iterator<TableManagerSubscriber> i = this.listOfSubs.iterator();
		while (i.hasNext()) {
			i.next().update(this.listOfTables);
		}
	}
	





	//Table retriever methods
	@Override
	public void addRegion(String location) {
		this.locations.add(location);
	}

	@Override
	public void addTimeSeries(String date1, String date2) {
		this.dates.add(date1);
		this.dates.add(date2);
	}

	@Override
	public List<DataTable> getTables() {
		// TODO Auto-generated method stub
		//for loop should handle getting time series and add seperately
		System.out.println("The number of time series is " + (this.dates.size()/2) + " raw value is " + this.dates.size());
		System.out.println("The number of locations is " + this.locations.size());
		for (int x=0; x<this.dates.size(); x=x+2) {
			System.out.println("The current value of x is " + x);
			for (int y=0; y<this.locations.size(); y++) {
				//System.out.println("Trying to get a table for " + this.locations.get(y) + " " + this.dates.get(x) + " " + this.dates.get(x+1));
				System.out.println("The current value of y is " + y);
				this.listOfTables.add(this.requestDataTable(this.dates.get(x), this.dates.get(x+1), this.locations.get(y)));
				System.out.println("The amount of dataTables made is! " + this.listOfTables.size());
				//System.out.println("Trying to get a table for " + this.locations.get(y) + " " + this.dates.get(x) + " " + this.dates.get(x+1));
			}
		}
		System.out.println("The amount of dataTables made is* " + this.listOfTables.size());
		this.notifySubscribers();
		return this.listOfTables;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		this.locations = new ArrayList<String>();
		this.dates = new ArrayList<String>();
		this.listOfTables = new ArrayList<DataTable>();
	}
	
	
	
	
}


