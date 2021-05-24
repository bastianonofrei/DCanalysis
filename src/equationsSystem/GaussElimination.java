 package equationsSystem;

import matrix.Matrix;

public class GaussElimination {

	private double[][] a;
	private double[] b;
	private double[] x;
	private boolean debug = false;

	public GaussElimination(double[][] a, double[] b) {
		this.a = a;
		this.b = b;
	}
	
	private double[] getSolutionFromTriangularMatrix() {
		int n = a.length;
		double[] result = new double[n];
		
		for(int i = n-1; i>= 0; i--) {
			result[i] = b[i];
			for(int j = i + 1; j < n; j++) {
				result[i] -= a[i][j] * result[j];
			}
		}
		
		return result;
		
	}
	
	private void solve(){
		int n = a.length;
		for(int i=0; i < n; i++){
			double[][] m = Matrix.getGaussPivot(a, i);
			this.a = Matrix.multiply(m, a);
			this.b = Matrix.multiply(m, b);
			
			if(debug) {
				System.out.println();
				Matrix.print(a);
			}
		}
		
		x = getSolutionFromTriangularMatrix();
	}
	
	public double[] getResults() {
		solve();
		return x;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

}
