

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ErrorMsg {
	public static void display (String title, String msg) {
		Stage error = new Stage();
		error.initModality(Modality.APPLICATION_MODAL);
		error.setTitle("Problem !!!");
		error.setMinWidth(300);
		error.setMinHeight(150);
		
		Label l = new Label();
		l.setText(msg);
		
		Button ok = new Button ("OK");
		ok.setOnAction(e -> error.close());
		
		VBox v = new VBox (20);
		v.getChildren().addAll(l , ok);
		v.setAlignment(Pos.CENTER);
		v.setMinSize(150, 100);
		
		Scene s  = new Scene(v);
		error.setX(200);
		error.setY(200);
		error.setScene(s);
		error.showAndWait();
	}

}
