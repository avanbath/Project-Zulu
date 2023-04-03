package my.custom.area;

import java.util.List;

public interface TableRetriever {
	void addRegion(String location);
	void addTimeSeries(String date1, String date2);
	List<DataTable> getTables();
	void reset();
}
