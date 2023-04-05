import java.util.List;
import org.jfree.chart.*;

public interface GraphCreator {
	ChartPanel create(List<DataTable> t);
}