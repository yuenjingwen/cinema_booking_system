package moblima;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerModule {
	DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
	Scanner scanner;
	
	static int row, column;

	//Menu
	public static void customerMain(Scanner scanner){
		
		int custChoice =0;
		
		while (custChoice!=5) {
		System.out.print("1. Buy a ticket\n"
							+ "2. Add review\n"
							+ "3. Read reviews\n"
							+ "4. See my movie history\n"
							+ "5. Back\n"
							+ "Please enter choice: ");
			try{
				custChoice = scanner.nextInt();
				scanner.nextLine();
				
				switch (custChoice) {
				case 1:
					
						buyProcess(scanner);
		
					break;
				case 2:
		
						addReview(scanner);
			
					break;
				case 3:
				
						readReview(scanner);
		
					break;
				case 4:
				
						showHistory(scanner);
						
				
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
			
			}
		
				catch(Exception e){
				e.printStackTrace();
				System.out.println("Invalid input. Please re-enter choicee.");
				scanner.nextLine();
			}
		}
		
	}
	//Process of buying
	public static void buyProcess(Scanner scanner){
		Ticket ticket= new Ticket();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm"); 
		
		//printCineplex choices********************
		CineplexDatabase.printAllShowtimes();
		
		//Choose
		int i = 1;
		int showtimeIndex;
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
		System.out.println("\n========Movie=================== Movie Screening Time=======");
		for(CinemaShow cs : CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaShowList()){
			System.out.println(i + ".\t" + cs.getMovie().getTitle() + "	" + cs.getShowtime().format(formatter));
			System.out.println("======================================================");
			i++;
		}
		System.out.print("Select showtime: ");
		showtimeIndex = scanner.nextInt();
		scanner.nextLine();
		ticket.movie=CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaShowList().get(showtimeIndex-1).getMovie().getTitle();
		

						
		ticket.time=CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaShowList().get(showtimeIndex-1).getShowtime().format(formatter);
		//implement seat
		CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaShowList().get(showtimeIndex-1).printSeating();

		String rowString="";
		
		try{
			System.out.println("Select row:");
			rowString = scanner.nextLine();
			switch(rowString){
			case "A": row=1;
				break;
			case "B": row=2;
			break;
			case "C": row=3;
			break;
			case "D": row=4;
			break;
			case "E": row=5;
			break;
			case "F": row=6;
			break;
			case "G": row=7;
			break;
			case "H": row=8;
			break;
			default: System.out.println("Error input!");
				throw new Exception();
			}		
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

		
		
		
		CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaShowList().get(showtimeIndex-1).setSeat(column, row);
		
		String tempColumn = Integer.toString(column);
		ticket.seat = rowString+ tempColumn  ;
			
			
			System.out.println("You have paid for your ticket!");
			ticket.printTicket();
			
			//need Edit this
			TicketDatabase.add(ticket);

	
	}

	
	private static void addReview(Scanner scanner) {
		int movieIndex;
		
		MovieDatabase.printMovieList();
		System.out.println("Select Movie to review");
		movieIndex = scanner.nextInt();
		scanner.nextLine();
		
		System.out.println("===================================================");
		System.out.println("Enter review below:");
		String tempReview = scanner.nextLine();
		System.out.println("===================================================");
		System.out.println("Give ratings between 1(Bad) to 5(Worth Watching!):");
		int tempRating = scanner.nextInt();
		scanner.nextLine();
		MovieDatabase.getArrayList().get(movieIndex-1).addReview(tempReview, tempRating);
		
		MovieDatabase.updateMovies();
	}
	
	//need a way to read review
	private static void readReview(Scanner scanner) {	
		MovieDatabase.printReviewList(scanner);
	}
	
	
	
	//need a way to read Ticket history
	private static void showHistory(Scanner scanner) {
		for(int i=0;  ; i++) {
			while(TicketDatabase.getArrayList().get(i) != null)
				TicketDatabase.getArrayList().get(i).printTicket();
		}
		
		//use print method in Ticket class
			
		
	}


	
	
	
	
	
	
}
