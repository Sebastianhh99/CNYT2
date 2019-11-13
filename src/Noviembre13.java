
public class Noviembre13 {
	static ComplexMatriz Ucero= new ComplexMatriz(4,4);
	static ComplexMatriz Uuno= new ComplexMatriz(4,4);
	static ComplexMatriz UX= new ComplexMatriz(4,4);
	static ComplexMatriz UNX= new ComplexMatriz(4,4);
	static ComplexVector ket0=new ComplexVector(2);
	static ComplexVector ket1=new ComplexVector(2);
	static ComplexMatriz hada=new ComplexMatriz(2,2);
	
	public static void asigna() {
		Ucero.fill(new ComplexNumber(0,0));
		Ucero.asigna(0, 0, new ComplexNumber(1,0));
		Ucero.asigna(1, 1, new ComplexNumber(1,0));
		Ucero.asigna(2, 2, new ComplexNumber(1,0));
		Ucero.asigna(3, 3, new ComplexNumber(1,0));
		
		Uuno.fill(new ComplexNumber(0,0));
		Uuno.asigna(0, 1, new ComplexNumber(1,0));
		Uuno.asigna(1, 0, new ComplexNumber(1,0));
		Uuno.asigna(2, 3, new ComplexNumber(1,0));
		Uuno.asigna(3, 2, new ComplexNumber(1,0));
		
		UX.fill(new ComplexNumber(0,0));
		UX.asigna(0, 1, new ComplexNumber(1,0));
		UX.asigna(1, 0, new ComplexNumber(1,0));
		UX.asigna(2, 2, new ComplexNumber(1,0));
		UX.asigna(3, 3, new ComplexNumber(1,0));
		
		UNX.fill(new ComplexNumber(0,0));
		UNX.asigna(0, 0, new ComplexNumber(1,0));
		UNX.asigna(1, 1, new ComplexNumber(1,0));
		UNX.asigna(2, 3, new ComplexNumber(1,0));
		UNX.asigna(3, 2, new ComplexNumber(1,0));
		
		ket0.asigna(0, new ComplexNumber(1,0));
		ket0.asigna(1, new ComplexNumber(0,0));
		
		ket1.asigna(0, new ComplexNumber(0,0));
		ket1.asigna(1, new ComplexNumber(1,0));
		
		hada.fill(new ComplexNumber(1/Math.sqrt(2),0));
		hada.asigna(1, 1, new ComplexNumber(-1/Math.sqrt(2),0));
	}
	
	public static void main(String[] args) throws ComplexException {
		asigna();
		ComplexNumber[] temp=
				{new ComplexNumber(0,0),new ComplexNumber(1,0),new ComplexNumber(0,0),new ComplexNumber(0,0)};
		ComplexVector p0=new ComplexVector(temp);
		ComplexMatriz hh=hada.tensorProduct(hada);
		ComplexMatriz identidad=new ComplexMatriz(2,2);
		identidad.fill(new ComplexNumber(0,0));
		identidad.asigna(0, 0, new ComplexNumber(1,0));
		identidad.asigna(1, 1, new ComplexNumber(1,0));
		ComplexMatriz hi=hada.tensorProduct(identidad);
		ComplexVector p1=hh.product(p0);
		ComplexVector p2=UNX.product(p1);
		ComplexVector p3=hi.product(p2);
		Ventana v=new Ventana();
		v.setData2(p3);
	}
}
