package my.custom.area;

public interface OpFacadeInter {
	void addRegion(String location, String date1, String date2);
	void resetDataTables();
	void setTableManager(TableManager t);
	String LaunchStatTest();
}
