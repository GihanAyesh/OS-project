import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

public class GanttChart {
	
	public void createGantt() {
		
	    ArrayList <TimeList> gnt = Algorithm.getList();
	    for (TimeList t: gnt) {
	        XYChart.Series<Number,String> series1 = new XYChart.Series<>();
	        series1.setName(Integer.toString(t.getId())); 
	        series1.getData().add(new XYChart.Data<>( t.getBur(),"Task in CPU")); 
	        GUI.stackedBarChart.getData().add(series1);
	        
	    }
	    
 
        
        VBox chart = new VBox();
        chart.getChildren().add(GUI.stackedBarChart);
        chart.setPadding(new Insets(10,10,10,10));
        GUI.border2.setTop(chart);
        
        Average av = new Average();
    	av.createLabel();
    }
}
       
