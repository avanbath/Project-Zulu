package my.custom.area;

import java.util.List;
import java.util.ArrayList;


//no exceptions handled in current version 1.0
public class NHPIDataTable implements DataTable {
	//fields
	String location;
	String startDate;
	String endDate;
	List<Integer> table;
	
	//constructor
	public NHPIDataTable(String l, String s, String e) {
		this.location = l;
		this.startDate = s;
		this.endDate = e;
		this.table = new ArrayList<Integer>();
	}
	
	//setters and getters
	@Override
	public String getLocation() {
		return this.location;
	}

	@Override
	public String getStart() {
		return this.startDate;
	}

	@Override
	public String getEnd() {
		return this.endDate;
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

}
