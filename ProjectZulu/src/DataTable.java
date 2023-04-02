package my.custom.area;

import java.util.List;

public interface DataTable {
	String getLocation();
	int getStartYear();
	int getEndYear();
	int getStartMonth();
	int getEndMonth();
	List<Integer> getTable();
	void addValue(int i);	
}
