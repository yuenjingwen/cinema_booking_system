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

public class PublicHolidaysEditor {
	
	public static ArrayList<PublicHoliday> phList = new ArrayList<PublicHoliday>();
	
	public static void fetchHolidays(){
		File file = new File("PublicHolidays.dat");
		try{
			FileInputStream fi = new FileInputStream(file);
			ObjectInputStream input = new ObjectInputStream(fi);
			
			try{
				while(true){
					PublicHoliday ph = (PublicHoliday)input.readObject();
					phList.add(ph);
				}
			} catch (EOFException e){
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			fi.close();
			input.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
	}
	
	public static void insertHoliday(String description, String date){
		phList.add(new PublicHoliday(description, date));
		File file = new File("PublicHolidays.dat");
		try{
			FileOutputStream fo = new FileOutputStream(file);
			ObjectOutputStream output = new ObjectOutputStream(fo);
			for(PublicHoliday ph: phList){
				output.writeObject(ph);
			}
			fo.close();
			output.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public static void removeHoliday(int index){
		PublicHolidaysEditor.phList.remove(index - 1);
		File file = new File("PublicHolidays.dat");
		try{
			FileOutputStream fo = new FileOutputStream(file);
			ObjectOutputStream output = new ObjectOutputStream(fo);
			for(PublicHoliday ph: phList){
				output.writeObject(ph);
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
