package moblima;

import java.io.Serializable;
/**
 * Represents Movie Type 3D that is a child of parent "Movie" class.
 * Implements Serializable interface.
 */
public class Movie3D extends Movie implements Serializable{
	/**
	 * Version number of the serializable class "Movie3D".
	 */
	private static final long serialVersionUID = 1L;
	
	public Movie3D(String title, String synopsis, String director, String cast, MovieType movieType, MovieRating movieRating) {
		super(title, synopsis, director, cast, movieType, movieRating);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getMovieDiscount(){
		int discount=0;
		try {
			discount = TicketDatabase.searchDiscountByName("3D");
		} catch (Exception e) {
			System.out.println("No such discount found.");
			e.printStackTrace();
		}
		return discount;
	}
}
