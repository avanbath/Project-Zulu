

import java.util.List;

import org.jfree.chart.*;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.SlidingCategoryDataset;

import java.util.Iterator;
import org.jfree.data.xy.*;
//update to git hub


public class LineGraphCreator implements GraphCreator {
	
	
	public LineGraphCreator(){
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
			double monthCount = 0;
			int yearCount = 0;
			while (doubleIterator.hasNext()) {
				if (monthCount == 13) {
					monthCount = 0;
					yearCount++;
				}
				
				double date = (dataTable.getStartYear() + yearCount)+(monthCount/12);
						//dataTable.getStartYear() + count 
				series.add(date,doubleIterator.next());
				monthCount++;
				//System.out.println("Current year added is " + (dataTable.getStartYear() + count));
			}
			dSet.addSeries(series);
		}
		
		return dSet;
	}
	
	
	public ChartPanel create(List<DataTable> t) {
		String title = "NHPI for " + t.get(0).getStartYear() + "-" + t.get(0).getEndYear();
		JFreeChart lChart = ChartFactory.createXYLineChart(title, "Years", "NHPI", createDataset(t), PlotOrientation.VERTICAL, true, true, false);
        //JFreeChart lChart = ChartFactory.createXYLineChart(title, "Years", "NHPI", createDataset(t), PlotOrientation.VERTICAL, true, true, false);
        ChartPanel chartPanel = new ChartPanel( lChart );
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        return chartPanel;
	}

	//return chartpanel to visual updater that then sends to the ui
	
	
	
	
	

}
