package moblima;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class TicketDatabase{

	private static ArrayList<Ticket> TDB;

	
	public static void add(Ticket temp) {
		TDB.add(temp);	
		
		File file = new File("Ticket.dat");
		try{
			FileOutputStream fo = new FileOutputStream(file);
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
	}

	public static ArrayList<Ticket> getArrayList() {
		return 0;
	}

	
}