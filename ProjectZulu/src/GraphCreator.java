// GraphCreator - Latest Version

import java.util.List;
import org.jfree.chart.*;

public interface GraphCreator {
	default ChartPanel create(List<DataTable> t) {
		return null;
	}
}