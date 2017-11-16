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
import java.util.Scanner;

public class MovieDatabase implements Database{

	/**
	 * An array list of the Movie class. 
	 */
	private static ArrayList<Movie> movieList;
//	private static ArrayList<MovieReview> RDB;
	
	/**
	 * Creates a dat file called movieFile.
	 */
	private static File movieFile = new File("Movie.dat");
	
	/**
	 * Get an array list of current movies. 
	 * @return an array list of current movies
	 */
	public static ArrayList<Movie> getArrayList() {
		return movieList;
	}
	
	/**
	 * For admin to edit the details of a selected movie.
	 * The details of the movie in the existing array list will also be updated accordingly. 
	 * @param scanner Scanner object
	 */
	public static void editMovie(Scanner scanner) {
		String temp = null;
		System.out.print("Select movie to edit: ");
		int index = scanner.nextInt();
		System.out.println(""
				+ "1. Title\n"
				+ "2. Director\n"
				+ "3. Synopsis\n"
				+ "4. Movie Type\n"
				+ "5. Cast\n"
				+ "6. Showing Status\n"
				+ "7. Movie Rating\n"
				+ "Enter parameter to change: ");
		try{
			int select = scanner.nextInt();
			scanner.nextLine();
			switch (select) {
			case 1:
				System.out.print("Enter title: ");
				temp = scanner.nextLine();
				movieList.get(index - 1).setTitle(temp);
				break;
			case 2:
				System.out.print("Enter director: ");
				temp = scanner.nextLine();
				movieList.get(index - 1).setDirector(temp);
				break;
			case 3:
				System.out.print("Enter synopsis: ");
				temp = scanner.nextLine();
				movieList.get(index - 1).setSynopsis(temp);
				break;
			case 4:
				System.out.print("\nMovie types:\n"
						+ "1. Normal"
						+ "2. Blockbuster"
						+ "3. 3D"
						+ "Enter movie type: ");
				try{
					int mType = scanner.nextInt();
					scanner.nextLine();
					switch (mType) {
					case 1:
						movieList.get(index-1).setMovieType(MovieType.NORMAL);
						break;
					case 2:
						movieList.get(index-1).setMovieType(MovieType.BLOCKBUSTER);
						break;
					case 3:
						movieList.get(index-1).setMovieType(MovieType.THREE_D);
						break;
					default:
						System.out.println("Invalid input. Setting movie type to Normal.");
						movieList.get(index-1).setMovieType(MovieType.NORMAL);
						break;
					}
				}catch(Exception e){
					System.out.println("Invalid input.");
					scanner.nextLine();
					return;
				}
				break;
			case 5:
				System.out.print("Enter cast: ");
				try {
				temp = scanner.nextLine();
				} catch (Exception e) {
					System.out.println("Invalid Input. Movie cast empty.");
				}
				movieList.get(index - 1).setCast(temp);
				break;
			case 6: 
				System.out.println("List of showing statuses:");
				System.out.println("1. Now Showing");
				System.out.println("2. Coming Soon");
				System.out.println("3. Preview");
				System.out.println("4. End of Showing");
				System.out.println("Select new showing status:");
			
				try{
					int movieStatus = scanner.nextInt();
					scanner.nextLine();
					
					
					switch (movieStatus) {
					case 1: 
						movieList.get(index-1).setShowingStatus(ShowingStatus.NOW_SHOWING);
						break;
					case 2: 
						movieList.get(index-1).setShowingStatus(ShowingStatus.COMING_SOON);
						break;
					case 3: 
						movieList.get(index-1).setShowingStatus(ShowingStatus.PREVIEW);
						break;
					case 4: 
						movieList.get(index-1).setShowingStatus(ShowingStatus.END_OF_SHOWING);
						break;
					default:
						System.out.println("Invalid Input. Please re-enter choice.");
					}
				} catch (Exception e){
					e.printStackTrace();
				}
				break;
			case 7:
				System.out.print("\nMovie ratings:\n"
						+ "1. General (G)\n"
						+ "2. Parental Guidance (PG)\n"
						+ "3. Parental Guidance 13 (PG13)\n"
						+ "4. No Children under 16 (NC16)\n"
						+ "5. Mature 18 (M18)\n"
						+ "6. Restricted 21 (R21)\n"
						+ "Enter movie rating: ");
				try{
					int mRating = scanner.nextInt();
					scanner.nextLine();
					switch (mRating) {
					case 1:
						movieList.get(index-1).setMovieRating(MovieRating.G);
						break;
					case 2:
						movieList.get(index-1).setMovieRating(MovieRating.PG);
						break;
					case 3:
						movieList.get(index-1).setMovieRating(MovieRating.PG13);
						break;
					case 4:
						movieList.get(index-1).setMovieRating(MovieRating.NC16);
						break;
					case 5:
						movieList.get(index-1).setMovieRating(MovieRating.M18);
						break;
					case 6:
						movieList.get(index-1).setMovieRating(MovieRating.R21);
						break;
					default:
						movieList.get(index-1).setMovieRating(MovieRating.G);
						break;
					}
				}catch(Exception e){
					System.out.println("Invalid input.");
					scanner.nextLine();
					return;
				}
			default:
				System.out.println("\n===========");
				System.out.println("Invalid Selection");
				System.out.println("===========\n");
				break;
			}
		}catch(Exception e){
			System.out.println("Invalid input.");
			scanner.nextLine();
			return;
		}
		updateMovies();
	}

