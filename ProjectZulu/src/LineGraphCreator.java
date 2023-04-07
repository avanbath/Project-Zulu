// LineGraphCreator - Latest Version

import java.util.List;
import org.jfree.chart.*;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import java.util.Iterator;
import org.jfree.data.xy.*;

public class LineGraphCreator implements GraphCreator {
	public LineGraphCreator(){
    }
	
	private XYDataset createDataset(List<DataTable> l) {
		// Create the XY series object
		XYSeriesCollection dSet = new XYSeriesCollection();
		// Create an iterator for the list of DataTables
		Iterator<DataTable> tableIterator = l.iterator();
		
		// While each individual table has not been added...
		while (tableIterator.hasNext()) {
			// Go to the closest unused table
			DataTable dataTable = tableIterator.next();
			// Create an iterator for the table's NHPI value double list
			Iterator<Double> doubleIterator = dataTable.getTable().iterator();
			
			// Create a new series which will be added to the collection
			XYSeries series = new XYSeries(dataTable.getLocation());
			
			double monthCount = 0;
			int yearCount = 0;
			
			while (doubleIterator.hasNext()) {
				if (monthCount == 13) {
					monthCount = 0;
					
					yearCount++;
				}
				
				double date = (dataTable.getStartYear() + yearCount)+(monthCount/12);
				
				series.add(date,doubleIterator.next());
				
				monthCount++;
			}
			dSet.addSeries(series);
		}
		
		return dSet;
	}
	
	public ChartPanel create(List<DataTable> t) {
		String title = "NHPI for " + t.get(0).getStartYear() + "-" + t.get(0).getEndYear();
		JFreeChart lChart = ChartFactory.createXYLineChart(title, "Years", "NHPI", createDataset(t), PlotOrientation.VERTICAL, true, true, false);
        
		ChartPanel panel = new ChartPanel( lChart );
        panel.setPreferredSize(new java.awt.Dimension(560, 367));
        
        // Return the ChartPanel so that it sends the graph to the UI
        return panel;
	}
}