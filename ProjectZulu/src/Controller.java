package my.custom.area;

public interface Controller {
	void addRegion(String location);
	void addTimeSeries(String date1, String date2);
	void resetDataTables();
	void resetTimeSeries();
	void setStatTest();
	void setCompareNHPI();
	void setMLForecast();
	List<String> execute();
}
