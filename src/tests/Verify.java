 package tests;

import matrix.Matrix;

public class Verify {
		public static void test(double[][] a, double[][] b) {
			boolean cond1 = true;
			boolean cond2 = true;
			
			double o[][] = {  {0, 0, 0,0,0},
					 {0, 0, 0,0,0},
					 {0, 0, 0,0,0},
					 {0, 0, 0,0,0},
					 {0, 0, 0,0,0}
							};
			double[][] at = Matrix.transpose(a);
			double[][] bt = Matrix.transpose(b);
			double[][] bat = Matrix.multiply(b,  at);
			double[][] abt = Matrix.multiply(a,  bt);
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < 5; j++) {
					if(bat[i][j] != o[i][j])
						cond1 = false; 
				}
		
		}
			for(int i = 0; i < 5; i++) {
				for(int j = 0; j < 4; j++) {
					if(abt[i][j] != o[i][j])
						cond2 = false; 
				}
		
		}
if(cond1 == true) {
	System.out.println("BxAt = 0");
}else {
	System.out.println("BxAt != 0");
}
if(cond2 == true) {
	System.out.println("AxBt = 0");
}else {
	System.out.println("AxBt != 0");
}	
}
public static void powerBalance(double[] currents, double[] ue, double[][] b, double[] r) {
	double Pg = 0.0;
	double Pa = 0.0;
	double[][] newue = new double[1][ue.length];
	for(int i = 0; i < 1; i++) {
		for(int j = 0; j < ue.length; j++) {
			newue[i][j] = ue[j];
		}
	}
	double[][] newr = new double[1][r.length];
	for(int i = 0; i < 1; i++) {
		for(int j = 0; j < r.length; j++) {
			newr[i][j] = r[j];
		}
	}

	double[] pg = Matrix.multiply(newue, currents);
	for(int i = 0; i < pg.length; i++) {
		
			Pg += pg[i];
	}
	double[] squaredCurrents = new double[currents.length];
	for(int i = 0; i < currents.length; i++) {

			squaredCurrents[i] = Math.pow(currents[i], 2);
	}
	double[] pa = Matrix.multiply(newr, squaredCurrents);
	for(int i = 0; i < pg.length; i++) {
		
			Pa += pa[i];
	}
	System.out.println("\nGenerated power is " + Pg + " Watt and absorbed power is " + Pa + " Watt");
	if (Pg==Pa) {

	System.out.println("Pg = Pa");
}
}
}