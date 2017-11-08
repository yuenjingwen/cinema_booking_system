package moblima;

import java.util.ArrayList;

public class ReviewDatabase{

	
	private static ArrayList<MovieReview> RDB;
	

	public void add(String reviewText, int rating) {
		MovieReview temp = new MovieReview(reviewText, rating);
		RDB.add(temp);
	}

	public void remove(int reviewIndex) {
		RDB.remove(reviewIndex);

	}

	public void edit(int reviewIndex, String reviewText, int rating) {
		MovieReview temp = new MovieReview(reviewText, rating);
		RDB.set(reviewIndex, temp);
		

	}

}
