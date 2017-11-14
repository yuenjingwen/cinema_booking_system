package moblima;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

import moblima.CinemaSeat.SeatOccupiedException;

public class CustomerModule {
	DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
	Scanner scanner;
	
	static int row, column;

	//Menu
public static void customerMain(Scanner scanner){
		
		int custChoice =0;
		
		while (custChoice!=8) {
		System.out.print(	"=====================Movie-goer Menu=====================\n"
							+ "1. Search/List Movie\n"
							+ "2. View movie details- View/Write Reviews\n"
							+ "3. Check seat availability\n"
							+ "4. Book and purchase tickets\n"
							+ "5. See my booking history\n"
							+ "6. List top 5 movies by sales\n"
							+ "7. List top 5 movies by rating\n"
							+ "8. Back\n"
							+ "=======================================================\n"
							+ "Please enter choice: \n");
			try{
				custChoice = scanner.nextInt();
				scanner.nextLine();
				
				switch (custChoice) {
				case 1:
					customerSearchAndListMovies(scanner);
					break;
				case 2:
					customerReviewMenu(scanner);
					break;
				case 3:
					customerCheckSeatAvailability(scanner);
					break;
				case 4:
					customerBuyProcess(scanner);
					break;
				case 5:
					customerShowHistory(scanner);
					break;
				case 6:
					customerPrintTop5Sales(scanner);
					break;
				case 7:
					customerPrintTop5Ratings(scanner);
					break;
				case 8 :
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
				System.out.println("Invalid input. Please re-enter choice.");
				scanner.nextLine();
			}
		}
		
	}
	
private static void customerSearchAndListMovies(Scanner scanner) {
	
		int choice = 0;
		
		while (choice != 3) {
			try{
				System.out.println("=========Movie Menu=========\n"
								  +"1. View Movie List\n"
								  +"2. Search for movie\n"
								  +"3. Back\n"
								  +"=========================\n"
								  +"Enter choice:");
				choice = scanner.nextInt();
				scanner.nextLine();
				}catch(Exception e){
					System.out.println("Invalid input. Please re-enter choice.");
					scanner.nextLine();
			}
		
			switch(choice) {
			case 1:
				MovieDatabase.printFullMovieList();
				break;
			case 2:
				System.out.println("Please enter movie to search:");
				String tempMovie = scanner.nextLine();
				
				for (Movie m: MovieDatabase.getArrayList()) {				
					
					if (tempMovie.equals(m.getTitle())) {			
						System.out.println("Movie found!");
						MovieDatabase.printSingleMovieDetails(m);
						break;
					}
				}
				
				System.out.println("Movie not found.");
				break;
			case 3:
				break;
			default: 
				System.out.println("Invalid input entered. Please reenter choice.");
			}
		}
		
	}

private static void customerCheckSeatAvailability(Scanner scanner) {
	
	CineplexDatabase.printAllShowtimes();
	
	System.out.println("Which showtime do you want to check seat availability for?");
	
	boolean found = false;
	int showtimeIndex = 1;
	
	do {
		try {
			int choice = scanner.nextInt();
			if (choice > CineplexDatabase.showTimeCount()) {
				throw new ArrayIndexOutOfBoundsException();
			}
			System.out.println();
			
			for (Cineplex cp: CineplexDatabase.getArrayList()) {
				for (Cinema c: cp.getCinemaList()){
					for (CinemaShow cs: c.getCinemaShowList()) {
						System.out.println("choice "+ choice+ "showtimeIndex" + showtimeIndex);
						if (choice == showtimeIndex) {
							System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
							System.out.println("Movie: " + cs.getMovie().getTitle() + " on " + cs.getShowtime().format(DateTimeFormatter.ofPattern("dd-MM, HH:mm")));
							System.out.println("- - - - - - - - - - - - - - - - - ");
							
							cs.printSeating();
							found = true;
						} 
						showtimeIndex++;
								
					}
				}
			}
			
			showtimeIndex = 1;
		} catch (InputMismatchException IMe) {
		System.out.println("Invalid choice entered. Please re-enter choice.");
		scanner.nextLine();
		} catch (ArrayIndexOutOfBoundsException AOe) {
		System.out.println("No such showtime found, please re-enter choice.");
		scanner.nextLine();
		}
	
	} while(!found);
	
}

private static void customerReviewMenu(Scanner scanner) {
		//Have to list description of movie- function yet to implement
		while (MainMenuManager.choice != 4){
		try{
			System.out.println("=========Reviews=========\n"
							  +"1. Read reviews\n"
							  +"2. View overall reviewers' rating\n"
							  +"3. Add review\n"
							  +"4. Back\n"
							  +"=========================\n"
							  +"Do you want to read or add reviews?");
			MainMenuManager.choice = scanner.nextInt();
			scanner.nextLine();
			
			}catch(Exception e){
			e.printStackTrace();
			System.out.println("Invalid input. Please re-enter choice.");
			scanner.nextLine();
		}
		
		
			switch(MainMenuManager.choice){
			case 1: 
				customerReadReview(scanner);
				break;
			case 2: 
				customerPrintAverageRating(scanner);
				break;
			case 3: 
				MovieDatabase.addReview(scanner);
				break;
			}
		}
}

	//Process of buying
private static void customerBuyProcess(Scanner scanner) {
		
		int i = 1; //Choose
		int showtimeIndex;
		int cineplexIndex;
		int cinemaIndex;
		int age = 0;
		int mobileNum;
		String name;
		String emailAddress;
		String seat;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM : HH:mm");	
		
		
		System.out.println("===================================");
		System.out.println("===================================");
		System.out.println("======TICKET PURCHASE COUNTER======");
		System.out.println("===================================");
		System.out.println("===================================");
		
		//Get Name
		
		do {
			try{
				System.out.println("Enter Name:");
				name = scanner.nextLine();
				break;
				}catch(Exception e){
				System.out.println("Invalid input. Please re-enter choice.");
				scanner.nextLine();
			}
		} while (true);		
		
		//Get mobile number
		do {
			try{
				System.out.println("Enter Mobile Number:");
				mobileNum = scanner.nextInt();
				scanner.nextLine();
				break;
				}catch(Exception e){
				System.out.println("Invalid input. Please re-enter choice.");
				scanner.nextLine();
			}
		} while (true);
		
		//Get email address
		
		do {
			try{
				System.out.println("Enter email address:");
				emailAddress = scanner.nextLine();
				break;
				}catch(Exception e){
				e.printStackTrace();
				System.out.println("Invalid input. Please re-enter choice.");
				scanner.nextLine();
			}
		} while(true);
		
		//Get age
		
		do {
			try{
				System.out.println("Enter age:");
				age = scanner.nextInt();
				scanner.nextLine();
				break;
				}catch(Exception e){
				System.out.println("Invalid input. Please re-enter choice.");
				scanner.nextLine();
			}
		} while (true);
		
		//printCineplex choices********
		
		do {
			try {
		CineplexDatabase.printAllCineplexes();
		System.out.print("Select cineplex: ");	
		cineplexIndex = scanner.nextInt();
		scanner.nextLine();
		break;
			} catch (Exception e) {
				System.out.println("Invalid input. Please re-enter choice.");
			}
		
		} while(true);
		
		i = 1;
		System.out.println("\n===============================");
		for(Cinema c : CineplexDatabase.cineplexList.get(cineplexIndex-1).getCinemaList()){
			System.out.println(i + ".\t" + c.getCinemaID());
			System.out.println("===============================");
			i++;
		}
		
		do {
			try {		
				System.out.print("Select cinema: ");
				cinemaIndex = scanner.nextInt();
				scanner.nextLine();
				if (CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaShowList().size() == 0) {
					throw new InputMismatchException();
				}
				break;
			} catch (InputMismatchException IMe) {
				System.out.println("Cinema has no showing times. Please select another cinema.");
			} catch (Exception e) {
				System.out.println("Invalid input. Please re-enter choice.");
			}
		} while(true);
		
		i = 1;
		System.out.println("\n========Movie===================Movie Date and Time===");
		for(CinemaShow cs : CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaShowList()){
			System.out.println(i + ".\t" + cs.getMovie().getTitle() + "			" + cs.getShowtime().format(formatter));
			System.out.println("======================================================");
			i++;
		}
		
		
		do {
			try {	
		System.out.print("Select showtime: ");
		showtimeIndex = scanner.nextInt();
		scanner.nextLine();
		break;
			} catch (Exception e) {
				System.out.println("Invalid input. Please re-enter choice.");
			}
		} while(true);

		//implement seat
		CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaShowList().get(showtimeIndex-1).printSeating();

		String rowString="";
		
		do {
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
					
				System.out.println("Select column:");
				column = scanner.nextInt();
				scanner.nextLine();
						
				//if seat not taken
				if (!CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaShowList().get(showtimeIndex-1).checkIfSeatOccupied(column, row)){
					System.out.println("Seat is taken, please book another seat.");
					break; //exit loop if seat is not taken
				}
				
				} catch (SeatOccupiedException e) {
					e.printStackTrace();
					System.out.println(e.getMessage()); //prints seat is taken.
				} catch(Exception e){
					e.printStackTrace();
					System.out.println("Invalid input. Please re-enter choice.");
					scanner.nextLine();
				
				}
		} while(true);
			
		
			String tempColumn = Integer.toString(column);
			seat = rowString+ tempColumn  ;
			
			
			
			
			//need Edit this
			Ticket ticket= new Ticket(seat, age, cinemaIndex, cineplexIndex, showtimeIndex, name, emailAddress, mobileNum);
			System.out.println("Price:" + ticket.getTicketPrice());
			
			
			String keepSeatingPlan = "O";
			
			System.out.println("Would you like to pay?: Y/N");
			
			
			keepSeatingPlan = scanner.next();
			
			do {
				scanner.nextLine();
				switch(keepSeatingPlan.toUpperCase()) {
				case "Y":
					System.out.println("Making payment...");		
	
					ticket.printTicket();
					TicketDatabase.add(ticket);
					
					Movie tempM = CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaShowList().get(showtimeIndex-1).getMovie();
					
					int index = 0;
					int movieIndex = 0;
					for (Movie m: MovieDatabase.getArrayList()) {
						if (tempM.getTitle() == m.getTitle()) {
							movieIndex = index;
							break;
						} else {
							index++;
						}
					}
					
					CinemaEnum cEnum = CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getClassOfCinema();			
					MovieType mEnum = CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaShowList().get(showtimeIndex-1).getMovie().getMovieType();
					LocalDateTime movieDay = CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaShowList().get(showtimeIndex-1).getShowtime();
					
					//adds ticket sale to the movie's total sales
					MovieDatabase.getArrayList().get(movieIndex).addTicketSale(ticket.calculateTicketPrice(cEnum, mEnum, age, movieDay));
					
					System.out.println("You have paid for your ticket!");
					TicketDatabase.updateTickets();
					CineplexDatabase.updateCineplexes();
					break;
				case "N": 
					System.out.println("Going back to menu...");
					break;
				default:
					System.out.println("Invalid input, please enter Y or N.");
				}
				break;
			} while (true);
			
			
	}
	
