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
	

	private static ArrayList<Cineplex> cineplexList;
	

	private static File cineplexFile = new File("Cineplex.dat");

	
	private static int choice = 0;

	
	private static String inputUser;
	private static String inputPass;
	
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
	
	// Fetching infomation of al cineplexes.
	private static void fetchCineplexes(File file){
		cineplexList = new ArrayList<Cineplex>();
		try{
			FileInputStream fi = new FileInputStream(file);
			ObjectInputStream input = new ObjectInputStream(fi);
			
			try{
				while(true){
					Cineplex c = (Cineplex)input.readObject();
					cineplexList.add(c);
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
	
	private static void updateCineplexes(File file){
		try{
			FileOutputStream fo = new FileOutputStream(file);
			ObjectOutputStream output = new ObjectOutputStream(fo);
			for(Cineplex c: cineplexList){
				output.writeObject(c);
			}
			fo.close();
			output.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
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
	
	private static void Adminlogin(Scanner scanner) {
		System.out.print("Enter Username: ");										// Admin to login
		inputUser = scanner.nextLine();
		
		System.out.print("Enter Password: ");
		inputPass = scanner.nextLine();
																					// Login check
		if(!inputUser.equals(AdminModule.getUser()) || !inputPass.equals(AdminModule.getPassword())){
			System.out.println("\n============================\n"
					+ "Invalid Username or Password\n"
					+ "============================\n");							// If invalid, won't execute printAdminMenu
		} else {
			System.out.println("\n================");
			System.out.println("Successful Login");										// Successful login
			System.out.println("================\n");
			
			
			choice = 0;
			while(choice != 4){
				printAdminMenu(scanner);												// Opens Admin menu
			}
		}
	}
	
	private static void printAdminMenu(Scanner scanner){
		System.out.print("1. Create/Update/Remove movie listing\n"
				+ "2. Create/Update/Remove cinema showtimes and the movies to be shown\n"
				+ "3. Configure system settings\n"
				+ "4. Back\n"
				+ "Please enter choice: ");
		try{
			choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
			case 1:
				while(choice != 4){
					printAdminMovieMenu(scanner);
				}
				choice = 1;
				break;
			case 2:
				while(choice != 4){
					printAdminShowtimeMenu(scanner);
				}
				choice = 2;
				break;
			case 3:
				printAdminSystemConfig(scanner);
				choice = 3;
				break;
			case 4:
				System.out.println("Logout Successful");
				break;
			default:
				System.out.println("\n===========");
				System.out.println("Error Input!");
				System.out.println("===========\n");				
				break;
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Invalid input. Please re-enter choicee.");
			scanner.nextLine();
		}
	}

	private static void printAdminMovieMenu(Scanner scanner){
		System.out.print("\n=======================================================\n");
		System.out.print("                     Movies Listed                     ");
		System.out.print("\n=======================================================\n");
		int i = 1;
		for(Movie m : MovieDatabase.getArraylist()){
			System.out.println("=======================================================");
			System.out.println("Index: " + i + "\t" + m.getMovieType() + "\n");
			System.out.println("Title:\t\t" + m.getTitle());
			System.out.println("Director:\t" + m.getDirector());
			System.out.println("Cast:\t\t" + m.getCast());
			System.out.println("-------------------------------------------------------");
			System.out.println("Synopsis: ");
			System.out.println(breakLines(m.getSynopsis(), 50));
			System.out.println("=======================================================");
			i++;
		}
		System.out.print("\n=====================\n"
				+ "Movie Listing Editor\n"
				+ "=====================\n"
				+ "1. Create\n"
				+ "2. Update\n"
				+ "3. Remove\n"
				+ "4. Back\n"
				+ "Please enter choice: ");
		try{
			choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
			case 1:
				MovieDatabase.addMovie(scanner);
				break;
			case 2:
				MovieDatabase.editMovie(scanner);
				break;
			case 3:
				MovieDatabase.removeMovie(scanner);
				break;
			case 4:
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
	
	
	private static void printAdminShowtimeMenu(Scanner scanner){
		System.out.println("=======================================================");
		System.out.println("                    Cinema & Showtime                  ");
		System.out.println("=======================================================");
		
		for(Cineplex cp : cineplexList){
			System.out.println(cp.getName());
			System.out.println("-------------------------------------------------------");
			for(Cinema c : cp.getCinemaList()){
				System.out.println("\n-------------------------------------------------------");
				System.out.println(c.getCinemaID() + "\t" + c.getClassOfCinema());
				System.out.println("-------------------------------------------------------");	
				for(CinemaShow cs : c.getCinemaShowList()){
					System.out.println("Movie:\t\t" + cs.getMovie().getTitle());
					System.out.println("Showtime:\t" + cs.getShowtime());
					System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - -");
				}
			}
			System.out.println("=======================================================");
		}
		
		System.out.print("\n=====================\n"
				+ "Movie Showtime Editor\n"
				+ "=====================\n"
				+ "1. Create\n"
				+ "2. Update\n"
				+ "3. Remove\n"
				+ "4. Back\n"
				+ "Please enter choice: ");
		try{
			choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
			case 1:
				addShowTime(scanner);
				break;
			case 2:
				System.out.println("u showtime");
				break;
			case 3:
				System.out.println("r showtime");
				break;
			case 4:
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
	
	private static void addShowTime(Scanner scanner){
		int i = 1;
		int movieIndex;
		int cineplexIndex;
		int cinemaIndex;
		System.out.println("===============================");
		for(Cineplex cp : cineplexList){
			System.out.println(i + ".\t" + cp.getName());
			System.out.println("===============================");
			i++;
		}
		System.out.print("Select cineplex: ");
		cineplexIndex = scanner.nextInt();
		scanner.nextLine();
		
		i = 1;
		System.out.println("\n===============================");
		for(Cinema c : cineplexList.get(cineplexIndex-1).getCinemaList()){
			System.out.println(i + ".\t" + c.getCinemaID());
			System.out.println("===============================");
			i++;
		}
		System.out.print("Select cinema: ");
		cinemaIndex = scanner.nextInt();
		scanner.nextLine();
		
		i = 1;
		System.out.println("\n===============================");
		for(Movie m : MovieDatabase.getArraylist()){
			System.out.println(i + ".\t" + m.getTitle());
			System.out.println("===============================");
			i++;
		}
		System.out.print("Select movie: ");
		movieIndex = scanner.nextInt();
		scanner.nextLine();
		
		int month, dayOfMonth, hour, minute;
		
		System.out.print("Enter month: ");
		month = scanner.nextInt();
		scanner.nextLine();
		
		System.out.print("Enter day of month: ");
		dayOfMonth = scanner.nextInt();
		scanner.nextLine(
				);
		
		System.out.print("Enter hour: ");
		hour = scanner.nextInt();
		scanner.nextLine();
		
		System.out.print("Enter minute: ");
		minute = scanner.nextInt();
		scanner.nextLine();
		
		LocalDateTime tempDateTime = LocalDateTime.of(2017, month, dayOfMonth, hour, minute);
		
		cineplexList.get(cineplexIndex - 1).getCinemaList().get(cinemaIndex-1)
		.getCinemaShowList().add(new CinemaShow(MovieDatabase.getArraylist().get(movieIndex-1), tempDateTime));
		
		Collections.sort(cineplexList.get(cineplexIndex-1).getCinemaList().get(cinemaIndex-1).getCinemaShowList());
		
		updateCineplexes(cineplexFile);
	}
	


	private static void printAdminSystemConfig(Scanner scanner){
		System.out.print("\n=====================\n"
				+ "SYSTEM CONFIG\n"
				+ "=====================\n"
				+ "1. Change password\n"
				+ "2. Public Holidays\n"
				+ "3. Ticket Price Rate\n"
				+ "4. Back\n"
				+ "Please enter choice: ");
		try{
			choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
			case 1:
				System.out.print("Enter current password: ");
				if(scanner.nextLine().equals(AdminModule.getPassword())){
					System.out.print("Enter new password: ");
					AdminModule.setPassword(scanner.nextLine());
				}else{
					System.out.println("\n================");
					System.out.println("Invalid password");
					System.out.println("================\n");
				}
				break;
			case 2:
				while(choice!=3){
					printPublicHolidayMenu(scanner);
				}
				choice = 2;
				break;
			case 3:
				break;
			case 4:
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

	private static String breakLines(String input, int maxLineLength) {
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
