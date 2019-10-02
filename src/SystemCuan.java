import javax.swing.JOptionPane;

public class SystemCuan {

	public SystemCuan() {}
	
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
		ComplexMatriz ic=ComplexMatriz.turn(ia).tensorProduct(ComplexMatriz.turn(ib));
		for(int i=0;i<ticks;i++) {
			try {
				ic=c.product(ic);
			} catch (ComplexException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
		return ComplexVector.turn(ic);
	}
	
	public static ComplexVector slits(int nRendijas,int nBlancos) {
		ComplexMatriz m=new ComplexMatriz(1+nRendijas+nRendijas*nBlancos,1+nRendijas+nRendijas*nBlancos);		
		return new ComplexVector(5);
	}
	
	public static void main(String args[]) {
		
	}
}
