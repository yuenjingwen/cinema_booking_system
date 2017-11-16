package moblima;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Movie implements Serializable{

	
	private static final long serialVersionUID = 41302788253857504L;
	/**
	 * Title of this movie.
	 */
	private String title;
	/**
	 * Synopsis of this movie.
	 */
	private String synopsis;
	/**
	 * Director of this movie.
	 */
	private String director;
	/**
	 * Cast of this movie.
	 */
	private String cast;
	/**
	 * MovieID of this movie. Uniquely assigned when the movie is created.
	 */
	private int movieID;
	/**
	 * Array list of reviews for this movie.
	 */
	private ArrayList<MovieReview> reviews;
	/**
	 * Showing status of this movie. E.g Coming soon.
	 */
	private ShowingStatus showingStatus;	
	/**
	 * Type of Movie. E.g Blockbuster
	 */
	private MovieType movieType;
	/**
	 * Movie rating for this movie, averaged from all the reviews.
	 */
	private MovieRating movieRating;
	
	/**
	 * Total ticket sales for this movie thus far.
	 */
	private float ticketSales;
	
	
	
	/**
	 * 
	 * @param title Title of this new movie.
	 * @param synopsis Synopsis of this new movie.
	 * @param director Director of this new movie.
	 * @param cast Cast of this new movie. Cast members separated by a comma.
	 * @param movieType This movie's type. E.g Blockbuster
	 * @param movieRating This movie's rating.
	 */
	public Movie(String title, String synopsis, String director, String cast, MovieType movieType, MovieRating movieRating){
		this.title = title;
		this.showingStatus = ShowingStatus.COMING_SOON;
		this.synopsis = synopsis;
		this.director = director;
		this.cast = cast;
		
		this.movieRating = movieRating;
		this.movieType = movieType;
		this.ticketSales = 0;
		Random random = new Random();
		this.movieID = random.nextInt(1000000) + 1;
		
		reviews = new ArrayList<MovieReview>();
	};
	
	/**
	 * Setter for this movie's type.
	 * @param movieType Input for this movie's type as a MovieType enum.
	 */
	public void setMovieType(MovieType movieType) {
		this.movieType = movieType;
	}
	
	/**
	 * Getter for this movie's title.
	 * 
	 */
	public String getTitle(){
		return this.title;
	}	

	/**
	 * 
	 * @return Returns the synopsis of this movie as a String.
	 */
	public String getSynopsis(){
		return this.synopsis;
	}
	/**
	 * 
	 * @return Returns the director of this movie as a String.
	 */
	public String getDirector(){
		return this.director;
	}
	
	/**
	 * 
	 * @return Returns the cast of this movie as a String.
	 */
	public String getCast(){
		return this.cast;
	}

	/**
	 * 
	 * @return Returns the average rating across the reviews for this movie.
	 */
	public float getAvgRating()
	{
		float sum = 0;
		for(MovieReview o : reviews){
			sum += (float) o.getRating();
		}
		
		return sum/reviews.size();
	}
	
	/**
	 * 
	 * @return Returns the showing status of this movie.
	 */
	public ShowingStatus getShowingStatus() {
		return showingStatus;
	}
	
	public void printAvgRating()
	{
		//print avg rating for that movie if there is >1 ratings
				if (reviews.size() > 1)
				{
					System.out.printf("%.1f", getAvgRating());
					System.out.println("\n");
				}
		//NA when there is one or less rating
				else
				{
					System.out.println("NA - there is only one individual rating/review to display.");
				}
	}

	
	public void printReviews(){
			System.out.println("- - - - - - - - - - - - - - - - - ");
		for(MovieReview o : reviews){
			
			System.out.println("Review:			"+ "Rating: " + o.getRating());
			System.out.println(MovieDatabase.breakLines(o.getReview(), 50));
			System.out.println("- - - - - - - - - - - - - - - - - ");
		}
	}
	
	public ArrayList<MovieReview> getReviewList() {
		return reviews;
	}
	
	public MovieType getMovieType(){
		return movieType;
	}
	
	public float getTicketSales(){
		return this.ticketSales;
	}

	public void setTitle(String title){
		this.title = title;
	}
	
	public void setSynopsis(String synopsis){
		this.synopsis = synopsis;
	}
	
	public void setDirector(String director){
		this.director = director;
	}
	
	public void setCast(String cast){
		this.cast = cast;
	}
	
	public void addReview(String review, int rating){
		reviews.add(new MovieReview(review, rating));
	}
	
	public void setShowingStatus (ShowingStatus status){
		this.showingStatus = status;
	}
		
	public void addTicketSale(float ticketSalePrice){
		this.ticketSales += ticketSalePrice;
	}

	
	static Comparator<Movie> salesComparator() {
		
		return new Comparator<Movie>() {
			@Override
			public int compare(Movie o1, Movie o2) {
				if (o1.getTicketSales()==o2.getTicketSales()) {
					return o1.getTitle().compareTo(o2.getTitle());
				} else {
					int comp = (int) (o2.getTicketSales()-o1.getTicketSales());
					return comp;
				}
			}
		};
	}

	static Comparator<Movie> ratingComparator() {
		return new Comparator<Movie>()	 {
			@Override
			public int compare(Movie o1, Movie o2) {
				if (o1.getAvgRating() == o2.getAvgRating()) {
					return o1.getTitle().compareTo(o2.getTitle());
				}else {
					float comp = (o2.getAvgRating()-o1.getAvgRating());
					if (comp > 0) {
						return 1;
					} else if (comp < 0) {
						return -1;
					} else {
						return 0;
					}
				}
			}
		};
	}

	public int getMovieID() {
		return movieID;
	}
	
	//To be overriden in subclass
	public int getMovieDiscount(){
		return 0;
	}

	public MovieRating getMovieRating() {
		return movieRating;
	}

	public void setMovieRating(MovieRating movieRating) {
		this.movieRating = movieRating;
	}
}

