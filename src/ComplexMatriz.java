import java.util.*;
import javafx.util.*;
import java.io.*;

public class ComplexMatriz {
	
	private ComplexNumber[][] matriz;
	
	/**
	 * Constructor de la clase ComplexMatriz
	 * @param n la cantidad de filas
	 * @param m la cantidad de columnas
	 */
	public ComplexMatriz(int n,int m) {
		matriz=new ComplexNumber[n][m];
	}
	
	/**
	 * Constructor de la clase ComplexMatriz
	 * @param matriz La matriz de numeros complejos
	 */
	public ComplexMatriz(ComplexNumber[][] matriz) {
		this.matriz=matriz;
	}
	
	public static ComplexMatriz turn(ComplexVector v) {
		ComplexNumber[] c=v.getVector();
		ComplexNumber[][] a={c};
		return new ComplexMatriz(a);
	}
	
	/**
	 * Invierte la matriz
	 * @return Una ComplexMatriz invertida
	 */
	public ComplexMatriz invert() {
		return this.escalarProduct(new ComplexNumber(-1,0));
	}
	
	/**
	 * Asigna un valor a la fila,columna de la matriz
	 * @param fila del valor
	 * @param columna del valor 
	 * @param valor el valor a asignar
	 */
	public void asigna(int fila,int columna,ComplexNumber valor) {
		matriz[fila][columna]=valor;
	}
	
	/**
	 * Suma dos matrices
	 * @param other La otra matriz
	 * @return Una ComplexMatriz con la suma de ambas matrices
	 * @throws ComplexException 
	 */
	public ComplexMatriz plus(ComplexMatriz other) throws ComplexException {
		ComplexNumber[][] matriz2=other.getMatriz();
		if(matriz.length!=matriz2.length || matriz[0].length!=matriz2[0].length) {
			throw new ComplexException(ComplexException.SIZE_ERROR);
		}
		ComplexMatriz ans=new ComplexMatriz(matriz.length,matriz[0].length);
		for(int i=0;i<matriz.length;i++) {
			for(int j=0;j<matriz[0].length;j++) {
				ans.asigna(i,j,matriz[i][j].plus(matriz2[i][j]));
			}
		}
		return ans;
	}
	
	/**
	 * Devuelve la adjunta de una matriz
	 * @return la adjunta de la matriz
	 */
	public ComplexMatriz adjoint() {
		ComplexMatriz ans=this.conjugate();
		return ans.transposed();
	}
	
	/**
	 * Genera el conjugado de una matriz
	 * @return Una ComplexMatriz que es el conjugado de esta
	 */
	public ComplexMatriz conjugate() {
		ComplexMatriz ans=new ComplexMatriz(matriz.length,matriz[0].length);
		for(int i=0;i<matriz.length;i++) {
			for(int j=0;j<matriz[0].length;j++) {
				ans.asigna(i, j, matriz[i][j].conjugate());
			}
		}
		return ans;
	}
	
	/**
	 * Transpone la Matriz
	 * @return Una ComplexMatriz que es la transpuesta de la misma
	 */
	public ComplexMatriz transposed() {
		ComplexMatriz ans=new ComplexMatriz(matriz[0].length,matriz.length);
		for(int i=0;i<matriz.length;i++) {
			for(int j=0;j<matriz[i].length;j++) {
				ans.asigna(j, i, matriz[i][j]);
			}
		}
		return ans;
	}
	
	/**
	 * Retorna el poducto de la matriz y un vecotr
	 * @param other El vector a multiplicar
	 * @return Un vector que es la multiplicacion del vector y la matriz
	 * @throws ComplexException
	 */
	public ComplexVector product(ComplexVector other) throws ComplexException{
		ComplexMatriz other2=new ComplexMatriz(other.getVector().length,1);
		for(int i=0;i<other.getVector().length;i++) {
			for(int j=0;j<1;j++) {
				other2.asigna(i, j, other.getVector()[i]);
			}
		}
		ComplexMatriz temp_ans=this.product(other2);
		ComplexVector ans=new ComplexVector(temp_ans.getMatriz().length);
		for(int i=0;i<temp_ans.getMatriz().length;i++) {
			for(int j=0;j<1;j++) {
				ans.asigna(i, temp_ans.getMatriz()[i][j]);
			}
		}
		return ans;
	}
	
