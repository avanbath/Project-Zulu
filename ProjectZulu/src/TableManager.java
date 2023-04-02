import java.util.List;

public class TableManager implements NHPIPublisher {
	int numOfTables;
	List<DataTable> l;
	
	protected void clearTables() {
		l.clear();
	}
	
	public void requestDataTable() {
		
	}
	
	public void notifySubscribers() {
		
	}

	@Override
	public void unSubscribe() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addSubscriber() {
		// TODO Auto-generated method stub
		
	}

}
