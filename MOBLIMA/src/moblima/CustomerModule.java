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
		System.out.print(	"=====================Movie-goer Menu=====================\n"
							+ "1. Search/List Movie\n"
							+ "2. View movie details- View/Write Reviews\n"
							+ "3. Check seat availability and Book/purchase Ticket\n"
							+ "4. See my movie history\n"
							+ "5. Back\n"
							+ "=======================================================\n"
							+ "Please enter choice: \n");
			try{
				custChoice = scanner.nextInt();
				scanner.nextLine();
				
				switch (custChoice) {
				case 1:
						CineplexDatabase.printAllShowtimes();
					break;
				case 2:
						//Have to list description of movie- function yet to implement
					int addOrRead=0;
					while (addOrRead != 4){
					try{
						System.out.println("=========Reviews=========\n"
										  +"1. Read reviews\n"
										  +"2. View overall reviewers' rating\n"
										  +"3. Add review\n"
										  +"4. Back\n"
										  +"=========================\n"
										  +"Do you want to read or add reviews?");
						addOrRead = scanner.nextInt();
						scanner.nextLine();
						
						}catch(Exception e){
						e.printStackTrace();
						System.out.println("Invalid input. Please re-enter choicee.");
						scanner.nextLine();
					}
					
					
						switch(addOrRead){
						case 1: readReview(scanner);
						break;
						case 2: printAverageRating(scanner);
						break;
						case 3: addReview(scanner);
						break;
						}
					}
					break;
				case 3:
					buyProcess(scanner);
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
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");	
		int i = 1; //Choose
		int showtimeIndex;
		int cineplexIndex;
		int cinemaIndex;
		int timeIndex;
		int age = 0;
		int mobileNumber;
		String name;
		String emailAddress;
		String seat;
		
		
		//Get Name
		try{
			System.out.println("Enter Name:");
			name = scanner.nextLine();
			
			}catch(Exception e){
			e.printStackTrace();
			System.out.println("Invalid input. Please re-enter choicee.");
			scanner.nextLine();
		}
		//Get mobile number
		try{
			System.out.println("Enter Mobile Number:");
			mobileNumber = scanner.nextInt();
			scanner.nextLine();
			
			}catch(Exception e){
			e.printStackTrace();
			System.out.println("Invalid input. Please re-enter choicee.");
			scanner.nextLine();
		}
		//Get email address
		try{
			System.out.println("Enter email address:");
			emailAddress = scanner.nextLine();
			
			}catch(Exception e){
			e.printStackTrace();
			System.out.println("Invalid input. Please re-enter choicee.");
			scanner.nextLine();
		}
		//Get age
		try{
			System.out.println("Enter age:");
			age = scanner.nextInt();
			scanner.nextLine();
			}catch(Exception e){
			e.printStackTrace();
			System.out.println("Invalid input. Please re-enter choicee.");
			scanner.nextLine();
		}
		
		//printCineplex choices********
		CineplexDatabase.printAllShowtimes();
		
		System.out.print("Select cineplex: ");
		
		cineplexIndex = scanner.nextInt();
		scanner.nextLine();
		
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
		seat = rowString+ tempColumn  ;
			
			
			
			
			//need Edit this
			Ticket ticket= new Ticket(seat, age, cinemaIndex, cineplexIndex, showtimeIndex);
			System.out.println("You have paid for your ticket!");
			ticket.printTicket();
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
	
	private static void printAverageRating(Scanner scanner) {
		
		MovieDatabase.printMovieList();
		System.out.print("Which movie's average rating would you like to view?");	//ask which movie user wants to see avg rating of
		int choice = scanner.nextInt();
		
		MovieDatabase.getArrayList().get(choice-1).printAvgRating();
		
		
	}


	private static void showHistory(Scanner scanner) {
		for(int i=0;  ; i++) {
			while(TicketDatabase.getArrayList().get(i) != null)
				TicketDatabase.getArrayList().get(i).printTicket();
		}
	}
		


	
	
	
	
	
	
}