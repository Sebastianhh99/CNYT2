import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.sun.prism.paint.Color;

import org.jcp.*;

public class Ventana extends JFrame{
    JPanel panel;
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    public Ventana(){
        setTitle("Grafico");
        setSize(1200,900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
 
    private void init() {
        panel = new JPanel();
        getContentPane().add(panel);
        // Fuente de Datos
        /*dataset.setValue(8, "Mujeres", "Lunes");
        dataset.setValue(7, "Hombres", "Lunes");
        dataset.setValue(9, "Mujeres", "Martes");
        dataset.setValue(4, "Hombres", "Martes");
        dataset.setValue(4, "Mujeres", "Miercoles");
        dataset.setValue(5, "Hombres", "Miercoles");
        dataset.setValue(8, "Mujeres", "Jueves");
        dataset.setValue(9, "Hombres", "Jueves");
        dataset.setValue(7, "Mujeres", "Viernes");
        dataset.setValue(8, "Hombres", "Viernes");
        */// Creando el Grafico
        JFreeChart chart = ChartFactory.createBarChart
        ("Grafica","Estado","Valor",
        dataset, PlotOrientation.VERTICAL, true,true, false); 
        CategoryPlot p = chart.getCategoryPlot();
        // Mostrar Grafico
        ChartPanel chartPanel = new ChartPanel(chart);
        panel.add(chartPanel);
    }
    
    public void setData(ComplexVector v) {
    	ComplexNumber[] ve=v.getVector();
    	for(int i=0;i<ve.length;i++) {
    		dataset.setValue(ve[i].module(), Integer.toString(i), Integer.toString(i));
    	}
    	init();
    }
    
    public void setData2(ComplexVector v) {
    	ComplexNumber[] ve=v.getVector();
    	for(int i=0;i<ve.length;i++) {
    		dataset.setValue(Math.pow(ve[i].module(),2), Integer.toString(i), Integer.toString(i));
    	}
    	init();
    }
    
    public void setData3(ComplexMatriz v) {
    	ComplexNumber[][] ve= v.getMatriz();
    	for(int i=0;i<ve.length;i++) {
    		for(int j=0;j<ve[i].length;j++) {
    			dataset.setValue(Math.pow(ve[i][j].module(), 2),Integer.toString(j),Integer.toString(j));
    		}
    	}
    	init();
    }
    
    public static void main(String args[]){
        new Ventana().setVisible(true);
    }
}