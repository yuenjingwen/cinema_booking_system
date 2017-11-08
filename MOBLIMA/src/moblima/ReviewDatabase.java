package moblima;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class ReviewDatabase{

	
	private static ArrayList<MovieReview> RDB;
	

	public static void addReview(String reviewText, int rating) {
		MovieReview temp = new MovieReview(reviewText, rating);
		
			
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

	public void remove(int reviewIndex) {
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

	

	public void edit(int reviewIndex, String reviewText, int rating) {
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
	}
	
	public void fetchReview() {
		
		MovieDatabase.printMovieList();	//print movie list
		System.out.print("Which movie review would you like to read?");	//ask which movie you want to see review for
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		
		//print all reviews for that movie 
		for (MovieReview r : MovieDatabase.getArrayList().get(choice).getReviewList()) {	//print into format
			System.out.println(r.getReview());
			System.out.println(r.getRating());
		}
	}

	

}
