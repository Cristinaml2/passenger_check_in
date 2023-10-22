
public class PassengerThread extends Thread{
	public  int zoneNum; // zone Number
	public  int SeatNum; // seat Number
	private static long time = System.currentTimeMillis();
	
	// Constructor
	PassengerThread(int id, int zone, int seat){
		setName("Passenger "+ id);
		this.zoneNum = zone;
		this.SeatNum = seat;
	}
	public void msg(String m){
	    System.out.println("["+(System.currentTimeMillis()-time)+"] "+getName()+": "+m);
	}
	
	public void PassengerArriving() {
	int ArrivalTime = (int) (Math.random()*100);// Passengers given a random arrival time
	try{
		Thread.sleep(ArrivalTime);
		msg("arrived at airport.");
	}
	catch (InterruptedException e) {
		e.printStackTrace();
	  }
	MainClass.Arrivalqueue[MainClass.i]=this;
	 MainClass.i++;
	}
	
	
	public void run() {
		PassengerArriving();
		
	}

}
