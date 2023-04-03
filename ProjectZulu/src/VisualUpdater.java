import java.util.List;
import org.jfree.chart.ChartPanel;

public class VisualUpdater implements TableManagerSubscriber, FactoryCommunicator {
	private GraphCreator creator;
	private VisualCompatible ui;
	
	@Override
	public void setCreatorLine() {
		// TODO Auto-generated method stub
		this.creator = new LineGraphCreator();
	}

	@Override
	public void setCreatorBar() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void setClient(VisualCompatible v) {
		// TODO Auto-generated method stub
		this.ui = v;
	}

	@Override
	public void update(List<DataTable> l) {
		// TODO Auto-generated method stub
		//make this a try and catch block
		//that throws an exception if this.creator == null;
		//and when the ui field is null
		if (this.creator == null) {
			return;
		}
		
		if (this.ui == null) {
			return;
		}
		
		ChartPanel cP = this.creator.create(l);
		this.ui.receiveVisual(cP);
	}
}