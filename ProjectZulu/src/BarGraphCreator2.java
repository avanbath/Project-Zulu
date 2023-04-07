


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

public class BarGraphCreator2 implements GraphCreator{
	public BarGraphCreator2(){
    }
	

	private DefaultCategoryDataset createDataset(List<DataTable> l) {

		DefaultCategoryDataset dSet = new DefaultCategoryDataset();
		//creating iterator for list of DataTables
		Iterator<DataTable> tableIterator = l.iterator();
		
		//while each individual table has not been added
		while (tableIterator.hasNext()) {
			//go to closest unused table
			DataTable dataTable = tableIterator.next();
			//create an iterator for the tables nhpi value double list
			Iterator<Double> doubleIterator = dataTable.getTable().iterator();
			boolean first = true;
			double monthCount = 0;
			int yearCount = 0;
			while (doubleIterator.hasNext()) {
				String date = "";
				System.out.println("Curent month before if " + monthCount);
				if (monthCount == 12 || first) {
					monthCount = 0;
					first = false;
					date = (dataTable.getStartYear() + yearCount)+(monthCount/12) + "";
					dSet.addValue(doubleIterator.next(), dataTable.getLocation(), date);
					yearCount++;
				}
				System.out.println("Curent month after if " + monthCount);

				doubleIterator.next();
				monthCount++;
			}

		}
		
		return dSet;
	}
	
	
	public ChartPanel create(List<DataTable> t) {
		int difference = t.get(0).getEndYear() - t.get(0).getStartYear();
		System.out.println("Size is " + difference);
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
        return panel;
	}
}

