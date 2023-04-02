package my.custom.area;
import java.util.List;
import org.jfree.chart.*;
public interface GraphCreator {
	final String title = "NHPIApplication";
	ChartPanel create(List<Double> t, int d1, String location);
	ChartPanel create(List<DataTable> t);
}


