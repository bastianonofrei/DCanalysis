 package equationsSystem;

import matrix.Matrix;

/**
 * The Gauss-Seidel method is convergent for larger diagonal dominance systems. 
 * @param double[][] A = the coefficients square matrix
 * @param double[] B = the free terms column
 * @return the solution for a diagonal dominance system obtained after an iterative correction process
 * @author Sorea Dan
 * */
public class GaussSeidel {
	private double[][] a;
	private double[] b;
	private int range;
	private boolean debug = false;
	private double[] x;
	private int MAX_RANGE = 500;
	private String NOTGAUSSSEIDELSYSTEM = "System not compliant with Gauss-Seidel Method";
	private String GAUSSSEIDELOUTOFRANGE = "Rank system out of range";
	
	public GaussSeidel(double[][] mat, double[] vect){
		this.a = mat;
		this.b = vect;
		this.range = b.length;
		this.x = new double[this.range];
	}
	
	public double[] getResults(){
		if(checkGaussSeidel(range)){
			x = solve();
		} else {
			x = xNaN();
			if(debug) {
				System.err.println(NOTGAUSSSEIDELSYSTEM);
			}
		}
		return x;
	}
	
	/**
	 * @return a vector initialized with NaN formatted as double[]
	 * 
	 * */
	private double[] xNaN() {
		double[] result = new double[x.length];
		for(int i=0; i<result.length; i++) {
			result[i] = Double.NaN;
		}
		return result;
	}
	
	/**
	 * @param int = range of the equations system
	 * @return true if the range below MAX_RANGE (for memory space reason) AND if the system is a diagonal dominance system, otherwise false
	 * 
	 * */
	private boolean checkGaussSeidel(int range){
		boolean result = true;
		if(checkDiagonal(range)) {
			if(range < MAX_RANGE) {
				result &= true;
			} else {
				System.err.println(GAUSSSEIDELOUTOFRANGE);
				result &= false;
			}
		} else {
			result &= false;
		}
		return result;
	}
	
	/**
	 * for each row of the matrix the term on the diagonal must be greater than or equal to the sum of all the other terms (aboslute values of them) 
	 * @return true if the above condition is satisfied
	 * 
	 * */	
	private boolean checkDiagonal(int range){
		boolean result = true;
		for(int i=0; i<range; i++){
			double s = 0;
			for(int j=0; j<range; j++){
				s += (i!=j)?Math.abs(a[i][j]):0;
			}
			if(s > Math.abs(a[i][i])){
				System.err.println(NOTGAUSSSEIDELSYSTEM);
				result &= false;
			}
		}
		return result;
	}

	/**
	 * performs a check and returns the result of the Gauss-Seidel algorithm
	 * 
	 * */
	private double[] solve(){
		double[] result = new double[range];
		for(int i=0; i<result.length; i++){
			result[i] = 0;
		}
		double eps = 1e-9;
		double errMax;
		int iter = 0; 
		do{
			iter++;
			errMax = 0;
			for(int i=0; i<range; i++){
				double old = result[i];
				double s = 0;
				for(int j=0; j<range; j++){
					if(i != j)
						s += a[i][j] * result[j];
				}
				result[i] = (b[i] - s)/a[i][i];
				if(Math.abs(result[i]-old) > errMax){
					errMax = Math.abs(result[i] - old);
				}
			}
			if(debug){
				System.out.println("Iteration: "+iter+"; error: "+errMax);
				Matrix.print(result);
			}
		}while(errMax > eps);
		return result;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}
}