	/**
	 * For admin to add new movies and its details.
	 * The new movie will be added to the existing movie array list.
	 * @param scanner Scanner object
	 */
	public static void addMovie(Scanner scanner){
		String title;
		String synopsis = null;
		String director = null;
		String cast = null;
		MovieType movietype = null;
		MovieRating movierating = null;
		System.out.print("Enter title: ");
		title = scanner.nextLine();
		System.out.print("Enter director: ");
		director = scanner.nextLine();
		System.out.print("Enter sypnosis: ");
		synopsis = scanner.nextLine();
		System.out.print("\nMovie types:\n"
				+ "1. Normal\n"
				+ "2. Blockbuster\n"
				+ "3. 3D\n"
				+ "Enter movie type: ");
		try{
			int mType = scanner.nextInt();
			scanner.nextLine();
			switch (mType) {
			case 1:
				movietype = MovieType.NORMAL;
				break;
			case 2:
				movietype = MovieType.BLOCKBUSTER;
				break;
			case 3:
				movietype = MovieType.THREE_D;
				break;
			default:
				movietype = MovieType.NORMAL;
				break;
			}
		}catch(Exception e){
			System.out.println("Invalid input.");
			scanner.nextLine();
			return;
		}
		
		System.out.print("\nMovie ratings:\n"
				+ "1. General (G)\n"
				+ "2. Parental Guidance (PG)\n"
				+ "3. Parental Guidance 13 (PG13)\n"
				+ "4. No Children under 16 (NC16)\n"
				+ "5. Mature 18 (M18)\n"
				+ "6. Restricted 21 (R21)\n"
				+ "Enter movie rating: ");
		try{
			int mRating = scanner.nextInt();
			scanner.nextLine();
			switch (mRating) {
			case 1:
				movierating = MovieRating.G;
				break;
			case 2:
				movierating = MovieRating.PG;
				break;
			case 3:
				movierating = MovieRating.PG13;
				break;
			case 4:
				movierating = MovieRating.NC16;
				break;
			case 5:
				movierating = MovieRating.M18;
				break;
			case 6:
				movierating = MovieRating.R21;
				break;
			default:
				movierating = MovieRating.G;
				break;
			}
		}catch(Exception e){
			System.out.println("Invalid input.");
			scanner.nextLine();
			return;
		}
		
		System.out.print("Enter cast: ");
		cast = scanner.nextLine();

		movieList.add(new Movie(title, synopsis, director, cast, movietype, movierating));
		updateMovies();
	}

