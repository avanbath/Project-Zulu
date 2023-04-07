// NHPIDataTable - Latest Version

import java.util.List;
import java.util.ArrayList;

public class NHPIDataTable implements DataTable {
	// Initialize the fields
	String location;
	int startYear;
	int endYear;
	int startMonth;
	int endMonth;
	List<Double> table;
	
	// Constructor
	public NHPIDataTable(String l, int sY, int eY, int sM, int eM) {
		this.location = l;
		this.startYear = sY;
		this.endYear = eY;
		this.startMonth = sM;
		this.endMonth = eM;
		this.table = new ArrayList<Double>();
	}
	
	// Setters and getters
	@Override
	public String getLocation() {
		return this.location;
	}

	@Override
	public int getStartYear() {
		return this.startYear;
	}

	@Override
	public int getEndYear() {
		return this.endYear;
	}
	
	@Override
	public void addValue(double i) {
		this.table.add(i);
	}

	@Override
	public List<Double> getTable() {
		return this.table;
	}

	@Override
	public int getStartMonth() {
		return this.startMonth;
	}

	@Override
	public int getEndMonth() {
		return this.endMonth;
	}

	@Override
	public boolean checkSameSeries(DataTable d) {
		if (this.startYear == d.getStartYear() && this.endYear == d.getEndYear() && this.startMonth == d.getStartMonth() && this.endMonth == d.getEndMonth()) {
			return true;
		}
		
		return false;
	}
}