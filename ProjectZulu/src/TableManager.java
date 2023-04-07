
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
		System.out.println("REGION ADDED TO TABLE MANAGER " + location);
	}

	@Override
	public void addTimeSeries(String date1, String date2) {
		this.dates.add(date1);
		this.dates.add(date2);
		System.out.println("TIME SERIES ADDED TO TABLE MANAGER " + date1 + ", " + date2);
	}

	@Override
	public List<DataTable> getTables() {
		// TODO Auto-generated method stub
		//for loop should handle getting time series and add seperately
		for (int x=0; x<this.dates.size(); x=x+2) {
			for (int y=0; y<this.locations.size(); y++) {
				System.out.println("REQUESTING TABLE FOR " + this.dates.get(x) + "," + this.dates.get(x+1) + ", " + this.locations.get(y));
				this.listOfTables.add(this.requestDataTable(this.dates.get(x), this.dates.get(x+1), this.locations.get(y)));
			}
		}
		this.notifySubscribers();
		System.out.println("The number of tables should be (Table Manager) " + this.listOfTables.size());
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


