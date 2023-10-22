import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class AttendantThread extends Thread{


	public  Queue<PassengerThread> BoardingZone1;
	public  Queue<PassengerThread> BoardingZone2;
	public  Queue<PassengerThread> BoardingZone3;
	public  Queue<PassengerThread> PassengersAboard;
	public PassengerThread Depart[];
	public int groupNum=4;
	
	static AtomicBoolean SignalPlaneLeaving = new AtomicBoolean(false);
	static AtomicBoolean SignalPlaneLanded = new AtomicBoolean(false);
	AttendantThread(String n){
		setName("Flight attendent "+n);

		}
	
	private static long time = System.currentTimeMillis();
	public void msg(String m){

		   System.out.println("["+(System.currentTimeMillis()-time)+"] "+getName()+": "+m);
		}
	
	public void run() {
		starting();
		PassBoarding();
		
	}


	public void starting() {
		try {
			Thread.sleep(1000);
			} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
	
	public void PassBoarding() {
		
		PassengersAboard = new LinkedList<PassengerThread>();
		 
		msg("Passengers in zone 1 please walk up to the gate.");
		
		BoardingZone1 = new LinkedList<PassengerThread>();
		PassengerThread curr1;
		for(int i=0; i <MainClass.Arrivalqueue.length; i++) {    
			if(MainClass.Arrivalqueue[i].zoneNum == 1) {
					curr1=MainClass. Arrivalqueue[i];
					BoardingZone1.add(curr1);
				//Zone1[i]= MainClass.Arrivalqueue[i];
					//System.out.println
			}
		}
		Iterator<PassengerThread> check1 = BoardingZone1.iterator();
		   while(check1.hasNext()){
			  curr1 = check1.next();
			 if(SignalPlaneLeaving.get() == true) {
				 msg(curr1.getName()+ "rebook your flight and go home you late buddy");  
			 }
			 else {
			  msg(curr1.getName()+ " in zone 1, walks to the gate.");
			  try {
					curr1.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  PassengersAboard.add(curr1);
			 }
	}
		   
			
		   
		   msg("Passengers in zone 2 please walk up to the gate.");
		   BoardingZone2 = new LinkedList<PassengerThread>();
		   PassengerThread curr2;
		   for(int j=0; j<MainClass.Arrivalqueue.length; j++) {
			   if(MainClass.Arrivalqueue[j].zoneNum == 2) {
					curr2=MainClass. Arrivalqueue[j];
					BoardingZone2.add(curr2);
		   } 
	}
		   Iterator<PassengerThread> check2 = BoardingZone2.iterator();
		   while(check2.hasNext()){
			  curr2 = check2.next();
			  if(SignalPlaneLeaving.get() == true) {
					 msg(curr2.getName()+ "rebook your flight and go home you late buddy");  
				 }
				 else {
			  msg(curr2.getName()+ " in zone 2, walks to the gate.");
			  try {
					curr2.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  PassengersAboard.add(curr2);
				 }
	}
		  
	
		   
		   msg("Passengers in zone 3 please walk up to the gate.");
			
			BoardingZone3 = new LinkedList<PassengerThread>();
			PassengerThread curr3;
			for(int c=0; c<MainClass.Arrivalqueue.length; c++) {    
				if(MainClass.Arrivalqueue[c].zoneNum == 3) {
						curr3=MainClass. Arrivalqueue[c];
						BoardingZone3.add(curr3);
				}
			}
			Iterator<PassengerThread> check3= BoardingZone3.iterator();
			   while(check3.hasNext()){
				  curr3 = check3.next();
				  if(SignalPlaneLeaving.get() == true) {
						 msg(curr3.getName()+ " rebook your flight and go home you late buddy");  
					 }
					 else {
				  msg(curr3.getName()+ " in zone 3, walks to the gate.");
				  try {
					curr3.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				  PassengersAboard.add(curr3);
					 }
		}
			   
		  Boarding(PassengersAboard);
		  msg("Door are closing, passengers have boarded the plane.");
		  Iterator<PassengerThread> look = PassengersAboard.iterator();
		  PassengerThread aboard;
		   while(look.hasNext()){
			  aboard= look.next();
			  try {
				aboard.sleep(5000);
				if(SignalPlaneLanded.get() == true) {
			  
			   break;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  msg("Passengers wake up we have landed on WonderLand!!!!!!");
		   }
		   Depart= new PassengerThread[PassengersAboard.size()];
			departing(PassengersAboard);
			for(int p=0; p <Depart.length; p++) {
				msg(Depart[p].getName()+ " at seat "+Depart[p].SeatNum+" is leaving");
				waitToDepart(p,Depart);
			}
			msg("is cleaning the plane.");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			msg("has left the plane.");
}
	
	public void Boarding(Queue boarding) {
		  PassengerThread p;
		 Iterator<PassengerThread> check = boarding.iterator();
		   while(check.hasNext()){
			  p = check.next();
			  
			  Thread.yield();
			  Thread.yield();
			  msg(p.getName()+" is boarding the plane.");
			  if(check.hasNext()){
			  p = check.next();
			  msg(p.getName()+" is boarding the plane.");
			  }
			  if(check.hasNext()) {
			  p = check.next();
			  msg(p.getName()+" is boarding the plane.");
			  }
			  if(check.hasNext()) {
			   p = check.next();
			   msg(p.getName()+" is boarding the plane.");
			  }
			  BusyWait(p,boarding);
			    
		   }
	}
	
	public void departing(Queue onPlane) {
		Iterator<PassengerThread> queue = onPlane.iterator();
		PassengerThread x;
		int i =0;
		   while(queue.hasNext()){
			   x=queue.next();
			   Depart[i]=x;
			   i++;
		   }
		  int j=0;
		  PassengerThread hold;
		  while(j < Depart.length ) {
			  if(Depart[j]!=null) {
			  int k=j+1;
			  while(k <Depart.length) {
				  if(Depart[k]!=null) 
				 if(Depart[j].SeatNum > Depart[k].SeatNum) {
					 hold = Depart[j];
					 Depart[j]= Depart[k];
					 Depart[k] = hold;
				 }
				 k++;
			  }
			  j++;
		  }
			  }
	}
	
	
	public void waitToDepart(int n, PassengerThread arr[]) {
		while(n < arr.length) {
			try {
				arr[n].sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			n++;
		}
	}
	
	public void BusyWait(PassengerThread wait, Queue waitPpl) {
		Iterator<PassengerThread> queue = waitPpl.iterator();
		   while(queue.hasNext()){
			  wait = queue.next();
		   try {
			wait.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   }
	}
}
