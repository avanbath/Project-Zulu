package my.custom.area;

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
		
		JPanel panel=new JPanel();
        JScrollPane scrollBar=new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        JFrame frame = new JFrame();
        frame.setSize(800,367);
        
        int counter = 0;
		Iterator<DataTable> i = l.iterator();
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
				counter++;
			}
		}
		ChartPanel chart = this.creator.create(sameSeries);
		panel.add(chart);
		
		frame.getContentPane().add(scrollBar);
        frame.setLayout(new GridLayout(1,counter));
        frame.setVisible(true);
		
	}

}
