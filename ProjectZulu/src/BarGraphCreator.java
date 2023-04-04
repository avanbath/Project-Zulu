package my.custom.area;

import java.awt.Dimension;
import java.util.Iterator;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class BarGraphCreator implements GraphCreator{
	public BarGraphCreator(){
    }
	

	private XYDataset createDataset(List<DataTable> l) {
		//creating the XY series object
		XYSeriesCollection dSet = new XYSeriesCollection();
		//creating iterator for list of DataTables
		Iterator<DataTable> tableIterator = l.iterator();
		
		//while each individual table has not been added
		while (tableIterator.hasNext()) {
			//go to closest unused table
			DataTable dataTable = tableIterator.next();
			//create an iterator for the tables nhpi value double list
			Iterator<Double> doubleIterator = dataTable.getTable().iterator();
			//create a new series which will be added to the collection
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
		int difference = t.get(0).getEndYear() - t.get(0).getStartYear();
		System.out.println("Size is " + difference);
		String title = "NHPI for " + t.get(0).getStartYear() + "-" + t.get(0).getEndYear();
		JFreeChart bChart = ChartFactory.createXYBarChart(title, "Years", false, "NHPI", (IntervalXYDataset) createDataset(t));
        //JFreeChart lChart = ChartFactory.createXYLineChart(title, "Years", "NHPI", createDataset(t), PlotOrientation.VERTICAL, true, true, false);
        ChartPanel chartPanel = new ChartPanel( bChart );
       
        //chartPanel.setPreferredSize(new java.awt.Dimension(10 * (difference), 367));
        chartPanel.setSize(new Dimension(15 * difference, 367));
        return chartPanel;
	}
}
