 package circuits;

import equationsSystem.GaussElimination;
import equationsSystem.GaussSeidel;
import matrix.Matrix;

public class NodePotential {

	private double[][] g;
	private double[] ue;
	private double[][] a;
	
	private double[] currents;
	private double[] potentials;
	
	public NodePotential(double[][] a, double[] r, double[] ue) {
		this.a = a;
		this.ue = ue;
		int edges = r.length;
		this.g = new double[edges][edges];
		for(int i=0; i < edges; i++) {
			g[i][i] = 1 / r[i];
		}
		solve();
	}
	
	private void solve() {
		double[][] ag = Matrix.multiply(a, g);
		double[][] at = Matrix.transpose(a);
		double[][] gprim = Matrix.multiply(ag, at);
		double[] isc = Matrix.minus(Matrix.multiply(ag, ue));
//		GaussElimination ge = new GaussElimination(gprim, isc);
//		potentials = ge.getResults();
		
		GaussSeidel gs = new GaussSeidel(gprim, isc);
		potentials = gs.getResults();
		
		currents = Matrix.multiply(g, Matrix.add(ue, Matrix.multiply(at, potentials)));
	}

	public double[] getCurrents() {
		return currents;
	}

	public double[] getPotentials() {
		return potentials;
	}
	
}
