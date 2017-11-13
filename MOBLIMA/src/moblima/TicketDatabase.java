package moblima;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class TicketDatabase{

	private static ArrayList<Ticket> ticketList;
	private static File ticketFile = new File("Ticket.dat"); 

	
	public static void add(Ticket temp) { 
		ticketList.add(temp);	
		updateTickets();
	} 
		
		

	public static ArrayList<Ticket> getArrayList() {
		return ticketList;
	}
	
	public static void updateTickets(){
		try{
			FileOutputStream fo = new FileOutputStream(ticketFile);
			ObjectOutputStream output = new ObjectOutputStream(fo);
			for(Ticket t: ticketList){
				output.writeObject(t);
			}
			fo.close();
			output.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}	
	
	public static void fetchTickets(){
		ticketList = new ArrayList<Ticket>();
		
		try{
			FileInputStream fi = new FileInputStream(ticketFile);
			ObjectInputStream input = new ObjectInputStream(fi);
			
			try{
				while(true){
					Ticket t = (Ticket)input.readObject();
					ticketList.add(t);
				}
			} catch (EOFException e){
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			fi.close();
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
}