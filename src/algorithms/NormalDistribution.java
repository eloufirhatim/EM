package algorithms;

import interfaces.Law;

public class NormalDistribution implements Law {

	private double mean = 0, sigma = 0;

	public NormalDistribution(double mean, double sigma) {
		this.mean = mean;
		this.sigma = sigma;
	}

	// Redéfinition de la méthode proba de l'interface
	public double proba(double x) {
		double t = (x - mean);
		return Math.exp(-(t * t) / (2 * sigma * sigma)) / (sigma * Math.sqrt(2 * Math.PI));
	}

	// Redéfinition de la méthode improveParameters de l'interface
	public void improveParameters(int N, double[] x, double[] tk) {
		double sumTkX = 0, sumTk = 0;
		for (int i = 0; i < N; i++)
			sumTkX += tk[i] * x[i];
		for (int i = 0; i < N; i++)
			sumTk += tk[i];
		mean = sumTkX / sumTk;
		double sumTkXc2 = 0;
		for (int i = 0; i < N; i++)
			sumTkXc2 += tk[i] * (x[i] - mean) * (x[i] - mean);
		sigma = Math.sqrt(sumTkXc2 / sumTk);
	}

	// La méthode toString pour l'affichage du résultat
	@Override
	public String toString() {
		return "Gaussian( mean=" + mean + " , sigma=" + sigma + " )";
	}
}