	/**
	 * Hace el producto de dos matrices
	 * @param other La otra matriz
	 * @return Una complexMatriz que es la multiplicacion
	 */
	public ComplexMatriz product(ComplexMatriz other) throws ComplexException{
		ComplexNumber[][] other2=other.getMatriz();
		if(this.matriz[0].length!=other2.length) {
			throw new ComplexException(ComplexException.SIZE_ERROR);
		}
		ComplexNumber[][] ansMatriz=new ComplexNumber[matriz.length][other2[0].length];
		for(int i=0;i<ansMatriz.length;i++) {
			Arrays.fill(ansMatriz[i],new ComplexNumber(0,0));
		}
		for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < other2[0].length; j++) {
                for (int k = 0; k < matriz[0].length; k++) {
                    ansMatriz[i][j] = ansMatriz[i][j].plus(matriz[i][k].product(other2[k][j]));
                }
            }
        }
		return new ComplexMatriz(ansMatriz);
	}
	
	/**
	 * Comprueba si una matriz es Hermitian
	 * @return boolean de si la matriz es Hermitian
	 */
	public boolean hermitian(){
		if(this.equals(this.adjoint())) {
			return true;
		}
		return false;
	}
	
	/**
	 * Comprueba si una matriz es una unitaria
	 * @return boolean si la la matriz es unitaria
	 */
	public boolean unitary() {
		ComplexMatriz complexMatriz;
		boolean ans=true;
		try {
			ComplexMatriz adjunta=this.adjoint();
			complexMatriz = this.product(adjunta);
			ComplexNumber[][] matriz=complexMatriz.getMatriz();
			for(int i=0;i<matriz.length;i++) {
				for(int j=0;j<matriz[i].length;j++) {
					if(i!=j && Math.round(matriz[i][j].getRectangular()[0])!=0) {
						ans=false;
					}
					else if(i==j && Math.round(matriz[i][j].getRectangular()[0])!=1){
						ans=false;
					}
				}
			}
		} catch (ComplexException e) {
			e.printStackTrace();
		}
		return ans;
	}
	
	/**
	 * Realiza el producto tensor entre dos matrices
	 * @param other La otra matriz
	 * @return una matriz de complejos con el resultado del producto tensor
	 */
	public ComplexMatriz tensorProduct(ComplexMatriz other) {
		ComplexNumber[][] other2=other.getMatriz();
		ComplexMatriz ans=new ComplexMatriz(other2.length*matriz.length,other2[0].length*matriz[0].length);
		for(int i=0;i<other2.length*matriz.length;i++) {
			for(int j=0;j<other2[0].length*matriz[0].length;j++) {
				ans.asigna(i, j, matriz[i/other2.length][j/other2[0].length].product(other2[i%other2.length][j%other2[0].length]));
			}
		}
		return ans;
	}
	
	/**
	 * Realiza el producto por un escalar
	 * @param value el valor del numero escalar
	 * @return Una ComplexMatriz con la multiplicacion del producto escalar
	 */
	public ComplexMatriz escalarProduct(ComplexNumber value) {
		ComplexMatriz ans=new ComplexMatriz(matriz.length,matriz[0].length);
		for(int i=0;i<matriz.length;i++) {
			for(int j=0;j<matriz[0].length;j++) {
				ans.asigna(i, j, matriz[i][j].product(value));
			}
		}
		return ans;
	}
	
	/**
	 * Compara dos matrices de numeros complejos
	 * @param other La otra matriz
	 * @return boolean si las matrices son iguales o no
	 */
	public boolean equals(ComplexMatriz other) {
		ComplexNumber[][] matriz2=other.getMatriz();
		if(matriz2[0].length!=matriz[0].length || matriz2.length!=matriz.length) {
			return false;
		}
		for(int i=0;i<matriz.length;i++) {
			for(int j=0;j<matriz[0].length;j++) {
				if(!matriz[i][j].equals(matriz2[i][j])) {
					return false;
				}
			}
		}
		return true;
	}
	
	public void fill(ComplexNumber value) {
		for(int i=0;i<matriz.length;i++) {
			Arrays.fill(matriz[i], value);
		}
	}
	
	public void fillColumn(int n,ComplexNumber value) {
		for(int i=0;i<matriz.length;i++) {
			matriz[i][n]= (i==n)? matriz[i][n]:value;
		}
	}
	
	public void fillRange(int n,int init,int end,ComplexNumber value) {
		for(int i=init;i<end;i++) {
			matriz[i][n]=value;
		}
	}
	
	/**
	 * Retorna la matriz
	 * @return la matriz de numeros complejos
	 */
	public ComplexNumber[][] getMatriz(){
		return matriz;
	}
	
	public void print() {
		for(int i=0;i<matriz.length;i++) {
			for(int j=0;j<matriz[i].length;j++) {
				System.out.print(matriz[i][j].getnumber()+" ");
			}
			System.out.println("");
		}
	}
	
	/**
	 * Muestra la matriz con los valores en forma rectangular
	 */
	public void printr() {
		for(int i=0;i<matriz.length;i++) {
			for(int j=0;j<matriz[i].length;j++) {
				System.out.print("("+matriz[i][j].getRectangular()[0]+" "+matriz[i][j].getRectangular()[1]+") ");
			}
			System.out.println("");
		}
	}
}
