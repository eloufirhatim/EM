package algorithms;

import interfaces.Law;

public class EM {

	/*
	 * -> Calculer les coefficients de mélange en utilisant l'algorithme EM
	 * Prend en paramètres : 
	 * 		- x : les valeurs des échantillons
	 * 		- laws : instances des Law
	 * Retourne les coefficients
	 */
	public static double[] algorithmEM(double[] x, Law[] laws) {
		int N=x.length;
		int G=laws.length;
	 
		double[] pi = new double[G];
		double[][] t = new double[G][N];
	 
		// Estimation initiale pour les coefficients de mélange
		for(int k=0;k<G;k++) pi[k]=1.0/G;
	 
		// Boucle itérative (jusqu'à convergence ou 5000 itérations)
		double convergence;
		for(int loop=0;loop<5000;loop++) {
			convergence=0;
	 
			// ---- E Step ----
	 
			//(Formule d'inversion de Bayes)
			for(int i=0;i<N;i++) {
				double denominator = 0;
				for(int l=0;l<G;l++) denominator+=pi[l]*laws[l].proba(x[i]);
				for(int k=0;k<G;k++) {
					double numerator = pi[k]*laws[k].proba(x[i]);
					t[k][i]=numerator/denominator;
				}
			}
	 
			// ---- M Step ----
	 
			// Coefficients de mélange (de l'estimateur du maximum de vraisemblance de distribution binomiale)
			for(int k=0;k<G;k++) {
				double savedpi=pi[k];
				pi[k]=0;
				for(int i=0;i<N;i++) pi[k]+=t[k][i];
				pi[k]/=N;
				convergence+=(savedpi-pi[k])*(savedpi-pi[k]);
			}
	 
			// Mettre à jour les paramètres des laws
			for(int k=0;k<G;k++)
				laws[k].improveParameters(N, x, t[k]);
	 
			if( convergence < 1E-10 ) break;
		}
	 
		return pi;
	}
	
}
