import org.apache.commons.math3.stat.descriptive.*;
import org.apache.commons.math3.stat.StatUtils;
import java.util.List;
import java.util.Iterator;

public class CompareNHPI implements Operation{
	List<DataTable> l;
	
	public void provideTables(List<DataTable> t) {
		this.l = t;
	}
	
	// Clear all tables
	public void clearTables() {
		this.l=null;
	}
	
	public  double[] fromListToArray(List<Double> l) {
		// Iterator class 
		
		// Count the elements
		Iterator<Double> i = l.iterator();
		
		int c = 0;
		
		while(i.hasNext()) {
			c++;
		}
		
		// Put the elements in an array
		double[] a = new double[c];
		
		c = 0;
		i = l.iterator();
		a[c] = i.next();
		
		while (i.hasNext()) {
			a[c] = i.next();
			c++;
		}
		return a;
	}
	
	public String execute() {
		// Check how many lists of NHPIs have been provided and create that amount of samples
		int numberOfSamples = l.size();
		String result=null;
		double mean,stdDev,max,min,variance;
		
		// Get information for every list provided
		for (int i = 0; i<numberOfSamples; i++) {
			double []sample = fromListToArray (l.get(i).getTable());
			
			// Get statistical information about this current NHPI list
			mean = StatUtils.mean(sample);
			max = StatUtils.max(sample);
			min = StatUtils.min(sample);
			variance = StatUtils.variance(sample);
			
			DescriptiveStatistics stats = new DescriptiveStatistics(sample);
			stdDev = stats.getStandardDeviation();
			
			String loopedresult = ("Sample #"+i+1+") min, max, mean & variance are: "+ min +", " + max + ", "+ String.format("%.2f", mean)+", " + String.format("%.2f", variance) + "\n" + "Standard Deviation: " + String.format("%.2f", stdDev)+"\n");
			result = result + loopedresult;
		}
		
		return result;
	}
}