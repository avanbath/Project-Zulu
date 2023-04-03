public interface NHPIPublisher {
	public void unSubscribe(); 
	public void requestDataTable();
	public void notifySubscribers();
	public void addSubscriber();
}