	//need a way to read review
private static void customerReadReview(Scanner scanner) {	
	MovieDatabase.printMovieTitles();
		
	do {
		try {
		System.out.println("Which movie's reviews do you want to read?");
		int movieIndex = scanner.nextInt();
		
		System.out.println("===Reviews for " + MovieDatabase.getArrayList().get(movieIndex-1).getTitle()+ "=============");
		MovieDatabase.getArrayList().get(movieIndex-1).printReviews();
		
		break;
		} catch (InputMismatchException IMe) {
			System.out.println("Invalid input. Try again");
		}
	} while (true);
}
	
private static void customerPrintAverageRating(Scanner scanner) {
		
	MovieDatabase.printFullMovieList();
	System.out.print("Which movie's average rating would you like to view?");	//ask which movie user wants to see avg rating of
	int choice = scanner.nextInt();
		
	MovieDatabase.getArrayList().get(choice-1).printAvgRating();
		
		
	}

private static void customerShowHistory(Scanner scanner) {
		
	if (TicketDatabase.getArrayList().size()== 0) {
		System.out.println("No booking history found.");
		return;
	}
		
	for(int i=0;i <TicketDatabase.getArrayList().size()  ; i++) {
		if(TicketDatabase.getArrayList().get(i) != null){
				TicketDatabase.getArrayList().get(i).printTicket();		
			}

	}
}
	
private static void customerPrintTop5Sales(Scanner scanner)
{
	ArrayList<Movie> tempSalesList = new ArrayList<Movie>();

	//create replica of MovieList
	if (MovieDatabase.getArrayList().get(0) == null)
	{
		System.out.println("Top 5 list is empty");
		return;
	}
	else if (MovieDatabase.getArrayList().size() != 0)
	{
		for(int i=0; i<MovieDatabase.getArrayList().size(); i++)
		{	
			tempSalesList.add(MovieDatabase.getArrayList().get(i));
		}

		Collections.sort(tempSalesList, Movie.salesComparator());

		for (int index=1; index<=5; index++)
		{
			System.out.println(	"==================================================================\n"
					+ "Number " + index + ":\nTitle: " + tempSalesList.get(index-1).getTitle() 
					+ "\nOverall Sales: " + tempSalesList.get(index-1).getTicketSales()
					+ "\n==================================================================\n\n");
		}
	}
}
	
	
private static void customerPrintTop5Ratings (Scanner scanner)
{
	ArrayList<Movie> tempRatingsList = new ArrayList<Movie>();

	//create replica of MovieList
	if (MovieDatabase.getArrayList().get(0) == null)
	{
		System.out.println("Top 5 list is empty");
		return;
	}
	else if (MovieDatabase.getArrayList().size() != 0)
	{
		for(int i=0; i<MovieDatabase.getArrayList().size(); i++)
		{	
			tempRatingsList.add(MovieDatabase.getArrayList().get(i));
		}

		Collections.sort(tempRatingsList, Movie.ratingComparator());

		for (int index=1; index<=5; index++)
		{
			System.out.println(	"==================================================================");
			System.out.println("Number " + index + ":\nTitle: " + tempRatingsList.get(index-1).getTitle());

			if (tempRatingsList.get(index-1).getReviewList().size() < 2) {
				System.out.println("Average rating: " + "NA. Movie has only 1 review/rating.");
			} else {
				System.out.println("Average rating: " + tempRatingsList.get(index-1).getAvgRating());
			}

		}

	}
}

}
