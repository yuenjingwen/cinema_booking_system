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

	private static ArrayList<Ticket> TDB;
	private static File ticketFile = new File("Ticket.dat") //dat file has yet to be created

	
	public static void add(Ticket temp) { 
		TDB.add(temp);	

		/*try{														//IS DIS NECESSARY i dun think so
			FileOutputStream fo = new FileOutputStream(ticketFile);
			ObjectOutputStream output = new ObjectOutputStream(fo);
			for(Ticket ticket: TDB){
				output.writeObject(ticket);
			}
			fo.close();
			output.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		*/
		updateTickets(ticketFile);
	} 
		
		

	public static ArrayList<Ticket> getArrayList() {
		
		TDB = new ArrayList<Ticket>();
		
		try{
			FileInputStream fi = new FileInputStream(ticketFile);
			ObjectInputStream input = new ObjectInputStream(fi);
			
			try{
				while(true){
					Ticket t = (Ticket)input.readObject();
					TDB.add(t);
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
	
	public static void updateTickets(File file){
		try{
			FileOutputStream fo = new FileOutputStream(file);
			ObjectOutputStream output = new ObjectOutputStream(fo);
			for(Ticket t: TDB){
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
		
	}