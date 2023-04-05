import java.util.List;
import org.jfree.chart.*;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.SlidingCategoryDataset;
import java.util.Iterator;
import org.jfree.data.xy.*;

public class LineGraphCreator implements GraphCreator {
	public LineGraphCreator(){
    }
	
	private XYDataset createDataset(List<DataTable> l) {
		// Create the XY series object
		XYSeriesCollection dSet = new XYSeriesCollection();
		// Create the iterator for the list of DataTables
		Iterator<DataTable> tableIterator = l.iterator();
		
		// While each individual table has not been added
		while (tableIterator.hasNext()) {
			// Go to the closest unused table
			DataTable dataTable = tableIterator.next();
			// Create an iterator for the table's NHPI value double list
			Iterator<Double> doubleIterator = dataTable.getTable().iterator();
			// Create a new series which will be added to the collection
			XYSeries series = new XYSeries(dataTable.getLocation());
			
			int count = 0;
			
			while (doubleIterator.hasNext()) {
				series.add(dataTable.getStartYear() + count ,doubleIterator.next());
				count++;
			}
			
			dSet.addSeries(series);
		}
		
		return dSet;
	}
	
	public ChartPanel create(List<DataTable> t) {
		String title = "NHPI for " + t.get(0).getStartYear() + "-" + t.get(0).getEndYear();
		JFreeChart lChart = ChartFactory.createXYLineChart(title, "Years", "NHPI", createDataset(t), PlotOrientation.VERTICAL, true, true, false);
        ChartPanel chartPanel = new ChartPanel( lChart );
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        
        // Return the chartPanel to the visual updater that sends it to the UI
        return chartPanel;
	}
}