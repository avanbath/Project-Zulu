import java.util.List;

public interface Operation {
	void provideTables(List<DataTable> l);
	void clearTables();
	
	String execute() throws Exception;
}