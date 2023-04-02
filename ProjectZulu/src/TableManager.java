package my.custom.area;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class TableManager implements NHPIPublisher {
	List<DataTable> listOfTables;
	List<TableManagerSubscriber> listOfSubs;
	int expectedTables;
	Adapter d;
	
	
	TableManager(Adapter a){
		this.d = a;
		this.listOfTables = new ArrayList<DataTable>();
		this.listOfSubs = new ArrayList<TableManagerSubscriber>();
	}

	@Override
	public DataTable requestDataTable(String date1, String date2, String location) {
		DataTable d = this.d.getFilledDataTable(date1, date2, location);
		listOfTables.add(d);
		if (listOfTables.size() == expectedTables) {
			notifySubscribers();
		}
		return d;
	}

	@Override
	public void addSubscriber(TableManagerSubscriber t) {
		// TODO Auto-generated method stub
		this.listOfSubs.add(t);
	}

	@Override
	public void unSubscribe(TableManagerSubscriber t) {
		// TODO Auto-generated method stub
		this.listOfSubs.remove(t);
		
	}

	@Override
	public void notifySubscribers() {
		// TODO Auto-generated method stub
		Iterator<TableManagerSubscriber> i = listOfSubs.iterator();
		while (i.hasNext()) {
			i.next().update(listOfTables);
		}
	}
	
	@Override
	public void setExpectedTables(int i) {
		// TODO Auto-generated method stub
		this.expectedTables = i;
	}
	
	
	
}
