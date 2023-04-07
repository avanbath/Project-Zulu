// TableRetriever - Latest Version

import java.util.List;

public interface TableRetriever {
	void addRegion(String location);
	void addTimeSeries(String date1, String date2);
	List<DataTable> getTables();
	void reset();
}