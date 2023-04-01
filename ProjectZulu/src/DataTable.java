package my.custom.area;

import java.util.List;

public interface DataTable {
	String getLocation();
	String getStart();
	String getEnd();
	List<Integer> getTable();
	void addValue(int i);	
}
