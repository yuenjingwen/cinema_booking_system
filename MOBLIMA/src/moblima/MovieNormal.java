package moblima;

import java.io.Serializable;

public class MovieNormal extends Movie implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MovieNormal(String title, String synopsis, String director, String cast, MovieType movieType, MovieRating movieRating) {
		super(title, synopsis, director, cast, movieType, movieRating);
		// TODO Auto-generated constructor stub
	}
	
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
