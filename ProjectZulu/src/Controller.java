package my.custom.area;

public interface Controller {
	void addRegion(String location, String date1, String date2);
	void addTimeSeries(String date1, String date2);
	void resetDataTables();
	void resetTimeSeries();
	void setStatTest();
	void setCompareNHPI();
	void setMLForecast();
	String execute();
}
