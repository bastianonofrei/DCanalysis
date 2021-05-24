 package matrix;

public class Matrix {

	public static void print(double[][] a) {
		int n = a.length;
		int m = a[0].length;
		
		for(int i=0; i<n; i++){
			for(int j=0; j<m; j++){
				System.out.print("\t"+a[i][j]);
			}
			System.out.println();
		}		
	}

	public static void print(double[] a){
		for(int i=0; i<a.length; i++){
			System.out.println(a[i]);
		}
	}
	
	public static double[][] multiply(double[][] a, double[][] b) {
		int n = a.length;
		int m = a[0].length;
		int p = b[0].length;

		double[][] c = new double[n][p];

		for(int i=0; i<n; i++){
			for(int j=0; j<p; j++){
				c[i][j] = 0;
				for(int k=0; k<m; k++){
					c[i][j] += a[i][k] * b[k][j];
				}
			}
		}
		return c;
	}

	public static double[] multiply(double[][] a, double[] b){
		int n = a.length;
		int m = a[0].length;
		double[] c = new double[n];
		for(int i=0; i<n; i++){
			c[i] = 0;
			for(int j=0; j<m; j++){
				c[i] += a[i][j] * b[j];
			}
		}
		return c;
	}
	
	public static double[][] getIdentity(int n){
		double[][] result = new double[n][n];
		for(int i=0; i<n; i++) {
			result[i][i] = 1;
		}
		return result;
	}
	
	public static double[][] getGaussPivot(double[][] a, int col){
		int n = a.length;
		double[][] result = getIdentity(n);
		result[col][col] = 1 / a[col][col];
		for(int i = col + 1; i < n; i++){
			result[i][col] = - a[i][col] / a[col][col];
		}
		return result;
	}
	
	public static double[][] transpose(double[][] a){
		int n = a.length;
		int m = a[0].length;
		double[][] result = new double[m][n];
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				result[i][j] = a[j][i];
			}
		}
		
		return result;
	}
	public static double[] minus(double[] a) {
		int n = a.length;
		double[] result = new double[n];
		for(int i = 0; i < n; i++) {
			result[i] = -a[i];
		}
		return result;
	}
	public static double[] add(double[] a, double[] b) {
		int n = a.length; 
		double[] result = new double[n];
		for(int i = 0; i < n; i++) {
			result[i] = a[i] + b[i];
		}
		return result;
	}
	
}
