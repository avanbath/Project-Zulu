package my.custom.area;

import java.util.List;

public interface Operation {
	void provideTables(List<DataTable> l);
	String execute();
	void clearTables();
}
