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
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;

public class MainMenuManager {
	



	
	public static int choice = 0;

	

	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		PublicHolidaysEditor.fetchHolidays();
		fetchCineplexes(cineplexFile);
		MovieDatabase.fetchMovies();
		
		choice = 0;
		
			while(choice != 3){
				try {
				printMainMenu(scanner);
				} catch (InputMismatchException e) {
					System.out.println("Invalid Input! Please enter choice from 1-3.");
					scanner.nextLine();
				}
			}
		
		
		System.out.println("Scanner closed. Program properly terminated.");
		scanner.close();
	}
	
	
	private static void printMainMenu(Scanner scanner){
		System.out.print("1. Admin\n"
				+ "2. Customer\n"
				+ "3. Exit\n"
				+ "Please enter choice: ");
		try{
			choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
			case 1:
				Adminlogin(scanner);
				break;
			case 2:
				cineplexList.get(0).getCinemaList().get(1).getCinemaShowList().get(0).printCinema();
				break;
			case 3:
				System.out.println("Program Terminating...");								// Ends program
				break;
			default:
				System.out.println("\n===========");
				System.out.println("Error Input!");
				System.out.println("===========\n");
				break;
			}
		}catch(Exception e){
			System.out.println("Invalid Input! Please enter choice from 1-3.");
			scanner.nextLine();
		}
	}
	
	private static void printPublicHolidayMenu(Scanner scanner){
		System.out.println("\n=====================\n"
				+ "Public Holidays Settings\n"
				+ "=====================\n"
				+ "Current List of Holidays:");
		
		System.out.println("=============================================");
		System.out.println("ID\tDATE\tDESCRIPTION");
		System.out.println("=============================================");
		
		int i = 1;
		for(PublicHoliday ph : PublicHolidaysEditor.phList){
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
			choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
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
	
	private static void addPH(Scanner scanner){
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
		
		PublicHolidaysEditor.insertHoliday(description, date);
	}

	private static void removePH(Scanner scanner){

		int index;
		System.out.print("Enter ID to remove: ");
		try{
			index = scanner.nextInt();
			scanner.nextLine();
			PublicHolidaysEditor.removeHoliday(index);
		} catch (Exception e){
			System.out.println("Invalid input");
		}
	}

	public static String breakLines(String input, int maxLineLength) {
	    String[] tokens = input.split("\\s+");
	    StringBuilder output = new StringBuilder(input.length());
	    int lineLen = 0;
	    for (int i = 0; i < tokens.length; i++) {
	        String word = tokens[i];

	        if (lineLen + (" " + word).length() > maxLineLength) {
	            if (i > 0) {
	                output.append('\n');
	            }
	            lineLen = 0;
	        }
	        if (i < tokens.length - 1 && (lineLen + (word + " ").length() + tokens[i + 1].length() <=
	                maxLineLength)) {
	            word += " ";
	        }
	        output.append(word);
	        lineLen += word.length();
	    }
	    return output.toString();
	}
	
	
	
	
	
	
	
	
	
	
}
