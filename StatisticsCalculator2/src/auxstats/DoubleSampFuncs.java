package auxstats;

import org.apache.commons.math3.distribution.FDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.TDistribution;

public abstract class DoubleSampFuncs {

	public double[] ZInterval(boolean upper, boolean lower, int size1, int size2, double popSD1, double popSD2, double sampMean1, 
							  double sampMean2, double confidence) {
		double[] interval = new double[2];
		NormalDistribution normal = new NormalDistribution(0, 1);
		
		if (upper && lower) {
			double alpha = (1-confidence)/2;
			double testStat = normal.inverseCumulativeProbability(alpha);
			interval[0] = (sampMean1 - sampMean2)  - (testStat*(Math.sqrt((Math.pow(popSD1, 2)/size1) + (Math.pow(popSD2, 2)/size2))));
			interval[1] = (sampMean1 - sampMean2)  + (testStat*(Math.sqrt((Math.pow(popSD1, 2)/size1) + (Math.pow(popSD2, 2)/size2))));
			return interval;
		}
		
		if (upper && !lower) { 
			double alpha = (1-confidence);
			double testStat = normal.inverseCumulativeProbability(alpha);
			interval[0] = (sampMean1 - sampMean2)  - (testStat*(Math.sqrt((Math.pow(popSD1, 2)/size1) + (Math.pow(popSD2, 2)/size2))));
			return interval;
		}
		
		if (!upper && lower) {
			double alpha = (1-confidence);
			double testStat = normal.inverseCumulativeProbability(alpha);
			interval[0] = (sampMean1 - sampMean2)  + (testStat*(Math.sqrt((Math.pow(popSD1, 2)/size1) + (Math.pow(popSD2, 2)/size2))));
			return interval;
		}
		
		return interval;
	}
	
	public double[] TIntervalPooled(boolean upper, boolean lower, int size1, int size2, double sampSD1, double sampSD2, double sampMean1,
									double sampMean2, double confidence) {
		
		
		double[] interval = new double[2];
		TDistribution t = new TDistribution(size1+size2-2);
		double sp = Math.sqrt((((size1 - 1)*Math.pow(sampSD1, 2)) + ((size2 - 1)*Math.pow(sampSD2, 2))) / (size1 + size2 - 2));
		
		if (upper && lower) {
			double alpha = (1-confidence)/2;
			double testStat = t.inverseCumulativeProbability(alpha);
			interval[0] = (sampMean1 - sampMean2) - (testStat*sp*(Math.sqrt((1.0/size1) + (1.0/size2))));
			interval[1] = (sampMean1 - sampMean2) + (testStat*sp*(Math.sqrt((1.0/size1) + (1.0/size2))));
			return interval;
		}
		
		if (upper && !lower) {
			double alpha = (1-confidence);
			double testStat = t.inverseCumulativeProbability(alpha);
			interval[0] = (sampMean1 - sampMean2) - (testStat*sp*(Math.sqrt((1.0/size1) + (1/size2))));
			return interval;
		}
		
		if (!upper && lower) {
			double alpha = (1-confidence);
			double testStat = t.inverseCumulativeProbability(alpha);
			interval[0] = (sampMean1 - sampMean2) + (testStat*sp*(Math.sqrt((1.0/size1) + (1.0/size2))));
			return interval;
		}
		
		return interval;
	}
	
	public double[] TIntervalUnpooled(boolean upper, boolean lower, int size1, int size2, double sampSD1, double sampSD2, double sampMean1,
			double sampMean2, double confidence) {

	
		double[] interval = new double[2];
		double numerator = Math.pow(((Math.pow(sampSD1, 2)/size1) + (Math.pow(sampSD2, 2)/size2)), 2);
		double denominator = ((Math.pow((Math.pow(sampSD1, 2)/size1), 2))/(size1 - 1) + (Math.pow((Math.pow(sampSD2, 2)/size2), 2))/(size2 - 1));
		double v = numerator / denominator;
		TDistribution t = new TDistribution(v);
		
		if (upper && lower) {
			double alpha = (1-confidence)/2;
			double testStat = t.inverseCumulativeProbability(alpha);
			interval[0] = (sampMean1 - sampMean2) - testStat*(Math.sqrt((Math.pow(sampSD1, 2)/size1) + (Math.pow(sampSD2, 2)/size2)));
			interval[1] = (sampMean1 - sampMean2) + testStat*(Math.sqrt((Math.pow(sampSD1, 2)/size1) + (Math.pow(sampSD2, 2)/size2)));
			return interval;
		}
		
		if (upper && !lower) {
			double alpha = (1-confidence);
			double testStat = t.inverseCumulativeProbability(alpha);
			interval[0] = (sampMean1 - sampMean2) - testStat*(Math.sqrt((Math.pow(sampSD1, 2)/size1) + (Math.pow(sampSD2, 2)/size2)));
			return interval;
		}
		
		if (!upper && lower) {
			double alpha = (1-confidence);
			double testStat = t.inverseCumulativeProbability(alpha);
			interval[0] = (sampMean1 - sampMean2) + testStat*(Math.sqrt((Math.pow(sampSD1, 2)/size1) + (Math.pow(sampSD2, 2)/size2)));
			return interval;
		}
		
		return interval;
	}

