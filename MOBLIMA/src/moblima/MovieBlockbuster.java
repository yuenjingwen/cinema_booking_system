package moblima;

import java.io.Serializable;

public class MovieBlockbuster extends Movie implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MovieBlockbuster(String title, String synopsis, String director, String cast, MovieType movieType) {
		super(title, synopsis, director, cast, movieType);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getMovieDiscount(){
		int discount = 0;
		try {
			discount = TicketDatabase.searchDiscountByName("Blockbuster");
			return discount;
		} catch (Exception e) {
			System.out.println("No such discount found.");
			e.printStackTrace();
		}
		return discount;
		
	}
}
