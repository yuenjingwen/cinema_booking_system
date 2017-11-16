package moblima;

import java.io.Serializable;
/**
 * Represents Movie Type Normal that is a child of parent "Movie" class.
 * Implements Serializable interface.
 */
public class MovieNormal extends Movie implements Serializable{
	/**
	 * Version number of the serializable class "MovieNormal".
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Creates a movie with given title, synopsis, director, cast, movie type and movie rating.
	 * Creates an ArrayList of movie reviews for this movie.
	 * 
	 * @param title Title of this new movie.
	 * @param synopsis Synopsis of this new movie.
	 * @param director Director of this new movie.
	 * @param cast Cast of this new movie. Cast members separated by a comma.
	 * @param movieType This movie's type. E.g Blockbuster
	 * @param movieRating This movie's rating.
	 */
	public MovieNormal(String title, String synopsis, String director, String cast, MovieType movieType, MovieRating movieRating) {
		super(title, synopsis, director, cast, movieType, movieRating);
		// TODO Auto-generated constructor stub
	}
	/**
	 * Gets movie discount of this movie.
	 * @return Discount value of this movie.
	 */
	@Override
	public int getMovieDiscount(){
		int discount = 0;
		try {
			discount = TicketDatabase.searchDiscountByName("Normal");
			return discount;
		} catch (Exception e) {
			System.out.println("No such discount found.");
			e.printStackTrace();
		}
		return discount;
		
	}
}
