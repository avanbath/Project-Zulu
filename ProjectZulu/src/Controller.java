// Controller - Latest Version

import java.util.List;

public interface Controller {
	void addRegion(String location);
	void addTimeSeries(String date1, String date2);
	void resetData();
	void setStatTest();
	void setCompareNHPI();
	void setMLForecast();
	List<String> execute();
}