import java.util.List;
import org.jfree.chart.*;

public interface GraphCreator {
	final String title = "NHPIApplication";
	ChartPanel create(List<DataTable> t);
}
