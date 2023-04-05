import java.util.List;

public interface OpFacadeInter {
	void setStatTest();
	void setCompareNHPI();
	void setMLForecast();
	List<String> launchOperation(List<DataTable> l) throws Exception;
}