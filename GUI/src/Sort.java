import java.util.Comparator;

public class Sort implements Comparator<TimeList> {
	@Override
	  public int compare(TimeList t1, TimeList t2) {
	    return t1.getId() - t2.getId();
	}

}
