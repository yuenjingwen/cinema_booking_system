package moblima;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * Represents a cinema that shows different movies at different showtimes.
 * Each cinema has a class type that changes the price of ticket bought for a movie showing 
 * in that cinema.
 */
public class Cinema implements Serializable{
	
	/**
	 * Version number of the serializable class "Cinema".
	 */
	private static final long serialVersionUID = 528082458209220230L;
	/**
	 * The ArrayList of object "CinemaShow" in the cinema.
	 */
	private ArrayList<CinemaShow> CinemaShowList;
	/**
	 * The class of this cinema.
	 */
	private CinemaEnum classOfCinema;
	/**
	 * The ID of this cinema.
	 */
	private String cinemaID;

	/**
	 * Creates a new ArrayList of objects "CinemaShow" with given class and ID of this cinema.
	 * 
	 * @param classOfCinema This cinema's class.
	 * @param cinemaID		This cinema's ID.
	 */
	public Cinema(CinemaEnum classOfCinema, String cinemaID) {
		CinemaShowList = new ArrayList<CinemaShow>();
		this.classOfCinema = classOfCinema;
		this.cinemaID = cinemaID;
	}
	/**
	 * Gets the ArrayList of object "CinemaShow" of this cinema.
	 * @return This cinema's ArrayList of "CinemaShow".
	 */
	public ArrayList<CinemaShow> getCinemaShowList() {
		return CinemaShowList;
	}
	/**
	 * Gets the class of this cinema.
	 * @return This cinema's class.
	 */
	public CinemaEnum getClassOfCinema() {
		return classOfCinema;
	}
	/**
	 * Gets the ID of this cinema.
	 * @return This cinema's ID.
	 */
	public String getCinemaID() {
		return cinemaID;
	}

	
	/**
	 * Sets the ID of this cinema.
	 * @param cinemaID This cinema's ID.
	 */
	public void setCinemaID(String cinemaID) {
		this.cinemaID = cinemaID;
	}

	/**
	 * Adds a new show to the ArrayList of "CinemaShow" of this cinema.
	 * @param show This cinema's new show.
	 */
	public void addCinemaShow(CinemaShow show){
		CinemaShowList.add(show);
	}
	/**
	 * Removes a show from the ArrayList of "CinemaShow" of this cinema.
	 * @param index Index of show to be removed.
	 */
	public void removeCinemaShow(int index){
		CinemaShowList.remove(index - 1);
	}
}