	public double[] FInterval(boolean upper, boolean lower, int size1, int size2, double sampVar1, double sampVar2, double confidence) {
		
		double[] interval = new double[2];
		FDistribution f = new FDistribution(size2-1, size1-1);
		
		if (upper && lower) {
			double alpha = (1-confidence)/2;
			double testStat = f.inverseCumulativeProbability(1-alpha);
			interval[0] = (sampVar1/sampVar2)*testStat;
			testStat = f.inverseCumulativeProbability(alpha);
			interval[1] = (sampVar1/sampVar2)*testStat;
			return interval;
			}
			
		if (upper && !lower) {
			double alpha = (1-confidence);
			double testStat = f.inverseCumulativeProbability(1-alpha);
			interval[0] = (sampVar1/sampVar2)*testStat;
			return interval;
		}
		
		if (!upper && lower) {
			double alpha = (1-confidence);
			double testStat = f.inverseCumulativeProbability(alpha);
			interval[0] = (sampVar1/sampVar2)*testStat;
			return interval;
		}
			
			return interval;
	}
	
	public double[] PropInterval(boolean upper, boolean lower, double x1, double x2, int n1, int n2, double confidence) {
		double[] interval = new double[2];
		NormalDistribution normal = new NormalDistribution(0, 1);
		double p1 = x1/n1;
		double p2 = x2/n2;
		
		if (upper && lower) {
			double alpha = (1-confidence)/2;
			double testStat = normal.inverseCumulativeProbability(alpha);
			interval[0] = (p1 - p2)  - (testStat*(Math.sqrt((p1*(1-p1)/n1 + ((p2*(1-p2)/n2))))));
			interval[1] = (p1 - p2)  + (testStat*(Math.sqrt((p1*(1-p1)/n1 + ((p2*(1-p2)/n2))))));
			return interval;
		}
		
		if (upper && !lower) { 
			double alpha = (1-confidence);
			double testStat = normal.inverseCumulativeProbability(alpha);
			interval[0] = (p1 - p2)  - (testStat*(Math.sqrt((p1*(1-p1)/n1 + ((p2*(1-p2)/n2))))));
			return interval;
		}
		
		if (!upper && lower) {
			double alpha = (1-confidence);
			double testStat = normal.inverseCumulativeProbability(alpha);
			interval[0] = (p1 - p2)  + (testStat*(Math.sqrt((p1*(1-p1)/n1 + ((p2*(1-p2)/n2))))));
			return interval;
		}
		
		return interval;
	}

	public double ZTest(double size1, double size2, double popSD1, double popSD2, double sampMean1, double sampMean2, int test) {
		double z = (sampMean1 - sampMean2)/Math.sqrt((Math.pow(popSD1, 2)/size1 + (Math.pow(popSD2, 2)/size2)));
		NormalDistribution normal = new NormalDistribution(0, 1);
		if (test == 0) {
			 return 2*(1-normal.cumulativeProbability(Math.abs(z)));
			} else if (test == 1) {
				return normal.cumulativeProbability(z);
			} else {
				return 1-normal.cumulativeProbability(z);
			}
	}
	
	public double TTestPooled(double size1, double size2, double sampSD1, double sampSD2, double sampMean1, double sampMean2, int test) {
		double sp = Math.sqrt((((size1 - 1)*Math.pow(sampSD1, 2)) + ((size2 - 1)*Math.pow(sampSD2, 2))) / (size1 + size2 - 2));
		double t = (sampMean1 - sampMean2) / (sp*Math.sqrt((1/size1) + (1/size2)));
		TDistribution tDist = new TDistribution(size1+size2-2);
		
		if (test == 0) {
		 return 2*(1-tDist.cumulativeProbability(Math.abs(t)));
		} else if (test == 1) {
			return tDist.cumulativeProbability(t);
		} else {
			return 1-tDist.cumulativeProbability(t);
		}
	}
	
	public double TTestUnpooled(double size1, double size2, double sampSD1, double sampSD2, double sampMean1, double sampMean2, int test) {
		double t = (sampMean1 - sampMean2) / Math.sqrt((Math.pow(sampSD1, 2)/size1) + (Math.pow(sampSD2, 2)/size2));
		double numerator = Math.pow(((Math.pow(sampSD1, 2)/size1) + (Math.pow(sampSD2, 2)/size2)), 2);
		double denominator = ((Math.pow((Math.pow(sampSD1, 2)/size1), 2))/(size1 - 1) + (Math.pow((Math.pow(sampSD2, 2)/size2), 2))/(size2 - 1));
		double v = numerator / denominator;
		TDistribution tDist = new TDistribution(v);
		
		if (test == 0) {
		 return 2*(1-tDist.cumulativeProbability(Math.abs(t)));
		} else if (test == 1) {
			return tDist.cumulativeProbability(t);
		} else {
			return 1-tDist.cumulativeProbability(t);
		}
	}

	public double FTest(double size1, double size2, double sampVar1, double sampVar2, int test) {
		FDistribution f = new FDistribution(size2-1, size1-1);
		double fNaught = sampVar1/sampVar2;
		if (test == 0) {
			return 2*Math.min(f.cumulativeProbability(0, fNaught), f.cumulativeProbability(fNaught, 99999999));
		} else if (test == 1) {
			return f.cumulativeProbability(fNaught, 99999999);
		} else {
			return f.cumulativeProbability(0, fNaught);
		}
	}

	public double PropTest(double x1, double x2, double n1, double n2, int test) {
		double p1 = x1/n1;
		double p2 = x2/n2;
		double pHat = (x1+x2)/(n1+n2);
		double zNaught = (p1 - p2) / Math.sqrt((pHat*(1-pHat)*((1.0/n1) + (1.0/n2))));
		NormalDistribution normal = new NormalDistribution(0, 1);
		if (test == 0) {
			 return 2*(1-normal.cumulativeProbability(Math.abs(zNaught)));
			} else if (test == 1) {
				return 1-normal.cumulativeProbability(zNaught);
			} else {
				return normal.cumulativeProbability(zNaught);
			}
		
	}
}
