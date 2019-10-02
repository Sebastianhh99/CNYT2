import java.util.*;
import java.io.*;

public class ComplexVector {
	private ComplexNumber[] vector;
	
	/**
	 * Constructor de la clase ComplexVector
	 * @param size tamaño del vector
	 */
	public ComplexVector(int size) {
		vector=new ComplexNumber[size];
	}
	
	/**
	 * Constructor de la clase ComplexVector
	 * @param vector el vector de numeros complejos
	 */
	public ComplexVector(ComplexNumber[] vector) {
		this.vector=vector;
	}
	
	public static ComplexVector turn(ComplexMatriz m) {
		ComplexNumber[][] a=m.getMatriz();
		ComplexVector ans=new ComplexVector(a[0].length);
		for(int i=0;i<a[0].length;i++) {
			ans.asigna(i, a[0][i]);
		}
		return ans;
	}
	
	/**
	 * Invierte el vector
	 * @return Un ComplexVector que es el inverso del mismo
	 */
	public ComplexVector invert() {
		return this.escalarProduct(new ComplexNumber(-1,0));
	}
	
	/**
	 * Suma de vectores
	 * @param other el vector a sumar con el otro
	 * @return un nuevo vector con la suma de los dos vectores
	 * @throws ComplexException Si los tamaños no cumplen tira excepcion
	 */
	public ComplexVector plus(ComplexVector other) throws ComplexException{
		ComplexNumber[] vector2=other.getVector();
		if(vector.length!=vector2.length) {
			throw new ComplexException(ComplexException.SIZE_ERROR);
		}
		ComplexVector ans=new ComplexVector(vector.length);
		for(int i=0;i<vector.length;i++) {
			ans.asigna(i, vector[i].plus(vector2[i]));
		}
		return ans;
	}
	
	/**
	 * Resta dos vectores
	 * @param other El otro vector
	 * @return Un vector con la Resta de los dos vectores
	 * @throws ComplexException
	 */
	public ComplexVector less(ComplexVector other) throws ComplexException{
		ComplexNumber[] vector2=other.getVector();
		if(vector.length!=vector2.length) {
			throw new ComplexException(ComplexException.SIZE_ERROR);
		}
		ComplexVector ans=new ComplexVector(vector.length);
		for(int i=0;i<vector.length;i++) {
			ans.asigna(i, vector[i].less(vector2[i]));
		}
		return ans;
	}
	
	/**
	 * Multiplica el vector por un producto escalar
	 * @param value El numero escalar
	 * @return Un nuevo vector multiplicado por el numero escalar
	 */
	public ComplexVector escalarProduct(ComplexNumber value) {
		ComplexVector ans=new ComplexVector(vector.length);
		for(int i=0;i<vector.length;i++) {
			ans.asigna(i, vector[i].product(value));
		}
		return ans;
	}
	
	/**
	 * Retorna la distancia entre los dos vectores
	 * @param other El otro vector
	 * @return double La distancia entre los dos vectores
	 * @throws ComplexException
	 */
	public double distance(ComplexVector other) throws ComplexException {
		return this.less(other).norm();
	}
	
	/**
	 * Retorna la norma del vector
	 * @return Double la norma del vector
	 */
	public double norm() {
		double ans=0;
		for(int i=0;i<vector.length;i++) {
			double[] rectangular=vector[i].getRectangular();
			ans+=Math.pow(rectangular[0], 2)+Math.pow(rectangular[1], 2);
		}
		ans=Math.sqrt(ans);
		return ans;
	}
	
	public ComplexNumber internalProducto(ComplexVector other) throws ComplexException{
		ComplexNumber ans=new ComplexNumber(0,0);
		ComplexNumber[] other2=other.getVector();
		if(other2.length!=vector.length) throw new ComplexException(ComplexException.SIZE_ERROR);
		for(int i=0;i<other2.length;i++) {
			ans=ans.plus(vector[i].product(other2[i]));
		}
		return ans;
	}
	
	/**
	 * Asigna un valor al vector
	 * @param i la posicion a asignar
	 * @param value el valor a asignar
	 */
	public void asigna(int i,ComplexNumber value) {
		vector[i]=value;
	}
	
	/**
	 * obtiene un array con los numeros Complejos
	 * @return Un array de ComplexNumbers
	 */
	public ComplexNumber[] getVector() {
		return vector;
	}
	
	/**
	 * Muestra en consola el vector
	 */
	public void print() {
		for(int i=0;i<vector.length;i++) {
			System.out.println(vector[i].getnumber());
		}
	}
	
	/**
	 * Muesta en consola el vector mostrando cada numero rectangular
	 */
	public void printr() {
		for(int i=0;i<vector.length;i++) {
			System.out.print("("+vector[i].getRectangular()[0]+" "+vector[i].getRectangular()[1]+") ");
			System.out.println(" ");
		}
	}
	
	/**
	 * Compara dos vectores
	 * @param other El otro vector
	 * @return true si son iguales false si son diferentes
	 */
	public boolean equals(ComplexVector other){
		ComplexNumber[] otherArray=other.getVector();
		if(otherArray.length!=vector.length) return false;
		for(int i=0;i<otherArray.length;i++) {
			if(!(otherArray[i].equals(vector[i]))) {
				return false;
			}
		}
		return true;
	}
	
	public void fill(ComplexNumber value) {
		Arrays.fill(vector, value);
	}
}
