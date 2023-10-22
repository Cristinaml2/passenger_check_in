
public class ClockThread extends Thread{

	private static long time = System.currentTimeMillis();
	
	ClockThread(String n){
		setName("Clock"+n);
	}
	
	public void msg(String m){
	    System.out.println("["+(System.currentTimeMillis()-time)+"] "+getName()+": "+m);
	}
	
	
	
	public void run() {
		
		starting();
	}
	
	
	public void starting() {
		try {
			Thread.sleep(5000);
			AttendantThread .SignalPlaneLeaving.set(true);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		msg(getName()+" Thread has started ");

	
	try {
		Thread.sleep(12000);
		AttendantThread .SignalPlaneLanded.set(true);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	msg(getName()+" Plane has landed. ");
}
		
	}


