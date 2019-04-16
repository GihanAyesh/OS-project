
import java.util.ArrayList;
import java.util.Collections;
import javafx.collections.ObservableList;


public class Algorithm {
	private static ObservableList<ProcessTable> items;
	private static ArrayList <TimeList> times; 
	private static double tat;
	private static double waitt;
	
	public Algorithm(ObservableList items) {
		this.items=items;
		times= new ArrayList<TimeList>();
	}
	
	public void calculate() {
		Collections.sort(items);
		double size=items.size();
		int turn =0;
		int wait=0;
		int totalturn =0;
		int totalwait=0;
		int val=0;
		for (ProcessTable p: items) {
			val=p.getBurst();
			wait=turn;
			turn=val+turn;
			totalwait+=wait;
			totalturn+=turn;
			times.add(new TimeList(p.getId(),p.getBurst(), p.getPriority(), wait,turn));
		tat= totalturn/size;
		waitt=totalwait/size;
		}
	
	    
	}

	public static double getTat() {
		return tat;
	}


	public static double getWaitt() {
		return waitt;
	}
	public static ArrayList getList() {
		return times;
	}
	public void arrange() {
		Collections.sort(times);
		for (TimeList k:times) {
			GUI.setL(k);
			
		}
		
	}

}
