package auxstats;

import org.apache.commons.math3.distribution.BinomialDistribution;
import org.apache.commons.math3.distribution.ExponentialDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.PoissonDistribution;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;


public abstract class DistFuncs {

	
	public double UniformMean(double lower, double higher) {
		return (lower+higher)/2;
	}
	
	public double UniformVar(double lower, double higher) {
		return (Math.pow((higher-lower+1), 2) -1) / 12;
	}
	
	public double BinomMean(int attempts, double probability) {
		if (attempts < 0) {
			throw new NotPositiveException(attempts);
		}
		if (probability < 0 || probability > 1) {
			throw new OutOfRangeException(probability, 0, 1);
		}
		
		return attempts*probability;
	}
	
	public double BinomVar(int attempts, double probability) {
		return (attempts*probability) * (1 - probability);
	}
	
	public double BinomPDF(int attempts, double probability, int x) {
		BinomialDistribution binom = new BinomialDistribution(attempts, probability);
		return binom.probability(x);
	}
	
	public double BinomCDF(int attempts, double probability, int x) {
		BinomialDistribution binom = new BinomialDistribution(attempts, probability);
		return binom.cumulativeProbability(x);
	}
	
	public double PoissMean(double rate, double time) {
		if (rate*time <= 0) {
			throw new NotStrictlyPositiveException(rate*time);
		}
		return rate*time;
	}
	
	public double PoissPDF(double rate, double time, int x) {
		PoissonDistribution poiss = new PoissonDistribution(rate*time);
		return poiss.probability(x);
	}
	
	public double PoissCDF(double rate, double time, int x) {
		PoissonDistribution poiss = new PoissonDistribution(rate*time);
		return poiss.cumulativeProbability(x);
	}
	
	public double ContUniformVar(double lower, double upper) {
		return Math.pow(upper-lower, 2)/12;
	}
	
	public double ContUniformCDF(double lower, double upper, double x) {
		if (x<lower) {
			return 0;
		} 
		
		if (x>=upper) {
			return 1;
		}
		
		return (x - lower)/(upper - lower);
	}
	
	public double NormalCDF(double lower, double upper, double mean, double SD) {
		NormalDistribution norm = new NormalDistribution(mean, SD);
		return norm.cumulativeProbability(upper) - norm.cumulativeProbability(lower);
	}
	
	public double ExpCDF(double gamma, double x) {
		ExponentialDistribution exp = new ExponentialDistribution(1/gamma);
		return exp.cumulativeProbability(x);
	}
	

}
	

