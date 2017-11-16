package moblima;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
/**
 * Represents a movie that can be showed in cinemas.
 * Implements serializable interface.
 */
public class Movie implements Serializable{

	/**
	 * Version number of the serializable class "Movie".
	 */
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
	 * @return Title of this movie.
	 */
	public String getTitle(){
		return this.title;
	}	

	/**
	 * Get synopsis of this movie.
	 * @return Synopsis of this movie as a String.
	 */
	public String getSynopsis(){
		return this.synopsis;
	}
	/**
	 * Get director of this movie.
	 * @return Director of this movie as a String.
	 */
	public String getDirector(){
		return this.director;
	}
	
	/**
	 * Get cast of this movie.
	 * @return Cast of this movie as a String.
	 */
	public String getCast(){
		return this.cast;
	}

	/**
	 * Gets average rating across the reviews of this movie.
	 * @return Average rating across the reviews for this movie.
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
	 * Gets showing status of this movie.
	 * @return Showing status of this movie.
	 */
	public ShowingStatus getShowingStatus() {
		return showingStatus;
	}
	/**
	 * Displays average rating of this movie.
	 * Not applicable for movies with only 1 review.
	 */
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

	/**
	 * Displays all reviews of this movie.
	 */
	public void printReviews(){
			System.out.println("- - - - - - - - - - - - - - - - - ");
		for(MovieReview o : reviews){
			
			System.out.println("Review:			"+ "Rating: " + o.getRating());
			System.out.println(MovieDatabase.breakLines(o.getReview(), 50));
			System.out.println("- - - - - - - - - - - - - - - - - ");
		}
	}
	/**
	 * Gets the ArrayList of movie reviews of this movie.
	 * @return ArrayList of movie review of this movie.
	 */
	public ArrayList<MovieReview> getReviewList() {
		return reviews;
	}
	/**
	 * Gets movie type of this movie.
	 * @return Movie type of this movie.
	 */
	public MovieType getMovieType(){
		return movieType;
	}
	/**
	 * Gets total ticket sales of this movie.
	 * @return total ticket sales of this movie.
	 */
	public float getTicketSales(){
		return this.ticketSales;
	}
	/**
	 * Sets title of this movie as given title.
	 * @param New title of this movie.
	 */
	public void setTitle(String title){
		this.title = title;
	}
	/**
	 * Sets synopsis of this movie as given synopsis.
	 * @param New synopsis of this movie.
	 */
	public void setSynopsis(String synopsis){
		this.synopsis = synopsis;
	}
	/**
	 * Sets director of this movie as given director.
	 * @param New director of this movie.
	 */
	public void setDirector(String director){
		this.director = director;
	}
	/**
	 * Sets cast of this movie as given cast.
	 * @param New cast of this movie.
	 */
	public void setCast(String cast){
		this.cast = cast;
	}
	/**
	 * Adds given review and rating to ArrayList "reviews" of this movie.
	 * @param review New review to be added.
	 * @param rating New rating to be added.
	 */
	public void addReview(String review, int rating){
		reviews.add(new MovieReview(review, rating));
	}
	/**
	 * Sets showing status of this movie as given showing status.
	 * @param New showing status of this movie.
	 */
	public void setShowingStatus (ShowingStatus status){
		this.showingStatus = status;
	}
	/**
	 * Add sales of given ticket to total ticket sales of this movie.
	 * @param Sales of given ticket.
	 */	
	public void addTicketSale(float ticketSalePrice){
		this.ticketSales += ticketSalePrice;
	}

	/**
	 * Compares the total ticket sales between 2 movies.
	 * @return lsdjfklasjdhflasdfsdf
	 */
	static Comparator<Movie> salesComparator() {
		
		return new Comparator<Movie>() {
			/**
			 * Compares the total ticket sales between 2 movies.
			 * @param o1 First movie to be compared with.
			 * @param o2 Second movie to be compared with.
			 * @return Positive integer if sales of o1 is higher than sales of o2.
			 * 		   Negative integer if sales of o2 is higher than sales of o1.
			 * 		   If sales of o1 is the same as sales of o2,
			 * 		   Positive integer if title of o1 is higher than title of o2.
			 * 		   Negative integer if title of o2 is higher than title of o1.
			 */
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
	/**
	 * Compares the total ticket sales between 2 movies.
	 * @return lsdjfklasjdhflasdfsdf
	 */
	static Comparator<Movie> ratingComparator() {
		return new Comparator<Movie>()	 {
			/**
			 * Compares the average rating between 2 movies.
			 * @param o1 First movie to be compared with.
			 * @param o2 Second movie to be compared with.
			 * @return Positive integer if average rating of o1 is higher than average rating of o2.
			 * 		   Negative integer if average rating of o2 is higher than average rating of o1.
			 * 		   If average rating of o1 is the same as average rating of o2,
			 * 		   Positive integer if title of o1 is higher than title of o2.
			 * 		   Negative integer if title of o2 is higher than title of o1.
			 */
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
	/**
	 * Gets movie ID of this movie.
	 * @return Movie ID of this movie.
	 */
	public int getMovieID() {
		return movieID;
	}
	/**
	 * Gets movie discount of this movie.
	 * @return The value 0.
	 */
	//To be overriden in subclass
	public int getMovieDiscount(){
		return 0;
	}
	/**
	 * Gets rating of this movie.
	 * @return Rating of this movie.
	 */
	public MovieRating getMovieRating() {
		return movieRating;
	}
	/**
	 * Sets given rating as rating of this movie.
	 * @param movieRating New rating of this movie.
	 */
	public void setMovieRating(MovieRating movieRating) {
		this.movieRating = movieRating;
	}
}

