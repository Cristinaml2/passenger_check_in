import java.util.Iterator;
import java.util.Random;




public class ClerkThread extends Thread{
	
	public static int CounterNumber = 3;
	private static long time = System.currentTimeMillis();
	//Constructor
	public ClerkThread(String name) {
		setName("Clerk "+ name);
	}
	
	public void msg(String m){
	    System.out.println("["+(System.currentTimeMillis()-time)+"] "+getName()+": "+m);
	}
	
	public boolean AvaliableSeat(int randSeat) {
		boolean seatIsAvalible = true;
		int j = 0;
		while(j <MainClass.NumberPass) {
			
		if(randSeat == MainClass.Arrivalqueue[j].SeatNum) {
			seatIsAvalible = false;
			break;
			}
			j++;
	     }
		return seatIsAvalible;
	}

	
	public void CheckIn() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		while(MainClass.person<MainClass.NumberPass) {
		AssignSeat();
		 if(MainClass.person+1<MainClass.NumberPass)
		AssignSeat();
		 if(MainClass.person+2<MainClass.NumberPass) {
			 AssignSeat(); 
			 msg("Please wait line is full.");
		 }
		 
		  BusyWait(MainClass.person+3,MainClass.Arrivalqueue);
		  MainClass.updateLine();
	    }
		
	}

	public void AssignSeat() {
		
		int ServiceTime = (int) (Math.random()*100);
		 Random RandNumGenerator =new Random();
		 int AssignSeat;
		 try {
			Thread.sleep(ServiceTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		 
		 AssignSeat = RandNumGenerator.nextInt(31);
		 if(AvaliableSeat(AssignSeat)== false) {
			 AssignSeat = RandNumGenerator.nextInt(31);
			 while(AvaliableSeat (AssignSeat) == false) {
			AssignSeat = RandNumGenerator.nextInt(31); 
			 }
		 }
		
		 if(MainClass.k<MainClass.NumberPass)
		 { MainClass.Arrivalqueue[MainClass.k].SeatNum = AssignSeat;
		
		 if(AssignSeat< 11)
			MainClass.Arrivalqueue[MainClass.k].zoneNum = 1;
		 else if (10<AssignSeat && AssignSeat < 21)
				MainClass.Arrivalqueue[MainClass.k].zoneNum = 2;
		 else if (20< AssignSeat && AssignSeat < 31)
				MainClass.Arrivalqueue[MainClass.k].zoneNum = 3;
		 
		 msg(MainClass.Arrivalqueue[MainClass.k].getName()+" seat number is "+ MainClass.Arrivalqueue[MainClass.k].SeatNum
				 
				+ " and zone number is " + MainClass.Arrivalqueue[MainClass.k].zoneNum);
		 
		 
		MainClass.Arrivalqueue[MainClass.k].setPriority(getPriority()+1);
		MainClass.Arrivalqueue[MainClass.k].setPriority(NORM_PRIORITY);
		 
		 MainClass.k++;
		 }
	}
	
	public void BusyWait(int wait, PassengerThread waitPpl[]) {
		while(wait < waitPpl.length) {
			try {
				waitPpl[wait].sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			wait++;
		}
	}
	
	
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CheckIn();

	}
}
