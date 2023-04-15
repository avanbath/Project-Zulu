// StatTest - Latest Version

import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.stat.inference.*;
import java.lang.Math;
import java.util.List;
import java.util.Iterator;

public class StatTest implements Operation {
	List<DataTable> l;
	
	public void provideTables(List<DataTable> t) {
		this.l = t;
	}
	
	// Clear the tables
	public void clearTables() {
		this.l = null;
	}

	public String getResultString(double tscore, double critvalue){
      String result="";

		if(Math.abs(tscore) > Math.abs(critvalue)) {
			result = "The T-score is: " + String.format("%.2f", tscore) + ". The ABS value is greater than the critical value;  a = 0.05 of " + String.format("%.2f", critvalue) + ", so the difference is significant.";
		}

		else if (Math.abs(tscore) == 0.00){
			result = "The T-score is: " + 0 + ". The two samples are identical or almost identical.";
		}

		else if(Math.abs(tscore) < Math.abs(critvalue)) {
			result = "The T-score is: " + String.format("%.2f", tscore) + ". The ABS value is lower than the critical value; a = 0.05 of " + String.format("%.2f", critvalue)  + ", so the difference is insignificant.";
		}

	  return result;
	}

	// Compute the T-test
	public String execute() throws Exception {
		if(l.size()!= 2) {
			throw new Exception("The statistical test can only be performed with 2 regions.");
		}

		double[] sample1 = l.get(0).getTable().stream().mapToDouble(Double::doubleValue).toArray();
		double[] sample2 = l.get(1).getTable().stream().mapToDouble(Double::doubleValue).toArray();

		double tscore;
		double critvalue;
		int df = sample1.length - 1;
		
		TTest ttest = new TTest();
		// Compute the T value
		tscore = ttest.t(sample1, sample2);
	    TDistribution t = new TDistribution(df);
	    
	    // Find the critical value given the degrees of freedom
	    critvalue = t.inverseCumulativeProbability(0.95);
		//compute what the result string is
	    String result = getResultString(tscore,critvalue);
		return result;
	}
}