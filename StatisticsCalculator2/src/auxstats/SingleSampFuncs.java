package auxstats;

import org.apache.commons.math3.distribution.ChiSquaredDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.TDistribution;

public abstract class SingleSampFuncs {
	
	public double[] ZInterval(boolean upper, boolean lower, double popSD, double sampMean, int n, double confidence) {
		double[] interval = new double[2];
		NormalDistribution normal = new NormalDistribution(0, 1);
		
		if (upper && lower) {
			double alpha = (1-confidence)/2;
			double testStat = normal.inverseCumulativeProbability(alpha);
			interval[0] = sampMean - testStat*(popSD/Math.sqrt(n));
			interval[1] = sampMean + testStat*(popSD/Math.sqrt(n));
			return interval;
		}
		
		if (upper && !lower) {
			double alpha = (1-confidence);
			double testStat = normal.inverseCumulativeProbability(alpha);
			interval[0] = sampMean - testStat*(popSD/Math.sqrt(n));
			return interval;
		}
		
		if (!upper && lower) {
			double alpha = (1-confidence);
			double testStat = normal.inverseCumulativeProbability(alpha);
			interval[0] = sampMean + testStat*(popSD/Math.sqrt(n));
			return interval;
		}
		
		return interval;
	}
	
	public double[] TInterval(boolean upper, boolean lower, double sampSD, double sampMean, int n, double confidence) {
		double[] interval = new double[2];
		TDistribution t = new TDistribution(n-1);
		
		if (upper && lower) {
			double alpha = (1-confidence)/2;
			double testStat = t.inverseCumulativeProbability(alpha);
			interval[0] = sampMean - testStat*(sampSD/Math.sqrt(n));
			interval[1] = sampMean + testStat*(sampSD/Math.sqrt(n));
			return interval;
		}
		
		if (upper && !lower) {
			double alpha = (1-confidence);
			double testStat = t.inverseCumulativeProbability(alpha);
			interval[0] = sampMean - testStat*(sampSD/Math.sqrt(n));
			return interval;
		}
		
		if (!upper && lower) {
			double alpha = (1-confidence);
			double testStat = t.inverseCumulativeProbability(alpha);
			interval[0] = sampMean + testStat*(sampSD/Math.sqrt(n));
			return interval;
		}
		
		return interval;
	}
	
	public double[] ChiInterval(boolean upper, boolean lower, double sampVar, int n, double confidence) {
		double[] interval = new double[2];
		ChiSquaredDistribution chi = new ChiSquaredDistribution(n-1);
		
		if (upper && lower) {
			double alpha = (1-confidence)/2;
			double testStat = chi.inverseCumulativeProbability(alpha);
			interval[0] = ((n-1)*Math.pow(sampVar, 2))/testStat;
			testStat = chi.inverseCumulativeProbability(1-alpha);
			interval[1] = ((n-1)*Math.pow(sampVar, 2))/testStat;
			return interval;
		}
		
		if (upper && !lower) {
			double alpha = (1-confidence);
			double testStat = chi.inverseCumulativeProbability(1-alpha);
			interval[0] = ((n-1)*Math.pow(sampVar, 2))/testStat;
			return interval;
		}
		
		if (!upper && lower) {
			double alpha = (1-confidence);
			double testStat = chi.inverseCumulativeProbability(alpha);
			interval[0] = ((n-1)*Math.pow(sampVar, 2))/testStat;
			return interval;
		}
		
		return interval;
	}
	
	public double[] PropInterval(boolean upper, boolean lower, double x, int n, double confidence) {
		double[] interval = new double[2];
		double sampProp = x/n;
		NormalDistribution normal = new NormalDistribution(0, 1);
		
		if (upper && lower) {
			double alpha = (1-confidence)/2;
			double testStat = normal.inverseCumulativeProbability(alpha);
			interval[0] = sampProp - testStat*(Math.sqrt((sampProp*(1-sampProp)/n)));
			interval[1] = sampProp + testStat*(Math.sqrt((sampProp*(1-sampProp)/n)));
			return interval;
		}
		
		if (upper && !lower) {
			double alpha = (1-confidence);
			double testStat = normal.inverseCumulativeProbability(alpha);
			interval[0] = sampProp - testStat*(Math.sqrt((sampProp*(1-sampProp)/n)));
			return interval;
		}
		
		if (!upper && lower) {
			double alpha = (1-confidence);
			double testStat = normal.inverseCumulativeProbability(alpha);
			interval[0] = sampProp + testStat*(Math.sqrt((sampProp*(1-sampProp)/n)));
			return interval;
		}
		
		
		return interval;
	}


	public double ZTest(double popSD, double sampMean, double muNaught, int n, int test) {
		double z = (sampMean - muNaught)/(popSD/Math.sqrt(n));
		NormalDistribution normal = new NormalDistribution(0, 1);
		
		if (test == 0) {
		 return 2*(1-normal.cumulativeProbability(Math.abs(z)));
		} else if (test == 1) {
			return normal.cumulativeProbability(z);
		} else {
			return 1-normal.cumulativeProbability(z);
		}
		
	}

	public double TTest(double sampSD, double sampMean, double muNaught, int n, int test) {
		double t = (sampMean - muNaught)/(sampSD/Math.sqrt(n));
		TDistribution tDist = new TDistribution(n-1);
		
		if (test == 0) {
		 return 2*(1-tDist.cumulativeProbability(Math.abs(t)));
		} else if (test == 1) {
			return tDist.cumulativeProbability(t);
		} else {
			return 1-tDist.cumulativeProbability(t);
		}
	}

	public double ChiTest(double sampSD, double sigmaNaught, int n, int test) {
		double chi = ((n-1)*Math.pow(sampSD, 2)/sigmaNaught);
		ChiSquaredDistribution chiDist = new ChiSquaredDistribution(n-1);
		
		if (test == 0) {
		 return 2*Math.min(chiDist.cumulativeProbability(0, chi), chiDist.cumulativeProbability(chi, 99999999));
		} else if (test == 1) {
			return chiDist.cumulativeProbability(0, chi);
		} else {
			return chiDist.cumulativeProbability(chi, 99999999);
		}
		
	}

	public double PropTest(double x, double pNaught, int n, int test) {
		double z = (x - n*pNaught)/(Math.sqrt(n*pNaught*(1-pNaught)));
		System.out.println(z);
		NormalDistribution normal = new NormalDistribution(0, 1);
		
		if (test == 0) {
		 return 2*(1-normal.cumulativeProbability(Math.abs(z)));
		} else if (test == 1) {
			return normal.cumulativeProbability(z);
		} else {
			return 1-normal.cumulativeProbability(z);
		}
		
	}
}