import java.util.Arrays;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;


public class GUI extends Application {
	
	private Stage window;
	private Scene begin,output;
	private TableView<ProcessTable> table;
	private static TableView<TimeList> timetable;
	private TextField burstVal, priorityVal ;
	private ObservableList<ProcessTable> pc = FXCollections.observableArrayList();
	public static StackedBarChart< Number,String> stackedBarChart;
	public static BorderPane border2;
	
    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start (Stage primaryStage) throws Exception  {
    	
    	window=primaryStage;
        primaryStage.setTitle("SJF Scheduler");
        
        Text text = new Text("Here is the table with the process details. Add data and press RUN to start the program \n "
    			+ "If burst value is larger than 100 value will be 100. If negetive , value will be 0 \n"
    			+ "If priority value is larger than 10 value will be 10. If negetive, value will be 0 ");
                
        Label label = new Label();
        label.setText(text.getText());
        label.setStyle("-fx-font:14px arial; -fx-text-fill:white; -fx-font-weight:bold;");
        
        HBox labelLayout = new HBox();
        labelLayout.setPadding(new Insets(10,10,10,10));
        labelLayout.getChildren().add(label);
              
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
        table.getColumns().addAll(processIDColumn, burstTimeColumn, priorityColumn);
        
        VBox tableLayout = new VBox();
        tableLayout.setPadding(new Insets(10,10,10,10));
        tableLayout.setSpacing(10);
        tableLayout.getChildren().add(table);
               
        Button inp= new Button("Input");
        inp.setOnAction(e ->addButton());
        inp.setMinWidth(80);
        inp.setMaxWidth(Double.MAX_VALUE);

        Button delete= new Button("Delete");
        delete.setOnAction(e -> deleteButton());
        delete.setMinWidth(80);
        delete.setMaxWidth(Double.MAX_VALUE);
        
        Button run= new Button("Start");
        run.setOnAction(e -> {
        	
        	Algorithm a = new Algorithm (pc);
        	a.calculate();
        	GanttChart c= new GanttChart();
        	c.createGantt();
        	a.arrange();
        	window.setScene(output);
        });
        run.setMinWidth(80);
        run.setMaxWidth(Double.MAX_VALUE);
        
        Button about= new Button("About");
        about.setOnAction(e -> Detail.display());
        about.setMinWidth(80);
        about.setMaxWidth(Double.MAX_VALUE);
                   
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
        buttonLayout.setSpacing(20);
        buttonLayout.getChildren().addAll(burstVal, priorityVal, inp,delete,run,about);
        
        BorderPane border= new BorderPane();
        border.setPadding(new Insets(10,10,10,10));
        border.setTop(labelLayout);
        border.setCenter(tableLayout);
        border.setBottom(buttonLayout);
        BackgroundSize bsize = new BackgroundSize(BackgroundSize.AUTO,BackgroundSize.AUTO,false,false,true,true);
        Image pic = new Image ("Style/tronic.jpg");
        border.setBackground(new Background(new BackgroundImage(pic,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,bsize)));
        begin = new Scene(border,643,400);
        begin.getStylesheets().add("Style/Style.css");
     
/***************************************************************************************************************************************************************************/
        
        Button bck= new Button("Back");
        bck.setOnAction(e -> {
        	timetable.getItems().clear();
        	stackedBarChart.getData().clear();
        	window.setScene(begin);});
        
        HBox topguy = new HBox();
        topguy.setPadding(new Insets(10,10,10,10));
        topguy.getChildren().add(bck);
        topguy.setAlignment(Pos.CENTER_RIGHT);
        
        TableColumn<TimeList, Integer> IDColumn = new TableColumn<>("Process ID");
        IDColumn.setMinWidth(200);
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("id")); 
        
        TableColumn<TimeList, Integer> BurColumn = new TableColumn<>("Burst Time");
        BurColumn.setMinWidth(200);
        BurColumn.setCellValueFactory(new PropertyValueFactory<>("bur")); 
        
        TableColumn<TimeList, Integer> prioColumn = new TableColumn<>("Priority");
        prioColumn.setMinWidth(200);
        prioColumn.setCellValueFactory(new PropertyValueFactory<>("prio")); 
        
        TableColumn<TimeList, Integer> waitTimeColumn = new TableColumn<>("Waiting Time");
        waitTimeColumn.setMinWidth(200);
        waitTimeColumn.setCellValueFactory(new PropertyValueFactory<>("wait")); 
        
        TableColumn<TimeList, Integer> tatTimeColumn = new TableColumn<>("TAT Time");
        tatTimeColumn.setMinWidth(200);
        tatTimeColumn.setCellValueFactory(new PropertyValueFactory<>("tat"));
        
        timetable=new TableView<>();
        timetable.getColumns().addAll(IDColumn, BurColumn, prioColumn, waitTimeColumn,tatTimeColumn);
        
        VBox tableLayout2 = new VBox();
        tableLayout2.setPadding(new Insets(10,10,10,10));
        tableLayout2.getChildren().add(timetable);
        
        CategoryAxis xAxis = new CategoryAxis();    
	    xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList("Task in CPU"))); 
	    xAxis.setLabel("Process");
	    xAxis.setMaxHeight(200);
	    xAxis.setStyle("-fx-font:14px arial; -fx-text-fill:white;");
	    NumberAxis yAxis = new NumberAxis(); 
	   	yAxis.setLabel("Time taken in the CPU");
	   	stackedBarChart = new StackedBarChart<>(yAxis, xAxis);         
	    stackedBarChart.setTitle("Gantt Chart");
	    stackedBarChart.setMaxHeight(140);
	    
       
        border2= new BorderPane();
        border2.setPadding(new Insets(10,10,10,10));
        border2.setBottom(topguy);
        border2.setCenter(tableLayout2);
        Image im=new Image("Style/tronic2.jpg");
        border2.setBackground(new Background(new BackgroundImage(im,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,bsize)));
             
        output = new Scene(border2,1270,650);
        output.getStylesheets().add("Style/Style.css");
        
        window.setScene(begin);
        window.setX(70);
        window.setY(50);
        window.show();
    }
    public void addButton() {
    	ProcessTable process= new ProcessTable();
    	process.setBurst(burstVal.getText());
    	process.setPriority(priorityVal.getText());
    	table.getItems().add(process);
    	pc.add(process);
    	burstVal.clear();
    	priorityVal.clear();
    }
    
    public void deleteButton() {
    	ObservableList<ProcessTable> selected, allProcesses;
    	allProcesses = table.getItems();
    	selected = table.getSelectionModel().getSelectedItems();
    	selected.forEach(allProcesses::remove);
    }
    public static void setL(TimeList x){
    	 timetable.getItems().add(x);
    }
    
}








