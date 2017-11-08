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
import java.util.Scanner;

public class PublicHolidaysDatabase {
	
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
		PublicHolidaysDatabase.phList.remove(index - 1);
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
	

	public static void printPublicHolidayMenu(Scanner scanner){
		System.out.println("\n=====================\n"
				+ "Public Holidays Settings\n"
				+ "=====================\n"
				+ "Current List of Holidays:");
		
		System.out.println("=============================================");
		System.out.println("ID\tDATE\tDESCRIPTION");
		System.out.println("=============================================");
		
		int i = 1;
		for(PublicHoliday ph : PublicHolidaysDatabase.phList){
			System.out.print(i + ".\t");
			System.out.println(ph.getDate() + "\t" + ph.getDescription());
			i++;
		}
		System.out.println("=============================================");
		
		System.out.print(""
				+ "1. Add Public Holiday\n"
				+ "2. Remove Public Holiday\n"
				+ "3. Back\n"
				+ "Please enter choice: ");
		try{
			MainMenuManager.choice = scanner.nextInt();
			scanner.nextLine();
			switch (MainMenuManager.choice) {
			case 1:
				addPH(scanner);
				break;
			case 2:
				removePH(scanner);
				break;
			case 3:
				break;
			default:
				System.out.println("\n===========");
				System.out.println("Error Input!");
				System.out.println("===========\n");
				break;
			}
		}catch(Exception e){
			System.out.println("Invalid input.");
			scanner.nextLine();
		}
	}
	
	public static void addPH(Scanner scanner){
		String date = "";
		String description;
		while(String.valueOf(date).length() != 4){
			System.out.print("Enter ph date in DDMM format: ");
			try{
				date = scanner.nextLine();
			} catch (Exception e){
				System.out.println("Please enter integers.");
			}
		}
		System.out.print("Enter description: ");
		description = scanner.nextLine();
		
		insertHoliday(description, date);
	}

	public static void removePH(Scanner scanner){

		int index;
		System.out.print("Enter ID to remove: ");
		try{
			index = scanner.nextInt();
			scanner.nextLine();
			removeHoliday(index);
		} catch (Exception e){
			System.out.println("Invalid input");
		}
	}

}
