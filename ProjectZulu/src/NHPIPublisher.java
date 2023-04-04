public interface NHPIPublisher {
	void addSubscriber(TableManagerSubscriber t);
	void unSubscribe(TableManagerSubscriber t);
	void notifySubscribers();
}
