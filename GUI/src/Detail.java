import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.geometry.*;


public class Detail {
	private static String message=("In SJF scheduling, the process with the lowest burst time, among the list of available processes in the ready queue,\n"
			+ " is going to be scheduled next.\n However, it is very difficult to predict the burst time needed for a process hence this algorithm is very difficult to implement in the system. \n Advanatges of this scheduling are \n"
			+ "High throughput \n Minimum TAT and waiting time");
	
	
	public static void display () {
    	Stage window= new Stage();
    	window.initModality(Modality.APPLICATION_MODAL);
    	window.setTitle("About SJF");
    	window.setMinWidth(500);
    	
    	Label label= new Label();
    	label.setText(message);
    	
    	Button close=new Button("Close");
    	close.setOnAction(e -> window.close());
    	
    	VBox layout = new VBox(30);
        layout.setPadding(new Insets(10,10,10,10));
    	layout.getChildren().addAll(label,close);
    	layout.setAlignment(Pos.CENTER);
    	
    	Scene scene = new Scene(layout);
    	window.setScene(scene);
    	window.showAndWait();
	}
}
