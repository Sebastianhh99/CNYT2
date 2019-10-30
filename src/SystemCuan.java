import javax.swing.JOptionPane;

public class SystemCuan {

	public SystemCuan() {}

	//Recibe ket retora el angulo y la fase
	public static  double[] anguloYFase(ComplexVector ket){
		double r=Math.sqrt(3)/(2*Math.sqrt(2));
		double i=1/Math.sqrt(2);
		ComplexNumber a=new ComplexNumber(r,i);
		//System.out.println(a.getPolar()[0]);
		System.out.println(a.getPolar()[1]);
		return null;
	}

	public static ComplexVector dinamicTime(ComplexMatriz m,ComplexVector v,int ticks) {
		for(int i=0;i<ticks;i++) {
			try {
				v=m.product(v);
			} catch (ComplexException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
		return v;
	}
	
	public static ComplexVector assembledSystems(ComplexMatriz a,ComplexMatriz b,ComplexVector ia,ComplexVector ib,int ticks) {
		ComplexMatriz c=a.tensorProduct(b);
		ComplexVector ic=ComplexVector.turn(ComplexMatriz.turn(ia).tensorProduct(ComplexMatriz.turn(ib)));
		for(int i=0;i<ticks;i++) {
			try {
				ic=c.product(ic);
			} catch (ComplexException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
		return ic;
	}
	
	public static ComplexVector slits(int nRendijas,int nBlancos,ComplexVector v) {
		int nf=1+nRendijas+((nRendijas+1)*nBlancos+nRendijas);
		ComplexVector init=new ComplexVector(nf);
		ComplexMatriz m=new ComplexMatriz(nf,nf);		
		m.fill(new ComplexNumber(0,0));
		init.fill(new ComplexNumber(0,0));
		init.asigna(0, new ComplexNumber(1,0));
		for(int i=1;i<=nRendijas;i++) {
			m.asigna(i, 0, new ComplexNumber(Math.sqrt(1/Double.parseDouble(Integer.toString(nRendijas))),0));
		}
		int in=0;
		for(int n=1;n<=nRendijas;n++) {
			if(n==1)  in=nRendijas+1;
			for(int i=0;i<2*nBlancos+1;i++) {
				m.asigna(in, n, v.getVector()[i]);
				in++;
			}
			in=in-nBlancos;
		}
		for(int i=0;i<nf;i++) {
			for(int j=0;j<nf;j++) {
				if(i==j && i>nRendijas) {
					m.asigna(i, j, new ComplexNumber(1,0));
				}
			}
		}
		for(int i=0;i<2;i++) {
			try {
				init=m.product(init);
			} catch (ComplexException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
		return init;
	}
	
	public double[] particulaEnUnaRecta(int n,ComplexVector init) {
		ComplexNumber vector[]=init.getVector();
		double[] ans=new double[vector.length];
		for(int i=0;i<vector.length;i++) {
			ans[i]=Math.pow(vector[i].module(), 2)/Math.pow(init.norm(), 2);
		}
		return ans;
	}
	
	public ComplexNumber valorEsperado(ComplexMatriz omega,ComplexVector psi) {
		ComplexNumber npsi=new ComplexNumber(1/psi.norm(),0);
		ComplexVector psiNo=psi.escalarProduct(npsi);
		try {
			ComplexVector r=omega.product(psiNo);
			return r.internalProducto(psiNo);
		} catch (ComplexException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return new ComplexNumber(0,0);
	}
	
	public ComplexNumber varianza(ComplexMatriz omega,ComplexVector psi) {
		ComplexNumber npsi=new ComplexNumber(1/psi.norm(),0);
		ComplexVector psiNo=psi.escalarProduct(npsi);
		ComplexMatriz delta=delta(omega,psiNo);
		try {
			return valorEsperado(delta.product(delta),psi);
		} catch (ComplexException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return null;
	}
	
	private static ComplexMatriz delta(ComplexMatriz omega,ComplexVector psi) {
		SystemCuan sys = new SystemCuan();
		ComplexNumber a=sys.valorEsperado(omega,psi);
		try {
			return omega.plus(identidad(omega).escalarProduct(a).escalarProduct(new ComplexNumber(-1,0)));
		} catch (ComplexException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return null;
	}
	
	private static ComplexMatriz identidad(ComplexMatriz m) {
		ComplexMatriz ans=new ComplexMatriz(m.getMatriz().length,m.getMatriz()[0].length);
		ans.fill(new ComplexNumber(0,0));
		for(int i=0;i<ans.getMatriz().length;i++) {
			for(int j=0;j<ans.getMatriz()[0].length;i++) {
				if(i==j) ans.asigna(i, j, new ComplexNumber(1,0));
			}
		}
		return ans;
	}
	
	public ComplexNumber[] calcularObservable(ComplexMatriz omega,ComplexVector psi) {
		ComplexNumber[] ans= new ComplexNumber[2];
		ans[0]=this.valorEsperado(omega, psi);
		//ans[1]=this.varianza(omega,psi);
		return ans;
	}
	
	public static void main(String args[]) {
		SystemCuan sys=new SystemCuan();
		/*ComplexNumber[] aarr= {new ComplexNumber(2,-1),new ComplexNumber(-1.5,2.5),new ComplexNumber(-3.5,5),new ComplexNumber(-4,6),
				new ComplexNumber(-3.5,2.5),new ComplexNumber(0,0),new ComplexNumber(-3.5,2.5),new ComplexNumber(6,-4),new ComplexNumber(0,2.5),
				new ComplexNumber(-1,1)};
		ComplexVector a=new ComplexVector(aarr);
		double[] aans= sys.particulaEnUnaRecta(10,a);
		for(int i=0;i<aans.length;i++) {
			System.out.println(aans[i]);
		}*/
		ComplexNumber[][] omegarr= {{new ComplexNumber(0,0),new ComplexNumber(0,-1/2.),new ComplexNumber(0,-1),new ComplexNumber(-7/2.,0)},
				{new ComplexNumber(0,1/2.),new ComplexNumber(0,0),new ComplexNumber(7/2.,0),new ComplexNumber(0,-1)},
				{new ComplexNumber(0,1),new ComplexNumber(7/2.,0),new ComplexNumber(0,0),new ComplexNumber(0,-1/2.)},
				{new ComplexNumber(-7/2.,0),new ComplexNumber(0,1),new ComplexNumber(0,1/2.),new ComplexNumber(0,0)}};
		ComplexNumber[] psiarr= {new ComplexNumber(-2,1),new ComplexNumber(1,0),new ComplexNumber(0,-1),new ComplexNumber(3,2)};
		ComplexMatriz omega=new ComplexMatriz(omegarr);
		ComplexVector psi=new ComplexVector(psiarr);
		ComplexNumber[] ans=sys.calcularObservable(omega, psi);
		System.out.println(ans[0].getnumber());
		//System.out.println(ans[1].getnumber());
	}
	
	/*public static void main(String args[]) {
		ComplexVector v=null;
		ComplexMatriz F1=new ComplexMatriz(13,13);
		F1.fill(new ComplexNumber(0,0));
		F1.asigna(9, 0, new ComplexNumber(1,0));
		F1.asigna(8, 1, new ComplexNumber(1,0));
		F1.asigna(3, 2, new ComplexNumber(1,0));
		F1.asigna(4, 3, new ComplexNumber(1,0));
		F1.asigna(7, 4, new ComplexNumber(1,0));
		F1.asigna(6, 5, new ComplexNumber(1,0));
		F1.asigna(5, 6, new ComplexNumber(1,0));
		F1.asigna(11, 7, new ComplexNumber(1,0));
		F1.asigna(10, 8, new ComplexNumber(1,0));
		F1.asigna(6, 9, new ComplexNumber(1,0));
		F1.asigna(1, 10, new ComplexNumber(1,0));
		F1.asigna(2, 11, new ComplexNumber(1,0));
		F1.asigna(0, 12, new ComplexNumber(1,0));
		ComplexNumber[] F1arr= {new ComplexNumber(10,0),new ComplexNumber(4,0),new ComplexNumber(1,0),new ComplexNumber(7,0),new ComplexNumber(2,0)
				,new ComplexNumber(2,0),new ComplexNumber(11,0),new ComplexNumber(0,0),new ComplexNumber(3,0),new ComplexNumber(1,0),new ComplexNumber(0,0)
				,new ComplexNumber(5,0),new ComplexNumber(2,0)};
		ComplexVector F1V=new ComplexVector(F1arr);
		//Para hacer el dinamico toca aca y hacer setData
		//v=dinamicTime(F1,F1V,25);
		ComplexMatriz F21=new ComplexMatriz(4,4);
		ComplexMatriz F22=new ComplexMatriz(3,3);
		F21.fill(new ComplexNumber(0,0));
		F22.fill(new ComplexNumber(0,0));
		F21.asigna(1,0, new ComplexNumber(0.3,0));
		F21.asigna(3,0, new ComplexNumber(0.3,0));
		F21.asigna(2,0, new ComplexNumber(0.4,0));
		F21.asigna(1,1, new ComplexNumber(0.2,0));
		F21.asigna(0,1, new ComplexNumber(0.2,0));
		F21.asigna(3,1, new ComplexNumber(0.3,0));
		F21.asigna(2,1, new ComplexNumber(0.3,0));
		F21.asigna(2,2, new ComplexNumber(0.2,0));
		F21.asigna(0,2, new ComplexNumber(0.3,0));
		F21.asigna(1,2, new ComplexNumber(0.1,0));
		F21.asigna(3,2, new ComplexNumber(0.4,0));
		F21.asigna(1,3, new ComplexNumber(0.4,0));
		F21.asigna(0,3, new ComplexNumber(0.5,0));
		F21.asigna(2,3, new ComplexNumber(0.1,0));
		F22.asigna(1, 0, new ComplexNumber(1/3.,0));
		F22.asigna(2, 0, new ComplexNumber(2/3.,0));
		F22.asigna(0, 1, new ComplexNumber(1/6.,0));
		F22.asigna(1, 1, new ComplexNumber(1/2.,0));
		F22.asigna(2, 1, new ComplexNumber(1/3.,0));
		F22.asigna(0, 2, new ComplexNumber(5/6.,0));
		F22.asigna(1, 2, new ComplexNumber(1/6.,0));
		ComplexNumber[] F21arr= {new ComplexNumber(0.2,0),new ComplexNumber(0.1,0),new ComplexNumber(0.6,0),new ComplexNumber(0.1,0)};
		ComplexVector F21V=new ComplexVector(F21arr);
		ComplexNumber[] F22arr= {new ComplexNumber(0.7,0),new ComplexNumber(0.15,0),new ComplexNumber(0.15,0)};
		ComplexVector F22V=new ComplexVector(F22arr);
		//Para hacer el assembled system toca aca y hacer setData
		//v=assembledSystems(F21,F22,F21V,F22V,5);
		double r=1/Math.sqrt(22);
		ComplexNumber[] F3arr= {new ComplexNumber(r,r),new ComplexNumber(-r,-r),new ComplexNumber(-r,r),new ComplexNumber(-r,-r),
				new ComplexNumber(r,-r),new ComplexNumber(-r,-r),
				new ComplexNumber(-r,-r),new ComplexNumber(-r,-r),
				new ComplexNumber(r,-r),new ComplexNumber(r,-r),new ComplexNumber(-r,r)};
		ComplexVector F3=new ComplexVector(F3arr);
		//Para hacer la doble rendija toca aca y poner setData2
		//v=slits(2,5,F3);
		Ventana a=new Ventana();
		a.setData(v);
		//a.setData2(v);
		a.setVisible(true);
	}*/
}
