package moblima;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ReviewDatabase{

	
	private static ArrayList<MovieReview> RDB;
	

	public static void addReview(String reviewText, int rating) {
		MovieReview temp = new MovieReview(reviewText, rating);
		RDB.add(temp);
		
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
	
	public MovieReview fetchReview() {
		
	}

	

}
