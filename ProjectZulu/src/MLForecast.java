import java.util.List;
import java.util.ArrayList;

import weka.classifiers.functions.LinearRegression;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;

public class MLForecast implements Operation {
	List<DataTable> l;
	
	public void provideTables(List<DataTable> t) {
		this.l = t;
	}
	
	//clear tables
		public void clearTables() {
			this.l=null;
		}
	
		public String execute() throws Exception {
			String result=null;
			List<Double> sample = l.get(0).getTable();
			 Attribute attribute = new Attribute("value");
		        ArrayList<Attribute> attributes = new ArrayList<>();
		        attributes.add(attribute);
		        
		        Instances data = new Instances("data", attributes, sample.size());
		        data.setClassIndex(0);
			
		        for (Double value : sample) {
		            DenseInstance instance = new DenseInstance(1);
		            instance.setValue(attribute, value);
		            data.add(instance);
		        }
		       // Train the linear regression model
			LinearRegression model = new LinearRegression();
	        model.buildClassifier(data);
	        
	        // Forecast the next value in the list
	        DenseInstance newInstance = new DenseInstance(1);
	        newInstance.setValue(attribute, 6.0);
	        double forecast = model.classifyInstance(newInstance);
	       result = "The forecast for the next value is: " + forecast;
	        
			
			return result;
			
		}
		
		//testing with main method
		public static void main(String args[]) throws Exception{
			
			String result=null;
			List<Double> sample = new ArrayList<>();
			sample.add(1.2);
			sample.add(2.4);
			sample.add(3.6);
			sample.add(4.8);
			 Attribute attribute = new Attribute("value");
		        ArrayList<Attribute> attributes = new ArrayList<>();
		        attributes.add(attribute);
		        
		        Instances data = new Instances("data", attributes, sample.size());
		        data.setClassIndex(0);
			
		        for (Double value : sample) {
		            DenseInstance instance = new DenseInstance(1);
		            instance.setValue(attribute, value);
		            data.add(instance);
		        }
			
			
			LinearRegression model = new LinearRegression();
	        model.buildClassifier(data);
	        
	        // Forecast the next value in the list
	        DenseInstance newInstance = new DenseInstance(1);
	        newInstance.setValue(attribute, 6.0);
	        double forecast = model.classifyInstance(newInstance);
	       result = "The forecast for the next value is: " + forecast;
	       System.out.println(result);
		}

}
