import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.data.*;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.util.Rotation;
import org.jfree.chart.plot.PlotOrientation;

public class LineGraph extends ApplicationFrame {
    public LineGraph(String appTitle, String chartTitle, String place){
        super(appTitle);

        JFreeChart lChart = ChartFactory.createLineChart(chartTitle, "Years", "NHPI", createDataset(place), PlotOrientation.VERTICAL, true, true, false);
        ChartPanel chartPanel = new ChartPanel(lChart);

        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        setContentPane(chartPanel);
    }
    
    private DefaultCategoryDataset createDataset(String place){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        DataManager.fillDataSet(dataset, place);

        return dataset;
    }

    // Comment this out if you would like to see how an example line chart visualization is made
    //public static void main(String args[]){
        //LineGraph graph = new LineGraph("Line graph", "NHPI stat test", "Alberta");
        //graph.pack();
        //graph.setVisible(true);
    //}
}