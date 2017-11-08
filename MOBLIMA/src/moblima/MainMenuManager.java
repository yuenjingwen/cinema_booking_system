package moblima;

import java.util.InputMismatchException;

import java.util.Scanner;


public class MainMenuManager {
	
	public static int choice = 0;

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		PublicHolidaysDatabase.fetchHolidays();
		CineplexDatabase.fetchCineplexes();
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
				AdminModule.Adminlogin(scanner);
				break;
			case 2:
				CineplexDatabase.getArrayList().get(0).getCinemaList().get(1).getCinemaShowList().get(0).printSeating();
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
