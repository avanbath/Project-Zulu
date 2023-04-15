// CompareNHPI - Latest Version

import org.apache.commons.math3.stat.descriptive.*;
import org.apache.commons.math3.stat.StatUtils;
import java.util.List;
import java.util.Iterator;

public class CompareNHPI implements Operation{
	List<DataTable> l;
	
	public void provideTables(List<DataTable> t) {
		this.l = t;
	}
	
	// Clear the tables
	public void clearTables() {
		this.l = null;
	}

	public String execute() {
		// Check how many lists of NHPIs have been provided and create that amount of samples
		int numberOfSamples = l.size();
		double mean, stdDev, max, min, variance;
		String result = "";
		
		// Get information for every list provided
		for (int i = 0; i < numberOfSamples; i++) {
			double[] sample = l.get(i).getTable().stream().mapToDouble(Double::doubleValue).toArray();
			
			// Get statistical information about this current NHPI list
			mean = StatUtils.mean(sample);
			max = StatUtils.max(sample);
			min = StatUtils.min(sample);
			variance = StatUtils.variance(sample);
			
			DescriptiveStatistics stats = new DescriptiveStatistics(sample);
			stdDev = stats.getStandardDeviation();
			
			String loopedresult = ("Region " + (i + 1) + ") Min, Max, Mean & Variance: " + min + ", " + max + ", " + String.format("%.2f", mean) + ", " + String.format("%.2f", variance) + "; Standard Deviation: " + String.format("%.2f", stdDev) + "  ||  ");
			result += loopedresult;
		}
		
		return result;
	}
}