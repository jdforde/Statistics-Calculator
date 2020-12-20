package auxstats;

import java.util.ArrayList;


public abstract class CommonCalc {
		
	public double getMean(ArrayList<Double> values) {
		double sum = 0;
		for (int i =0; i< values.size(); i++) {
			sum+=values.get(i);
		}
		
		return sum/values.size();
	}
	
	public double getVar(ArrayList<Double> values) {
		double mean = getMean(values);
		double sum = 0;
		for (int i = 0; i<values.size(); i++) {
			sum+=Math.pow(values.get(i)-mean, 2);
		}
				
		return sum/values.size();
	}
	
	public double getSampVar(ArrayList<Double> values) {
		double mean = getMean(values);
		double sum = 0;
		for (int i = 0; i<values.size(); i++) {
			sum+=Math.pow(values.get(i)-mean,  2);
		}

		return sum/(values.size()-1);

	}
	
	public double getSD(ArrayList<Double> values) {
		return Math.sqrt(getVar(values));
	}
	
	public double getSampSD(ArrayList<Double> values) {
		return Math.sqrt(getSampVar(values));
	}

}
