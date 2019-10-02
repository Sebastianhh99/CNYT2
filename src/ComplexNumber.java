import java.util.*;
import javafx.util.*;
import java.io.*;

public class ComplexNumber{
	private double[] polar= {0,0};
	private double[] rectangular= {0,0};
	private String number;	
	
	/**
	 * Construtor de clase ComplexNumnber
	 * @param number en forma de String de la forma "a+bi"
	 */
	public ComplexNumber(String number) {
		this.number=number;
		setRectangular(number);
		setPolar();
	}
	
	/**
	 * Constructor de clase ComplexNumber
	 * @param a la parte real
	 * @param b la parte imaginaria
	 */
	public ComplexNumber(double a,double b) {
		this.rectangular[0]=a;
		this.rectangular[1]=b;
		if(b>=0) {
			this.number=Double.toString(a)+"+"+Double.toString(b)+"i";
		}
		else {
			this.number=Double.toString(a)+Double.toString(b)+"i";
		}
		setPolar();
	}
	
	public static double[] turnRectangular(double a,double b) {
		double[] ans=new double[2];
		b=Math.toRadians(b);
		ans[0]=a*Math.toDegrees(Math.cos(b));
		ans[1]=a*Math.toDegrees(Math.sin(b));
		return ans;
	}
	
	/**
	 * Asigna la forma rectangular
	 * @param number
	 */
	private void setRectangular(String number) {
		String valorA="";
		String valorB="";
		boolean flag=true;
		for(int i=0;i<number.length();i++) {
			if(flag) {
				if((number.charAt(i)=='-' || number.charAt(i)=='+') && i!=0) {
					flag=false;
					i=i-1;
				}
				else {
					valorA+=number.charAt(i);
				}
			}
			else {
				if(number.charAt(i)!='i') {
					valorB+=number.charAt(i);
				}
			}
		}
		if(valorB.equals("") && valorA.charAt(valorA.length()-1)=='i') {
			valorB=valorA.substring(0,valorA.length()-1);
			valorA="0";
		}
		if(valorB.equals("-") || number.equals("-i")) {
			valorB="-1";
		}
		if(valorB.equals("+") || number.equals("i")) {
			valorB="1";
		}
		if(valorB.isEmpty()){
			valorB="0";
		}
		rectangular[0]=Double.parseDouble(valorA);
		rectangular[1]=Double.parseDouble(valorB);
	}
	
	/**
	 * Suma el numero conplejo con otro
	 * @param number El otro numero complejo
	 * @return la suma de los numeros complejos
	 */
	public ComplexNumber plus(ComplexNumber number){
		double[] rectangular=number.getRectangular();
		double[] ans= {this.rectangular[0]+rectangular[0],this.rectangular[1]+rectangular[1]};
		return new ComplexNumber(ans[0],ans[1]);
	}
	
	/**
	 * Hace el producto de los dos numeros complejos
	 * @param number El otro numero complejo
	 * @return El producto de los numeros complejos
	 */
	public ComplexNumber product(ComplexNumber number){
		double[] rectangular=number.getRectangular();
		double[] ans= {this.rectangular[0]*rectangular[0]-this.rectangular[1]*rectangular[1],this.rectangular[0]*rectangular[1]+this.rectangular[1]*rectangular[0]};
		return new ComplexNumber(ans[0],ans[1]);
	}
	
	/**
	 * Hace la resta de los numeros complejos
	 * @param number El otro numero complejo
	 * @return La resta de los numeros complejos
	 */
	public ComplexNumber less(ComplexNumber number){
		double[] rectangular=number.getRectangular();
		double[] ans= {this.rectangular[0]-rectangular[0],this.rectangular[1]-rectangular[1]};
		return new ComplexNumber(ans[0],ans[1]);
	}
	
	/**
	 * Hace la division de los numeros complejos
	 * @param number El otro numero complejo
	 * @return La division de los numeros complejos
	 */
	public ComplexNumber division(ComplexNumber number) throws ComplexException{
		if(number.equals(new ComplexNumber(0,0))) {
			throw new ComplexException(ComplexException.DIVISION_BY_0);
		}
		double[] pairA=this.rectangular;
		double[] pairB=number.getRectangular();
		double first= pairB[0]*pairB[0]+pairB[1]*pairB[1];
		double second=pairA[0]*pairB[0]+pairA[1]*pairB[1];
		double third=pairB[0]*pairA[1]-pairA[0]*pairB[1];
		double[] ans={second/first,third/first};
		return new ComplexNumber(ans[0],ans[1]);
	}
	
	/**
	 * Hace el modulo del numero complejo
	 * @return El modulo del numero
	 */
	public double module() {
		double ans=Math.sqrt(Math.pow(rectangular[0],2)+Math.pow(rectangular[1],2));
		return ans;
	}
	
	/**
	 * Hace el conjugado del numero complejo
	 * @return ComplexNumber un numero complejo que es el cnjugado de este
	 */
	public ComplexNumber conjugate() {
		ComplexNumber ans=new ComplexNumber(rectangular[0],rectangular[1]*-1);
		return ans;
	}
	
	/**
	 * Hace la fase del numero complejo
	 * @return la fase del numero complejo
	 */
	public double fase() {
		double fase=Math.toDegrees(Math.atan(rectangular[1]/rectangular[0]));
		if(rectangular[0]<0 && rectangular[1]<0 || rectangular[0]<0 && rectangular[1]>0) {
			fase+=180;
		}
		else if(rectangular[0]>0 && rectangular[1]<0) {
			fase+=360;
		}
		return fase;
	}
	
	/**
	 * Compara dos numeros complejos a ver si son iguales
	 * @param number El otro numero complejo
	 * @return true si son iguales false si son diferentes
	 */
	public boolean equals(ComplexNumber number) {
		double[] rectangular=number.getRectangular();
		if(this.rectangular[0]==rectangular[0] && this.rectangular[1]==rectangular[1]) {
			return true;
		}
		return false;
	}
	
	/**
	 * Asigna la forma polar del numero complejo
	 */
	private void setPolar() {
		double r=module();
		double t=fase();
		if(t<0) {
			t+=360;
		}
		polar[0]=r;
		polar[1]=t;
	}
	
	/**
	 * para obtener la forma polar
	 * @return la forma polar del numero
	 */
	public double[] getPolar() {
		return polar;
	}
	
	/**
	 * para obtener la forma rectangular
	 * @return la forma rectangular del numero
	 */
	public double[] getRectangular() {
		return rectangular;
	}
	
	/**
	 * para obtener la forma a+bi
	 * @return el numero con la forma a+bi
	 */
	public String getnumber() {
		return number;
	}
}
