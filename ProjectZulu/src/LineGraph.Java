import javax.swing.*;

import org.jfree.chart.*;
import org.jfree.data.*;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.util.Rotation;
import org.jfree.chart.plot.PlotOrientation;

public class LineGraph extends ApplicationFrame {
    public LineGraph(String appTitle, String chartTitle){
        super(appTitle);
        JFreeChart lChart = ChartFactory.createLineChart(chartTitle, "Years", "NHPI", createDataset(), PlotOrientation.VERTICAL, true, true, false);
        ChartPanel chartPanel = new ChartPanel( lChart );
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        setContentPane( chartPanel);
    }
    
    private DefaultCategoryDataset createDataset(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        DataManager.fillDataSet(dataset, "Alberta");
        return dataset;
    }

    public static void main(String args[]){
        LineGraph graph = new LineGraph("Line graph", "NHPI stat test");
        graph.pack();
        graph.setVisible(true);
    }
}
