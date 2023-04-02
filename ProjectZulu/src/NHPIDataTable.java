package my.custom.area;

import java.util.List;
import java.util.ArrayList;


//no exceptions handled in current version 1.0
public class NHPIDataTable implements DataTable {
	//fields
	String location;
	int startYear;
	int endYear;
	int startMonth;
	int endMonth;
	List<Integer> table;
	
	//constructor
	public NHPIDataTable(String l, int sY, int eY, int sM, int eM) {
		this.location = l;
		this.startYear = sY;
		this.endYear = eY;
		this.startMonth = sM;
		this.endMonth = eM;
		this.table = new ArrayList<Integer>();
	}
	
	//setters and getters
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
	public void addValue(int i) {
		//add exception if table does not exist
		this.table.add(i);
	}

	@Override
	public List<Integer> getTable() {
		return this.table;
	}

	@Override
	public int getStartMonth() {
		// TODO Auto-generated method stub
		return this.startMonth;
	}

	@Override
	public int getEndMonth() {
		// TODO Auto-generated method stub
		return this.endMonth;
	}

}
