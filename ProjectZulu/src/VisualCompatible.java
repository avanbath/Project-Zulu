import org.jfree.chart.ChartPanel;

public interface VisualCompatible {
	void receiveVisual(ChartPanel c);
	void receiveTable(ChartPanel c);
}
