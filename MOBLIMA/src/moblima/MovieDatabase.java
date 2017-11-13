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

public class MovieDatabase{

	private static ArrayList<Movie> movieList;
//	private static ArrayList<MovieReview> RDB;
	private static File movieFile = new File("Movie.dat");
	
	
	public static ArrayList<Movie> getArrayList() {
		return movieList;
	}
	
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
				+ "Enter movie type: ");
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
				temp = scanner.nextLine();
				movieList.get(index - 1).setCast(temp);
				break;
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

	public static void addMovie(Scanner scanner){
		String title;
		String synopsis = null;
		String director = null;
		String cast = null;
		MovieType movietype = null;
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
		
		System.out.print("Enter cast: ");
		cast = scanner.nextLine();

		movieList.add(new Movie(title, synopsis, director, cast, movietype));
		updateMovies();
	}

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

	public static void fetchMovies(){
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
	
	public static void printMovieList() {
		System.out.print("\n=======================================================\n");
		System.out.print("                     Movies Listed                     ");
		System.out.print("\n=======================================================\n");
		int i = 1;
		for(Movie m : getArrayList()){
			System.out.println("=======================================================");
			System.out.println("Index: " + i + "\t" + m.getMovieType() + "\n");
			System.out.println("Showing Status: " + m.getShowingStatus().toString());
			System.out.println("Title:\t\t" + m.getTitle());
			System.out.println("Director:\t" + m.getDirector());
			System.out.println("Cast:\t\t" + m.getCast());
			System.out.println("-------------------------------------------------------");
			System.out.println("Synopsis: ");
			System.out.println(breakLinesForReviews(m.getSynopsis(), 50));
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
	
	public static void printReviewList(Scanner scanner) {
		
		MovieDatabase.printMovieList();	//print movie list
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


	public static void addReview(Scanner scanner) {
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
	getArrayList().get(movieIndex-1).addReview(tempReview, tempRating);
	
	updateMovies();
}
	
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

}


