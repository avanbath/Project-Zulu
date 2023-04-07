// BarGraphCreator - Latest Version

import java.awt.Dimension;
import java.util.Iterator;
import java.util.List;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.*;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.LayeredBarRenderer;

public class BarGraphCreator implements GraphCreator{
	public BarGraphCreator(){
    }
	
	private DefaultCategoryDataset createDataset(List<DataTable> l) {
		DefaultCategoryDataset dSet = new DefaultCategoryDataset();
		// Create an iterator for the list of DataTables
		Iterator<DataTable> tableIterator = l.iterator();
		
		// While each individual table has not been added...
		while (tableIterator.hasNext()) {
			// Go to the closest unused table
			DataTable dataTable = tableIterator.next();
			// Create an iterator for the table's NHPI value double list
			Iterator<Double> doubleIterator = dataTable.getTable().iterator();
			
			boolean first = true;
			double monthCount = 0;
			int yearCount = 0;
			
			while (doubleIterator.hasNext()) {
				String date = "";
				
				if (monthCount == 13 || first) {
					monthCount = 0;
					first = false;
					date = (dataTable.getStartYear() + yearCount)+(monthCount/12) + "";
					dSet.addValue(doubleIterator.next(), dataTable.getLocation(), date);
					
					yearCount++;
				}
				
				else {
					doubleIterator.next();
				}
				
				monthCount++;
			}
		}
		
		return dSet;
	}
	
	public ChartPanel create(List<DataTable> t) {
		String title = "NHPI for " + t.get(0).getStartYear() + "-" + t.get(0).getEndYear();
		CategoryAxis cAxis = new CategoryAxis("Year");
		ValueAxis vAxis = new NumberAxis("Years");
		CategoryPlot cPlot = new CategoryPlot(createDataset(t), cAxis, vAxis, new LayeredBarRenderer());
		
		cPlot.setOrientation(PlotOrientation.VERTICAL);
		
		JFreeChart barChart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, cPlot, true);
		
		LayeredBarRenderer render = (LayeredBarRenderer) cPlot.getRenderer();
		render.setSeriesBarWidth(0, 1.0);
        render.setSeriesBarWidth(1, 0.7);
        render.setSeriesBarWidth(2, 0.5);
        render.setItemMargin(0.01);

        CategoryAxis domain = cPlot.getDomainAxis();
        domain.setCategoryMargin(0.25);
        domain.setUpperMargin(0.05);
        domain.setLowerMargin(0.05);
        
        ChartPanel panel = new ChartPanel(barChart);
        panel.setSize(new Dimension(800, 367));
        
        // Return the ChartPanel so that it sends the graph to the UI
        return panel;
	}
}