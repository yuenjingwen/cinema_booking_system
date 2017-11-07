package moblima;

import java.io.Serializable;
import java.util.ArrayList;

public class Cineplex implements Serializable{
	
	private static final long serialVersionUID = 3346347368664976796L;
	private String name;
	private ArrayList<Cinema> cinemaList;
	
	public String getName() {
		return name;
	}
	
	public Cineplex(String name) {
		this.name = name;
		cinemaList = new ArrayList<Cinema>();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Cinema> getCinemaList() {
		return cinemaList;
	}	
	
}