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

public class CustomerModule {
	Scanner scanner;
	public static Ticket ticket= new Ticket();
	static int row, column;
	
	private static ArrayList<Cineplex> cineplexList;
	private static File cineplexFile = new File("Cineplex.dat");

	
	
	//Menu
	public static void customerMain(Scanner scanner){
			System.out.print("1. Select a Cineplex\n"
					+ "2. Add review\n"
					+ "3. Read reviews\n"
					+ "4. See my movie history\n"
					+ "5. Back\n"
					+ "Please enter choice: ");
			try{
				MainMenuManager.choice = scanner.nextInt();
				scanner.nextLine();
				switch (MainMenuManager.choice) {
				case 1:
					while(MainMenuManager.choice != 5){
						buyProcess(scanner);
					}
					break;
				case 2:
					while(MainMenuManager.choice != 5){
						addReview(scanner);
					}
					break;
				case 3:
					while(MainMenuManager.choice != 5){
						readReview(scanner);
					}
					break;
				case 4:
					while(MainMenuManager.choice != 5){
						showHistory(scanner);
					}
					break;
				case 5:
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
	//Process of buying
	public static void buyProcess(Scanner scanner){
		
		//printCineplex choices********************
		CineplexDatabase.printAllShowtimes();
		
		//Choose
		int i = 1;
		int movieIndex;
		int cineplexIndex;
		int cinemaIndex;
		int timeIndex;
		
		System.out.print("Select cineplex: ");
		
		cineplexIndex = scanner.nextInt();
		scanner.nextLine();
		
		ticket.cineplex=CineplexDatabase.cineplexList.get(cineplexIndex -1).getName();
		
		i = 1;
		System.out.println("\n===============================");
		for(Cinema c : CineplexDatabase.cineplexList.get(cineplexIndex-1).getCinemaList()){
			System.out.println(i + ".\t" + c.getCinemaID());
			System.out.println("===============================");
			i++;
		}
		System.out.print("Select cinema: ");
		cinemaIndex = scanner.nextInt();
		scanner.nextLine();
		ticket.cinema=CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaID();
		
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
		ticket.movie=CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaShowList().get(movieIndex-1).getMovie().getTitle();
		/*i = 1;
		System.out.println("\n===============================");
		for(Cinema c : CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaShowList().get(movieIndex-1).getShowtime()){
			System.out.println(i + ".\t" + c.getCinemaID());
			System.out.println("===============================");
			i++;
		}*/
		
		System.out.print("Select time: ");
		timeIndex = scanner.nextInt();
		scanner.nextLine();
		ticket.time=CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaShowList().get(movieIndex-1).getShowtime().toString();
		//implement seat
		CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaShowList().get(cinemaIndex-1).printSeating();
		//ticket.seat=CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaShowList().get(movieIndex-1).getMovie().getTitle();
		
		
		

		/*//Choose cineplex**************************	

			try{
				System.out.println("Select row:")
				row = scanner.nextInt();
				scanner.nextLine();
						
				}catch(Exception e){
				e.printStackTrace();
				System.out.println("Invalid input. Please re-enter choicee.");
				scanner.nextLine();
			}
			try{
				System.out.println("Select column:");
				column = scanner.nextInt();
				scanner.nextLine();
						
				}catch(Exception e){
				e.printStackTrace();
				System.out.println("Invalid input. Please re-enter choicee.");
				scanner.nextLine();
			}
			String tempRow = Integer.toString(row);
			String tempColumn = Integer.toString(column);
			ticket.seat = tempRow+ tempColumn  ; */
			
			
			System.out.println("You have paid for your ticket!");
			ticket.printTicket();
			
			//need Edit this
			TicketDatabase.add(ticket);
	
	}
	
	private static void addReview(Scanner scanner) {
		System.out.println("===================================================");
		System.out.println("Enter review below:");
		String tempReview = scanner.nextLine();
		System.out.println("===================================================");
		System.out.println("Give ratings between 1(Bad) to 5(Worth Watching!):");
		int tempRating = scanner.nextInt();
		scanner.nextLine();
		ReviewDatabase.addReview(tempReview, tempRating);
	}
	
	//need a way to read review
	private static void readReview(Scanner scanner) {
			ReviewDatabase reviewDB = new ReviewDatabase();
			for(Review r : MovieDatabase.getArrayList()){
			
			System.out.println("Rating		" + );
			System.out.println("Review:		" + );
			System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - -");
		}
		
	}
	
	//need a way to read Ticket history
	private static void showHistory(Scanner scanner) {
		// TODO Auto-generated method stub
		
	}


	
	
	
	
	
	
}
