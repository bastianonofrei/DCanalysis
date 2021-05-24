 package circuits;

import equationsSystem.GaussSeidel;
import matrix.Matrix;

public class LoopCurrent {

	private double[][] r;
	private double[][] b;
	private double[] ue;
	
	private double[] loopCurrents;
	private double[] currents;
	
	public LoopCurrent(double[][] b, double[] r, double[] ue) {
		this.b = b;
		this.ue = ue;
		
		int edges = r.length;
		this.r = new double[edges][edges];
		for(int i = 0; i < edges; i++) {
			this.r[i][i] = r[i];
		}
		solve();
	}
	
	private void solve() {
		double[][] bt = Matrix.transpose(b);
		double[][] br = Matrix.multiply(b,  r);
		double[][] rprim = Matrix.multiply(br, bt);
		double[] ueprim = Matrix.multiply(b, ue);
		
		GaussSeidel ge = new GaussSeidel(rprim, ueprim);
		loopCurrents = ge.getResults();
		
		currents = Matrix.multiply(bt, loopCurrents);
	}

	public double[] getLoopCurrents() {
		return loopCurrents;
	}

	public double[] getCurrents() {
		return currents;
	}
	
}
