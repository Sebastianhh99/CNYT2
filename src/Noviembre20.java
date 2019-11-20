

public  class Noviembre20{

    static ComplexMatriz uf=new ComplexMatriz(8,8);
    static ComplexMatriz hada=new ComplexMatriz(2,2);

    public static void main(String[] args) {
    	uf.fill(new ComplexNumber(0,0));
    	uf.asigna(0,0,new ComplexNumber(1,0));
    	uf.asigna(1,1,new ComplexNumber(1,0));
    	uf.asigna(2,2,new ComplexNumber(1,0));
    	uf.asigna(3,3,new ComplexNumber(1,0));
    	uf.asigna(4,5,new ComplexNumber(1,0));
    	uf.asigna(5,4,new ComplexNumber(1,0));
    	uf.asigna(6,6,new ComplexNumber(1,0));
    	uf.asigna(7,7,new ComplexNumber(1,0));
    	hada.fill(new ComplexNumber(1/Math.sqrt(2),0));
		hada.asigna(1, 1, new ComplexNumber(-1/Math.sqrt(2),0));
		ComplexMatriz hhh=hada.tensorProduct(hada.tensorProduct(hada));
    }
}