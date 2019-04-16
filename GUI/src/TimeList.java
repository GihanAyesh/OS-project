
public class TimeList implements Comparable <TimeList> {	
	private	int id;
	private	int bur;
	private int prio;
	private int wait;
	private int tat;
	
	public TimeList (int id,int bur,int prio, int wait, int tat) {
		this.id=id;
		this.wait=wait;
		this.bur = bur;
		this.prio=prio;
		this.tat=tat;
	}
	public void setWait(int wait) {
		this.wait=wait;
	}
	
	public int getWait() {
		return this.wait;
	}
	
	public int getId() {
		return this.id;
	}
	public int getBur() {
		return this.bur;
	}
	
	public int getPrio() {
		return this.prio;
	}
	public int getTat() {
		return this.tat;
	}
	@Override
	public int compareTo(TimeList o) {
		int compare = Integer.compare(id, o.getId());
		return compare;
	}


}




