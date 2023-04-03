import java.util.List;

public interface DataTable {
	String getLocation();
	int getStartYear();
	int getEndYear();
	int getStartMonth();
	int getEndMonth();
	List<Double> getTable();
	void addValue(double i);	
}