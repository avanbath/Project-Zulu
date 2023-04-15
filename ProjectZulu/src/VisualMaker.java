// VisualMaker - Latest Version

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.jfree.chart.ChartPanel;

public class VisualMaker implements TableManagerSubscriber, FactoryCommunicator {
	private GraphCreator creator;
	private boolean yearly;
	
	// Line/bar graph creation methods
	@Override
	public void setCreatorLine() {
		this.creator = new LineGraphCreator();
	}

	@Override
	public void setCreatorBar() {
		this.creator = new BarGraphCreator();
	}

	@Override
	public void setYearly() {
		this.yearly = true;
	}

	@Override
	public void update(List<DataTable> l) {
		// Scrolling window setup
		JPanel panel=new JPanel();
	    JScrollPane scrollBar=new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	    JFrame frame = new JFrame();
	    frame.setSize(800,367);
	    
	    // Loop setup
	    int numOfTimeSeries = 0;
	    
	    // Create an iterator for the list of DataTables
	    Iterator<DataTable> i = l.iterator();
	    
	    // Create a list that contains tables of the same time series
		List<DataTable> sameSeries = new ArrayList<DataTable>();
		sameSeries.add((DataTable) i.next());
		
		while (i.hasNext()) {
			DataTable current = (DataTable) i.next();
			
			if (sameSeries.get(sameSeries.size()-1).checkSameSeries(current)){
				sameSeries.add(current);
			}
			
			else {
				ChartPanel chart = this.creator.create(sameSeries);
				panel.add(chart);
				
				sameSeries = new ArrayList<DataTable>();
				sameSeries.add(current);
				
				numOfTimeSeries++;
			}
		}
		
		ChartPanel chart = this.creator.create(sameSeries);
		panel.add(chart);
		
		numOfTimeSeries++;
		
		frame.getContentPane().add(scrollBar);
		frame.setLayout(new GridLayout(1,numOfTimeSeries));
		frame.setVisible(true);
	}
}