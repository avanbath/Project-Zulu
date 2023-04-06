

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
	
	
	
	@Override
	public void setCreatorLine() {
		// TODO Auto-generated method stub
		this.creator = new LineGraphCreator();
	}

	@Override
	public void setCreatorBar() {
		// TODO Auto-generated method stub
		this.creator = new BarGraphCreator();
	}

	

	@Override
	public void setYearly() {
		// TODO Auto-generated method stub
		this.yearly = true;
		
	}

	@Override
	public void update(List<DataTable> l) {
		// TODO Auto-generated method stub
		if (this.creator == null) {
			System.out.println("You must set a creator before getting a visual");
		}
		else {
			System.out.println("The amount of dataTables is " + l.size());
			//scrolling window setup
			JPanel panel=new JPanel();
	        JScrollPane scrollBar=new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	        JFrame frame = new JFrame();
	        frame.setSize(800,367);
	        //loop setup
	        int counter = 0;
	        //creating iterator for list of DataTables
			Iterator<DataTable> i = l.iterator();
			//creating a list that contains tables of the same time series
			List<DataTable> sameSeries = new ArrayList<DataTable>();
			sameSeries.add((DataTable) i.next());
			System.out.println("First Table added " +  sameSeries.get(0).getLocation());
			while (i.hasNext()) {
				DataTable current = (DataTable) i.next();
				if (sameSeries.get(sameSeries.size()-1).checkSameSeries(current)){
					sameSeries.add(current);
				}
				else {
					System.out.println("Requesting chart panel for " + sameSeries.get(0).getStartYear() + ", " + sameSeries.get(0).getEndYear());
					ChartPanel chart = this.creator.create(sameSeries);
					System.out.println("Displaying chart panel");
					panel.add(chart);
					sameSeries = new ArrayList<DataTable>();
					sameSeries.add(current);
					counter++;
				}
			}
			//System.out.println("Creating another a final chart");
			System.out.println("Requesting chart panel for " + sameSeries.get(0).getStartYear() + ", " + sameSeries.get(0).getEndYear());
			ChartPanel chart = this.creator.create(sameSeries);
			System.out.println("Displaying chart panel");
			//System.out.println("Finished the final chart");
			panel.add(chart);
			counter++;
			frame.getContentPane().add(scrollBar);
	        frame.setLayout(new GridLayout(1,counter));
	        frame.setVisible(true);
		}
		
	}

}