	/**
	 * For admin to remove a movie.
	 * The movie will also be removed from the existing movie array list.
	 * @param scanner Scanner object
	 */
	public static void removeMovie(Scanner scanner){
		int index;
		System.out.println("=============================");
		System.out.print("Enter movie index to remove: ");
		index = scanner.nextInt();
		scanner.nextLine();
		movieList.remove(index-1);
		updateMovies();
		System.out.println("=============================");
		System.out.println("Movie removed");
		System.out.println("=============================\n");
	}

//	public static void fetchMovies(){
//		movieList = new ArrayList<Movie>();
//		try{
//			FileInputStream fi = new FileInputStream(movieFile);
//			ObjectInputStream input = new ObjectInputStream(fi);
//			
//			try{
//				while(true){
//					Movie m = (Movie)input.readObject();
//					movieList.add(m);
//				}
//			} catch (EOFException e){
//			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//			}
//			
//			fi.close();
//			input.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	/**
	 * Changes database to the most updated version. 
	 */
	public static void updateMovies(){
		try{
			FileOutputStream fo = new FileOutputStream(movieFile);
			ObjectOutputStream output = new ObjectOutputStream(fo);
			for(Movie m: movieList){
				output.writeObject(m);
			}
			fo.close();
			output.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}	
	
	/**
	 * Prints out the list of all movies together with its details. 
	 * Details include: index, showing status, title, director, cast and synopsis.
	 */
	public static void printFullMovieList() {
		System.out.print("\n=======================================================\n");
		System.out.print("                     Movies Listed                     ");
		System.out.print("\n=======================================================\n");
		int i = 1;
		for(Movie m : getArrayList()){
			System.out.println("=======================================================");
			System.out.println("Index: " + i + "\t" + m.getMovieType() + "\n");
			System.out.println("Showing Status: " + m.getShowingStatus().toString());
			System.out.println("Title:\t\t" + m.getTitle() + " (" +  m.getMovieRating() +")");
			System.out.println("Director:\t" + m.getDirector());
			System.out.println("Cast:\t\t" + m.getCast());
			System.out.println("-------------------------------------------------------");
			System.out.println("Synopsis: ");
			System.out.println(breakLinesForReviews(m.getSynopsis(), 50));
			System.out.println("=======================================================");
			i++;
		}
	
	}

	/**
	 * Prints out the list of movie titles and their average ratings and reviews.
	 */
	public static void printMovieTitles() {
		System.out.print("\n=======================================================\n");
		System.out.print("                     Movies Listed                     ");
		System.out.print("\n=======================================================\n");
		int i = 1;
		for(Movie m : getArrayList()){
			System.out.println("["+i+"] Title:\t\t" + m.getTitle());
			System.out.println("Average Rating:\t\t" + m.getAvgRating() + " from " + m.getReviewList().size() + " reviews");
			System.out.println("=======================================================");
			i++;
		}
	}
/*
	public void removeReview(int reviewIndex) {
		RDB.remove(reviewIndex-1);
		
		File file = new File("MovieReview.dat");
		try{
			FileOutputStream fo = new FileOutputStream(file);
			ObjectOutputStream output = new ObjectOutputStream(fo);
			for(MovieReview r: RDB){
				output.writeObject(r);
			}
			fo.close();
			output.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	*/
	
/*
	public void editReview(int reviewIndex, String reviewText, int rating) {
		MovieReview temp = new MovieReview(reviewText, rating);
		RDB.set(reviewIndex, temp);
		
		File file = new File("MovieReview.dat");
		try{
			FileOutputStream fo = new FileOutputStream(file);
			ObjectOutputStream output = new ObjectOutputStream(fo);
			for(MovieReview r: RDB){
				output.writeObject(r);
			}
			fo.close();
			output.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}*/
	
	/**
	 * Prints out the list of all reviews for a selected movie.
	 * @param scanner Scanner object
	 */
	public static void printReviewList(Scanner scanner) {
		
		MovieDatabase.printFullMovieList();	//print movie list
		System.out.print("Which movie review would you like to read?");
		System.out.println();//ask which movie you want to see review for
		int movieIndex = scanner.nextInt();
		
		//print all reviews for that movie 
				System.out.println("=== Reviews For " + MovieDatabase.getArrayList().get(movieIndex -1).getTitle() + " ====\n");
		for (MovieReview r : movieList.get(movieIndex-1).getReviewList()) {	//print into format
				System.out.println("Review: " + r.getReview());
				System.out.println("Rating: " + r.getRating());
				System.out.println("-------------------------");
		
		}
	}


	/**
	 * For customers to add a review and rating for a selected movie.
	 * The review and rating will then be added into the existing array list for reviews. 
	 * @param scanner Scanner object
	 */
	public static void addReview(Scanner scanner) {
	int movieIndex;
	
	MovieDatabase.printFullMovieList();
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
	getArrayList().get(movieIndex-1).addReview(tempReview, tempRating);
	
	updateMovies();
}
	
	/**
	 * To set the maximum number of characters in one line of review. 
	 * This is to allow the whole review to fit into the dimension of the console. 
	 * @param input This is the review that the customer submitted.
	 * @param maxLineLength This is the maximum number of characters that can fit into one line of review.
	 * @return It returns the review in a different the selected format. The content of the review is unchanged. 
	 */
	private static String breakLinesForReviews(String input, int maxLineLength) {
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

	/**
	 * Prints the details of a single movie instead of details of the whole lists of movies available. 
	 * Details include: movie type, showing status, title, director, cast and synopsis.
	 * @param m The selected movie for its details to be printed. 
	 */
	public static void printSingleMovieDetails(Movie m) {
		System.out.println("=======================================================");
		System.out.println("Movie Type:		" + m.getMovieType() + "\n");
		System.out.println("Showing Status: " + m.getShowingStatus().toString());
		System.out.println("Title:\t\t" + m.getTitle());
		System.out.println("Director:\t" + m.getDirector());
		System.out.println("Cast:\t\t" + m.getCast());
		System.out.println("-------------------------------------------------------");
		System.out.println("Synopsis: ");
		System.out.println(breakLinesForReviews(m.getSynopsis(), 50));
		System.out.println("=======================================================");
	}
	
	/**
	 * Prints out the top 5 sales of movies if the movie array list is not empty. 
	 * @param scanner Scanner Object. 
	 */
	public static void printTop5Sales(Scanner scanner)
	{
		ArrayList<Movie> tempSalesList = new ArrayList<Movie>();

		//create replica of MovieList
		if (getArrayList().get(0) == null)
		{
			System.out.println("Top 5 list is empty");
			return;
		}
		else if (getArrayList().size() != 0)
		{
			for(int i=0; i<getArrayList().size(); i++)
			{	
				tempSalesList.add(getArrayList().get(i));
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
		
	/**
	 * Prints out the top 5 ratings of movies if the movie array list is not empty.
	 * @param scanner Scanner object
	 */
	public static void printTop5Ratings (Scanner scanner)
	{
		ArrayList<Movie> tempRatingsList = new ArrayList<Movie>();

		//create replica of MovieList
		if (getArrayList().get(0) == null)
		{
			System.out.println("Top 5 list is empty");
			return;
		}
		else if (getArrayList().size() != 0)
		{
			for(int i=0; i<getArrayList().size(); i++)
			{	
				tempRatingsList.add(getArrayList().get(i));
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

	@Override
	/**
	 * Update the movie database and writes it to the dat file. 
	 */
	public void updateDatabase() {
		
	}

	/**
	 * Fetches the movie database from the dat file and inputs it into the static array list. 
	 */
	public void fetchDatabase() {
		movieList = new ArrayList<Movie>();
		try{
			FileInputStream fi = new FileInputStream(movieFile);
			ObjectInputStream input = new ObjectInputStream(fi);
			
			try{
				while(true){
					Movie m = (Movie)input.readObject();
					movieList.add(m);
				}
			} catch (EOFException e){
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			fi.close();
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}



