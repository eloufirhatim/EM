package Test;

import interfaces.Law;
import algorithms.EM;
import algorithms.NormalDistribution;

import java.util.Random;

public class Main {

	static Random random = new Random(1);

	public static double gaussianSample(double mu, double sigma) {
		return (random.nextGaussian()) * sigma + mu;
	}

	public static double[] generateSample(int N) {
		double[] x = new double[N];

		// générer des valeurs aléatoires selon certaines laws
		// Ici 3 Laws (20%, 50%, 30%)
		for (int i = 0; i < N; i++) {
			double r = (100 * i) / N;
			if (r < 20) // 20% des laws
				x[i] = gaussianSample(2.0, 0.5);
			else if (r < 70) // 50% des laws
				x[i] = gaussianSample(6.0, 1.0);
			else
				// 30% des laws
				x[i] = gaussianSample(10.0, 2.0);
		}
		return x;
	}

	public static void main(String[] args) {
		// Estimation initiale pour les paramètres de laws
		int G = 3;
		Law[] laws = new Law[G];
		laws[0] = new NormalDistribution(4.0, 1.0);
		laws[1] = new NormalDistribution(8.0, 1.0);
		laws[2] = new NormalDistribution(12.0, 1.0);

		double[] x = generateSample(100);

		// Exécuter l'algorithme EM
		double pi[] = EM.algorithmEM(x, laws);

		// Affichage des coefficients de mélange
		for (int k = 0; k < G; k++)
			System.out.printf("%f * %s\n", pi[k], laws[k]);

		/*
		 * ----------------------------------------------------------------------------------
		 * |	Résultat affiché pour cet exemple (Console) :								|
		 * |	---------------------------------------------								|
		 * |	0,202513 * Gaussian( mean=1.867078570136912 , sigma=0.4528503752007887 )	|
		 * |	0,408323 * Gaussian( mean=5.74861320553719 , sigma=0.9900162367518208 ) 	|
		 * |	0,389164 * Gaussian( mean=9.463604608126928 , sigma=2.2534494054145497 )	|
		 * ----------------------------------------------------------------------------------
		 */
	}
}
