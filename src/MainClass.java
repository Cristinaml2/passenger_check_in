import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class MainClass {

	// Threads
	public static Queue<PassengerThread> queue = new LinkedList<PassengerThread>(); 
	public static PassengerThread Arrivalqueue[]; 
	public static PassengerThread passenger;
	public static Iterator<PassengerThread> check ;
	public static int  NumberPass;
	public static int i=0;
	public static int k=0;
	public static int person=0;
	
	public static void main(String[] args) {
	if   (args.length > 0) {// check if length of arguments array is > 0
        try {
       	NumberPass = Integer.parseInt(args[0]); } //set NumPass to position 0 in args array
            catch (Exception e) {
            	System.out.println("Command Line Argument must be an integer");
           	e.printStackTrace(); }
       }  else
		NumberPass = 30;
	
	//Create Threads
	for(int j=0; j<NumberPass; j++) {
		passenger = new PassengerThread(j+1,-1,-1);
		queue.add(passenger);
	}
	Arrivalqueue = new PassengerThread [NumberPass];
    ClerkThread C1= new ClerkThread("1");
    ClerkThread C2= new ClerkThread("2");
    
    Iterator<PassengerThread> iterator = queue.iterator();
    while(iterator.hasNext()){
      passenger = iterator.next();
      passenger.start();
    }
    
    AttendantThread FA =new AttendantThread(" ");
    ClockThread clock = new ClockThread(" ");

	 C1.start();
	 C2.start();
	

	 
	 try {
			C1.join();
			C2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	 FA.start();
	 clock.start();
	}
	public static void updateLine()
	{
		person+=3;
	}

}