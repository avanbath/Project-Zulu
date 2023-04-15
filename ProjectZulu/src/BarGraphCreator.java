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
	//takes a dataset and parses it into a Default CategoryDataset
	//so that the create method can make a proper bar graph
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
	//method to reduce the LOC of chartPanel
	//accepts a LayeredBarRenderer and sets its parameters for the create method
	private void configRenderer(LayeredBarRenderer lbr){
		lbr.setSeriesBarWidth(0, 1.0);
		lbr.setSeriesBarWidth(1, 0.7);
		lbr.setSeriesBarWidth(2, 0.5);
		lbr.setItemMargin(0.01);
	}
	//method to reduce the LOC of chartPanel
	//accepts a CategoryAxis and sets its parameters for the create method
	private void configDomain(CategoryAxis dom){
		dom.setCategoryMargin(0.25);
		dom.setUpperMargin(0.05);
		dom.setLowerMargin(0.05);
	}

	//Responsible for creating a graph for the given dataset
	@Override
	public ChartPanel create(List<DataTable> t) {
		String title = "NHPI for " + t.get(0).getStartYear() + "-" + t.get(0).getEndYear();
		CategoryAxis cAxis = new CategoryAxis("Year");
		ValueAxis vAxis = new NumberAxis("Years");
		CategoryPlot cPlot = new CategoryPlot(createDataset(t), cAxis, vAxis, new LayeredBarRenderer());
		
		cPlot.setOrientation(PlotOrientation.VERTICAL);
		
		JFreeChart barChart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, cPlot, true);
		
		LayeredBarRenderer render = (LayeredBarRenderer) cPlot.getRenderer();
		configRenderer(render);

        CategoryAxis domain = cPlot.getDomainAxis();
        configDomain(domain);
        
        ChartPanel panel = new ChartPanel(barChart);
        panel.setSize(new Dimension(800, 367));
        
        // Return the ChartPanel so that can be sent into a frame
        return panel;
	}
}