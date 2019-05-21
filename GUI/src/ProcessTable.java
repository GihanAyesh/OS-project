
public class ProcessTable implements Comparable<ProcessTable> {
	
	private	int id;
	private static int amount=0;
	private int burst;
	private int priority;
	private boolean er;
	
	public ProcessTable() {
		ProcessTable.amount++;
		this.id=amount;
		this.burst=0;
		this.priority=0;
	}
	public ProcessTable (int burst,int priority) {
		ProcessTable.amount++;
		this.id=amount;
		this.burst=burst;
		this.priority=priority;
	}
	public void setBurst(String burst) {
		int val = Integer.parseInt(burst);
		if (val<0) {
			val=0;
		}
		this.burst=val;
	}
	public void setPriority(String priority) {
		int val = Integer.parseInt(priority);
		if (val<0) {
			val=0;
		}
		this.priority=val;
	}
	public int getBurst() {
		return this.burst;
	}
	public int getPriority() {
		return this.priority;
	}
	public int getId() {
		return this.id;
	}
	@Override
	public int compareTo(ProcessTable p) {
		int compare = Integer.compare(burst, p.getBurst());
		if (compare==0) {
			compare = -(Integer.compare(priority,  p.getPriority()));
		}
		return compare;
	}
	  

}






