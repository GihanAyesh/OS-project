import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.geometry.Insets;
import javafx.scene.layout.*;


public class GUI extends Application {
	
	private Stage window;
	private Scene begin,output;
	private String text = ("Here is the table with the process details. Add data and press RUN to start the program \n "
			+ "If burst value is largr than 100 value will be 100. If negetive , value will be 0. \n"
			+ "If priority value is larger than 10 value will be 10. If negetive, value will be 0 ");
	
	private TableView<ProcessTable> table;
	private TextField burstVal, priorityVal ;
	
    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start (Stage primaryStage) throws Exception  {
    	window=primaryStage;
        primaryStage.setTitle("SJF Scheduler");
        
        Label label = new Label();
        label.setText(text);
        
        HBox labelLayout = new HBox();
        labelLayout.setPadding(new Insets(10,10,10,10));
        labelLayout.getChildren().add(label);
       
/***********************************************************************************************************************************************************************/
        
        TableColumn<ProcessTable, Integer> processIDColumn = new TableColumn<>("Process ID");
        processIDColumn.setMinWidth(200);
        processIDColumn.setCellValueFactory(new PropertyValueFactory<>("id")); 
        
        TableColumn<ProcessTable, Integer> burstTimeColumn = new TableColumn<>("Burst Time");
        burstTimeColumn.setMinWidth(200);
        burstTimeColumn.setCellValueFactory(new PropertyValueFactory<>("burst")); 
        
        TableColumn<ProcessTable, Integer> priorityColumn = new TableColumn<>("Priority");
        priorityColumn.setMinWidth(200);
        priorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority")); 
        
        table=new TableView<>();
        table.setItems(addItem());
        table.getColumns().addAll(processIDColumn, burstTimeColumn, priorityColumn);
        
        VBox tableLayout = new VBox();
        tableLayout.setPadding(new Insets(10,10,10,10));
        tableLayout.getChildren().add(table);
       
 /************************************************************************************************************************************************************************/
        
        Button inp= new Button("Input");
        inp.setOnAction(e ->addButton());
        inp.setMinWidth(40);
        inp.setMaxWidth(100);

        Button delete= new Button("Delete");
        delete.setOnAction(e -> deleteButton());
        delete.setMinWidth(40);
        delete.setMaxWidth(100);
        
        Button run= new Button("Start");
        run.setOnAction(e -> window.setScene(output));
        run.setMinWidth(40);
        run.setMaxWidth(100);
        
        Button about= new Button("About SJF");
        about.setOnAction(e -> Detail.display());
        about.setMinWidth(40);
        about.setMaxWidth(100);
        
 /*****************************************************************************************************************************************************************************/       
        
        burstVal = new TextField();
        burstVal.setPromptText("Burst Time");
        burstVal.setMinWidth(40);
        burstVal.setMaxWidth(120);
        
        priorityVal = new TextField();
        priorityVal.setPromptText("Priority Value");
        priorityVal.setMinWidth(40);
        priorityVal.setMaxWidth(120);
        
        
        HBox buttonLayout = new HBox();
        buttonLayout.setPadding(new Insets(10,10,10,10));
        buttonLayout.setSpacing(30);
        buttonLayout.getChildren().addAll(burstVal, priorityVal, inp,delete,run,about);
        
        BorderPane border= new BorderPane();
        border.setPadding(new Insets(10,10,10,10));
        border.setTop(labelLayout);
        border.setCenter(tableLayout);
        border.setBottom(buttonLayout);
        
        
        begin = new Scene(border,643,400);
        
/***************************************************************************************************************************************************************************/
        
        Button bck= new Button("Back");
        bck.setOnAction(e -> window.setScene(begin));
        
        StackPane secondLayout = new StackPane();
        secondLayout.getChildren().add(bck);
        
        output = new Scene(secondLayout,700,400);
        
        window.setScene(begin);
        window.show();
    }
    
    public ObservableList<ProcessTable> addItem(){
    	ObservableList<ProcessTable> processes = FXCollections.observableArrayList();
    	processes.add(new ProcessTable(50,1));
    	processes.add(new ProcessTable(100,2));
    	processes.add(new ProcessTable(100,3));
    	processes.add(new ProcessTable(1000,1));
    	return processes;
    }
    
    public void addButton() {
    	ProcessTable process= new ProcessTable();
    	process.setBurst(burstVal.getText());
    	process.setPriority(priorityVal.getText());
    	table.getItems().add(process);
    	burstVal.clear();
    	priorityVal.clear();
    }
    
    public void deleteButton() {
    	ObservableList<ProcessTable> selected, allProcesses;
    	allProcesses = table.getItems();
    	selected = table.getSelectionModel().getSelectedItems();
    	selected.forEach(allProcesses::remove);
    }
    
    
}








