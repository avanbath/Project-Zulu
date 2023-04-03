

import java.util.List;

import org.jfree.chart.*;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import java.util.Iterator;
import org.jfree.data.xy.*;


public class LineGraphCreator implements GraphCreator {
	
	
	public LineGraphCreator(){
    }
	/*
	private DefaultCategoryDataset createDataset(List<Double> t, int startDate){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Integer y = startDate;
        Iterator<Double> i = t.iterator();
        while (i.hasNext()) {
        	dataset.addValue((Double) i.next(), "NHPI", y.toString());
        	y++;
        }
        return dataset;
    }
    */

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
	
	/*
	@Override
	public ChartPanel create(List<Double> t, int d1, String location) {
		// TODO Auto-generated method stub
		//LineGraph l = new LineGraph("NHPIapp");
        JFreeChart lChart = ChartFactory.createLineChart(location, "Years", "NHPI", createDataset(t, d1), PlotOrientation.VERTICAL, true, true, false);
        ChartPanel chartPanel = new ChartPanel( lChart );
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        return chartPanel;
	}
	*/
	public ChartPanel create(List<DataTable> t) {
        JFreeChart lChart = ChartFactory.createXYLineChart(title, "Years", "NHPI", createDataset(t), PlotOrientation.VERTICAL, true, true, false);
        ChartPanel chartPanel = new ChartPanel( lChart );
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        return chartPanel;
	}

	//return chartpanel to visual updater that then sends to the ui
	
	
	
	
	

}
