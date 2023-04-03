
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.stat.inference.*;
import java.lang.Math;
import java.util.List;
import java.util.Iterator;
public class statTest implements Operation {
	
	List<DataTable> l;
	
	public void provideTables(List<DataTable> t) {
		this.l = t;
	}
	//clear tables
	public void clearTables() {
		this.l=null;
	}
	public  double[] fromListToArray(List<Double> l) {
		//iterator class 
		
		//count elements
		Iterator<Double> i = l.iterator();
		int c = 0;
		while(i.hasNext()) {
			c++;
		}
		//put elements in array
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
	
	
	//Method for computing the T-test
	public String execute() throws Exception {
		
		if(l.size()!= 2) {
			throw new Exception("2 Lists must be provided only.");
			}
		//get the two lists of NHPI's
		double[]sample1 = fromListToArray (l.get(0).getTable());
		double[]sample2 =fromListToArray (l.get(1).getTable());
		
		String result = "default";
		double tscore;
		double critvalue;
		int df = sample1.length - 1;
		TTest ttest = new TTest();
		tscore = ttest.t(sample1, sample2); //This computes the t value
	    TDistribution t = new TDistribution(df); 
	    critvalue = t.inverseCumulativeProbability(0.95);//Find the critical value given the degrees of freedom
	    
	    if(Math.abs(tscore)>Math.abs(critvalue)) {
	    	result = "the t-score is " + String.format("%.2f", tscore) + " and its abs value is greater than critical value with a=0.05 of " + String.format("%.2f", critvalue) + " so the difference is significant";
  	    }
        else if (Math.abs(tscore)== 0.00){
        	result = "the t-score is "+ 0 +" Therefore the two samples are identical or almost identical";
        }
  	    else if(Math.abs(tscore)<Math.abs(critvalue)) {
  	    	result = "the t-score is " + String.format("%.2f", tscore) + " and its abs value is lower than critical value with a=0.05 of " + String.format("%.2f", critvalue)  + " so the difference is insignificant";
  	    }
		return result;
	}
	
	
}