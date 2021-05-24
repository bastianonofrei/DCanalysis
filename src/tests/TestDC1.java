 package tests;

import circuits.LoopCurrent;
import circuits.NodePotential;
import matrix.Matrix;

public class TestDC1 {

	public static void main(String[] args) {
		// node potentials
		
		double[][] a = {  {1, 1, 0, 0, 0, 0,0,0,-1}
						, {-1, 0, 1, 1, 0, 0,0,0,0}
						, {0, -1, -1, 0, 1, 0,0,0,0}
						,{0, 0, 0, -1, -1, 1,0,0,0},
						{0, 0, 0, 0, 0, -1,1,1,0}
						};
		
		
		// loop currents
		
		double[][] b = {  {1, -1, 1, 0, 0, 0,0,0,0}
		, {0, 0, -1, 1, -1, 0,0,0,0}
		, {0, 1, 0, 0, 1, 1,0,1,1}
		,{0, 0, 0, 0, 0, 0,1,-1,0}
		
		};
		
		
		double[] r = {1,2,3,4,5,6,7,8,9};
		double[] ue = {10, 20, 0, 0, 0, 0,0,0,0};
		
		NodePotential np = new NodePotential(a, r, ue);
		double[] currents = np.getCurrents();
		Matrix.print(currents);
		System.out.println("\n===========================");
		double[] potentials = np.getPotentials();
		Matrix.print(potentials);

		
		System.out.println("\nThe initial values for currents are: ");
		Matrix.print(currents);
		
		Verify.test(a, b);
		Verify.powerBalance(currents, ue, b, r);
		
	
		// for edge 5 shortcircuited
		
double[] ResistancesShortCircuit = {1.0, 2.0, 3.0, 4.0, 0.0, 6.0,7.0,8.0,9.0};
		
		LoopCurrent CurrentShortCirc = new LoopCurrent(b, ResistancesShortCircuit, ue);
		double[] currentShortCircuit = CurrentShortCirc.getCurrents();
		
		System.out.println(" \nThe values for currents with edge 5 shortcircuited are: \n");
		Matrix.print(currentShortCircuit);
		
		
	}

}
