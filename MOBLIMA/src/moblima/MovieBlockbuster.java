package moblima;

public class MovieBlockbuster extends Movie {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MovieBlockbuster(String title, String synopsis, String director, String cast, MovieType movieType) {
		super(title, synopsis, director, cast, movieType);
		// TODO Auto-generated constructor stub
	}

	public int getMovieDiscount(){
		int discount = 3;
		return discount;
	}
}
