package moblima;

import java.time.LocalDateTime;
import java.io.Serializable;

public class Ticket implements Serializable {
	
	private static final long serialVersionUID = 84759385764544637L;
	private float price;
	private String discount = "None";
	public String cineplex; //new
	public String cinema; //new
	public String movie; //new
	public String time; //new
	public String seat;
	
	
	public Ticket(){
		price=0;
		discount = "None";
		cineplex= "None"; //new
		cinema= "None"; //new
		movie= "None"; //new
		time= null; //new
		seat = "None";
	}
	
	public float getTicketPrice() {
		return price;
	}

	public String getDiscount() {
		return discount;
	}



	public void calculateTicketPrice(CinemaEnum cEnum, MovieType mEnum, int age, int movieDate, boolean student){
		
		float temp = 0;
		
		switch(cEnum){
		case DIGITAL:
			temp += 5;
			break;
		case PLATINIUM:
			temp += 8;
			break;
		default:
			System.out.println("Error in ticket pricing.");
			break;
		}
		
		switch(mEnum){
		case NORMAL:
			temp += 2;
			break;
		case BLOCKBUSTER:
			temp += 3;
			break;
		case THREE_D:
			temp += 5;
			break;
		default:
			System.out.println("Error in ticket pricing.");
			break;
		}
		
		if(age < 12){
			discount = "Child Discount";
			temp -= 2;
		}else if(age > 65){
			discount = "Elderly Discount";
			temp -= 4;
		}else if(student){
			discount = "Student Discount";
			temp -= 2;
		}
		
		
	}
	
	public void setCineplex(String cineplexName){
		cineplex = cineplexName;
	}
	public void setCinema(String cinemaName){
		cinema = cinemaName;
	}
	public void setMovie(String movie){
		this.movie = movie;
	}
	public void setTime(String time){
		this.time = time;
	}
	public void setSeat(String seat){
		this.seat= seat;
	}
	public void printTicket(){
		System.out.println("===========================================================================\n"+
						   "                                   Ticket                                  \n"+
				           "           "+cineplex+ "\n"+
				           "           "+cinema+ "\n\n"+
				           
				           "                                "+movie+ "\n\n" +									
				           "                                                        Time:"+time+ "\n" +
				           "                                                        Seat:"+seat+ "\n" +  
						   "===========================================================================\n");
	}
}
