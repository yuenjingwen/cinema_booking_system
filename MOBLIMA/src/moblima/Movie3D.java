package moblima;

public class Movie3D extends Movie {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Movie3D(String title, String synopsis, String director, String cast, MovieType movieType) {
		super(title, synopsis, director, cast, movieType);
		// TODO Auto-generated constructor stub
	}

	public int getMovieDiscount(){
		int discount = 5;
		return discount;
	}
}
