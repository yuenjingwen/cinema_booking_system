package moblima;

import java.io.Serializable;
import java.util.ArrayList;

public class Cineplex implements Serializable{
	
	private static final long serialVersionUID = 3346347368664976796L;
	/**
	 * The initial number of cinemas in this cineplex.
	 */
	private int numOfCinema = 3;
	/**
	 * The name of this cineplex.
	 */
	private String name;
	/**
	 * An array list containing all the cinemas in this cineplex. 
	 */
	private ArrayList<Cinema> cinemaList;
	
	/**
	 * Creates a new cineplex with the given name and a given number of cinema(s). 
	 * @param name The name of this cineplex.
	 * @param num The number of cinemas in this cineplex. 
	 */
	public Cineplex(String name, int num) {
		this.name = name;
		numOfCinema = num;
		cinemaList = new ArrayList<Cinema>();
	}
	
	/**
	 * Gets the name of this cineplex.
	 * @return name This cineplex's name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Change the name of this cineplex.
	 * @param name The new updated name of this cineplex.
	 */
	public void setName(String name) {
		this.name = name;
		}
	
	/**
	 * Gets the number of cinemas in this cineplex.
	 * @return numOfCinema The number of cinemas in this cineplex.
	 */
	public int getNumOfCinema() {
		return numOfCinema;
	}
	
	/**
	 * Change the number of cinemas in this cineplex.
	 * @param num The updated number of cinemas in this cineplex. 
	 */
	public void setNumOfCinema(int num) {
		numOfCinema = num;
	}
	
	/**
	 * Gets the array list containing all the cinemas for this cineplex. 
	 * @return cinemaList The array list containing all the cinemas for this cineplex.
	 */
	public ArrayList<Cinema> getCinemaList() {
		return cinemaList;
	}	
	
}