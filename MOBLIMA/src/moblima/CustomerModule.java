package moblima;

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
		
		while (custChoice!=7) {
		System.out.print(	"=====================Movie-goer Menu=====================\n"
							+ "1. Search/List Movie\n"
							+ "2. View movie details- View/Write Reviews\n"
							+ "3. Check seat availability and Book/purchase Ticket\n"
							+ "4. See my movie history\n"
							+ "5. List top 5 movies by sales\n"
							+ "6. List top 5 movies by rating\n"
							+ "7. Back\n"
							+ "=======================================================\n"
							+ "Please enter choice: \n");
			try{
				custChoice = scanner.nextInt();
				scanner.nextLine();
				
				switch (custChoice) {
				case 1:
					MovieDatabase.printMovieList();
					break;
				case 2:
					customerReviewMenu(scanner);
					break;
				case 3:
					customerBuyProcess(scanner);
					break;
				case 4:
					customerShowHistory(scanner);
					break;
				case 5:
					customerPrintTop5Sales(scanner);
					break;
				case 6:
					customerPrintTop5Ratings(scanner);
					break;
				case 7:
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
			System.out.println("Invalid input. Please re-enter choicee.");
			scanner.nextLine();
		}
		
		
			switch(MainMenuManager.choice){
			case 1: 
				readReview(scanner);
				break;
			case 2: 
				printAverageRating(scanner);
				break;
			case 3: 
				addReview(scanner);
				break;
			}
		}
}

	//Process of buying
	public static void customerBuyProcess(Scanner scanner){
		
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");	
		
		
		System.out.println("===================================");
		System.out.println("===================================");
		System.out.println("======TICKET PURCHASE COUNTER======");
		System.out.println("===================================");
		System.out.println("===================================");
		
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
			System.out.println("Invalid input. Please re-enter choice.");
			scanner.nextLine();
		}
		
		//printCineplex choices********
		CineplexDatabase.printAllCineplexes();
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
			System.out.println("Price:" + ticket.getTicketPrice());
			System.out.println("Making payment...");
		
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


	private static void customerShowHistory(Scanner scanner) {
		for(int i=0;i <TicketDatabase.getArrayList().size()  ; i++) {
			if(TicketDatabase.getArrayList().get(i) != null){
				TicketDatabase.getArrayList().get(i).printTicket();		
				}

		}
	}
	
	private static void customerPrintTop5Sales(Scanner scanner)
	{
		
	}
	
	private static void customerPrintTop5Ratings (Scanner scanner)
	{	
		ArrayList<Movie> sortedRatingsList = new ArrayList<Movie>();
		
		
		if (MovieDatabase.getArrayList().get(0) == null){
			System.out.println("Top 5 list is empty");
			return;
		}else if (MovieDatabase.getArrayList().size() != 0){
			
			
			System.out.println("Size"+MovieDatabase.getArrayList().size() );
		 
			for(int i=0; i<MovieDatabase.getArrayList().size()-1; i++)
				{	System.out.println(MovieDatabase.getArrayList().get(i).getTitle());
				sortedRatingsList.add(MovieDatabase.getArrayList().get(i));
				//MovieDatabase.getArrayList().get(i)
				sortedRatingsList.get(i).setTitle(MovieDatabase.getArrayList().get(i).getTitle());		
				//sortedRatingsList.get(i).getReviewList().add(i, MovieDatabase.getArrayList().get(i).getReviewList().get(i));
				System.out.println("check");
			}
			
	
		ratingsInsertionSort(sortedRatingsList);
		
		for (int j=1; j<=5; j++)
		{
			System.out.println("Number " + j + ":\nTitle: " + sortedRatingsList.get(j-1).getTitle() 
								+ "\nOverall reviewers' rating: " + sortedRatingsList.get(j-1).getAvgRating());
		}
		}
		else{
			System.out.println("Default statement: Error... Returning to main menu");
		}
		
	}
	
	private static ArrayList<Movie> ratingsInsertionSort(ArrayList<Movie> list)
	{
		for(int i = 1; i < list.size(); i++)
		{
			for(int j = i; j>0; j--)
			{						// Swaps if current array[j] < array[j-1] then decreases j by 1
				if(list.get(j).getAvgRating() > list.get(j-1).getAvgRating())  
				{					// Swap sequence
					Movie temp = list.get(j-1);
					list.add(j-1, list.get(j));
					list.add(j, temp);
				}
				else
				{
					break;
				}
			}
		}
		return list;
	}


	


	
	
	
	
	
	
}