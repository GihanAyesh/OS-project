import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class Average {
	public void createLabel() {
		 Label lastl = new Label();
	     lastl.setText("Average TAT \n  Time : \n "+ String.format("%.2f",Algorithm.getTat()) );
	     lastl.setStyle("-fx-font:14px arial; -fx-font-weight:bold;");
	     VBox avrgl = new VBox();
	     avrgl.getChildren().addAll(lastl);
	     avrgl.setPadding(new Insets(0,10,0,10));
	     avrgl.setAlignment(Pos.CENTER_LEFT);
	     
	     
	     Label lastr = new Label();
	     lastr.setText( " Average Waiting \n \t Time :  \n "+ String.format("%.2f",Algorithm.getWaitt()));
	     lastr.setStyle("-fx-font:14px arial; -fx-font-weight:bold;");
	     VBox avrgr = new VBox();
	     avrgr.getChildren().addAll(lastr);
	     avrgr.setPadding(new Insets(0,10,0,10));
	     avrgr.setAlignment(Pos.CENTER_LEFT);
	     
	     
	     GUI.border2.setRight(avrgr);
	     GUI.border2.setLeft(avrgl);
	}
}
