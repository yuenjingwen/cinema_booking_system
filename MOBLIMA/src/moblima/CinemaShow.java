package moblima;

import java.io.Serializable; 
import java.time.LocalDateTime;
/**
 * Represents a cinema show in a cinema.
 * Each cinema show has a specific movie, seats and showtime.
 */
public class CinemaShow implements Serializable, Comparable<CinemaShow>{

	/**
	 * Version number of the serializable class "Cinema".
	 */
	private static final long serialVersionUID = 707618588443761880L;
	/**
	 * Layout of seats for this cinema show, displayed in a 2D array.
	 */
	private CinemaSeat[][] seats;
	/**
	 * Show time of this cinema show.
	 */
	private LocalDateTime showtime;
	/**
	 * Movie ID of movie showing for this cinema show.
	 */
	private int movieID;

	
	/**
	 * Creates a new layout of seats for this cinema show.
	 * Sets a show time and movie ID for this cinema show.
	 * @param movieID This cinema show's movie ID.
	 * @param showtime This cinema show's show time.
	 */
	public CinemaShow(int movieID, LocalDateTime showtime){
		seats = new CinemaSeat [8][8];
		
		for(int i = 0; i < 8; i++){
			if(i == 6 || i == 7){
				for(int j = 0; j < 8; j++){
					seats[i][j] = new CinemaSeat(SeatType.COUPLE);
				}
			}else{
				for(int j = 0; j < 8; j++){
					seats[i][j] = new CinemaSeat(SeatType.NORMAL);
				}
			}
		}
		
		this.showtime = showtime;
		this.movieID = movieID;
	}
	
	
	/**
	 * Gets show time of this cinema show.
	 * @return This cinema show's show time.
	 */
	public LocalDateTime getShowtime() {
		return showtime;
	}
	/**
	 * Set a show time for this cinema show.
	 * @param time Show time for this cinema show.
	 */
	public void setShowtime(LocalDateTime time) {
		showtime = time;
	}

	/**
	 * Prints layout of seats of this cinema show.
	 * Shows which seats are taken and which are available.
	 */
	public void printSeating(){
		System.out.println();;
		System.out.println("==================================");
		System.out.println("=          MOVIE SCREEN          =");
		System.out.println("==================================");
		for(int i = 0; i < 6; i++){
			for(int j = 0; j < 8; j++){
				if(j == 0){
					System.out.print(" " + (char) (65 + i) + " ");
				}
				if(j == 4){
					System.out.print("    ");
				}
				System.out.print("[");
				if(seats[i][j].getIsTaken()){
					System.out.print("X");
				}else{
					System.out.print(" ");
				}
				System.out.print("]");
				if(j == 7){
					System.out.print(" " + (char) (65 + i));
				}
			}
			System.out.println();
		}
		for(int i = 6; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(j == 0){
					System.out.print(" " + (char) (65 + i) + " ");
				}
				if(j == 4){
					System.out.print("    ");
				}
				if(j%2 == 0){
					System.out.print("[");
				}else{
					System.out.print(" ");
				}
				if(seats[i][j].getIsTaken()){
					System.out.print("X");
				}else{
					System.out.print(" ");
				}
				if(j%2 == 1){
					System.out.print("]");
				}else{
					System.out.print(" ");
				}
				if(j == 7){
					System.out.print(" " + (char) (65 + i));
				}
			}
			System.out.println();
		}
		System.out.println("----------------------------------");
		System.out.println("    1  2  3  4      5  6  7  8    ");
		System.out.println("==================================");
		System.out.println("[X] = Seat Taken                  ");
		System.out.println("[ ] = Seat Available              ");
		System.out.println("==================================");
	}
	/**
	 * Gets the seats of this cinema show.
	 * @return This cinema show's layout of seats.
	 */
	public CinemaSeat[][] getSeats(){
		return seats;
	}
	/**
	 * Sets the given seat as taken.
	 * @param x Row number of seat to be taken.
	 * @param y Column number of seat to be taken.
	 */
	public void setSeat(int x, int y){	
		this.seats[x][y].seatIsTaken(true); 			
	}
	/**
	 * Determines if given seat is taken.
	 * @param x Row number of seat whose availability is to be determined.
	 * @param y Column number of seat whose availability is to be determined.
	 * @return true if seat is taken, false if seat is available.
	 */
	public boolean isSeatOccupied(int x, int y) {
		return this.seats[x][y].getIsTaken();	
	}
	/**
	 * Compares the show times between this cinema show and given cinema show.
	 * @param o Given cinema show.
	 * @return Positive integer if show time of this cinema show is earlier than show time of given cinema show.
	 * 		   Negative integer if show time of this cinema show is later than show time of given cinema show.
	 * 		   0 integer if show time of this cinema show is same as show time of given cinema show.
	 */
	@Override
	public int compareTo(CinemaShow o) {
		return getShowtime().compareTo(o.getShowtime());
	}
	/**
	 * Gets the movie of this cinema show.
	 * @return Movie of this cinema show.
	 */
	public Movie getMovieFromID(){
		for(Movie m : MovieDatabase.getArrayList()){
			if(m.getMovieID() == this.movieID){
				return m;
			}
		}
		return null;
	}
	/**
	 * Gets movie ID of movie showing in this cinema show.
	 * @return This cinema show's movie ID.
	 */
	public int getMovieID() {
		return movieID;
	}
	/**
	 * Set movie ID of this cinema show as given movie ID.
	 * @param movieID New movie ID of this cinema show.
	 */
	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}
	
	
}

	