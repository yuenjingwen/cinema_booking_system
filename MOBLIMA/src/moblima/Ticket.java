package moblima;

import java.time.LocalDateTime;

public class Ticket {
	
	
	private float price;
	private String discount = "None";
	private CinemaEnum cinemaTier;
	private MovieType movieType;
	private int ticketholderAge;
	private LocalDateTime ticketDate;
	
public Ticket(CinemaEnum cEnum, MovieType mEnum, int age, LocalDateTime movieDate, boolean student){
		
		cinemaTier = cEnum;
		movieType = mEnum;
		ticketholderAge = age;
		ticketDate = movieDate;
		
		
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
			student = false;
			temp -= 2;
		}else if(age > 65){
			discount = "Elderly Discount";
			student = false;
			temp -= 4;
		}else if(student){
			discount = "Student Discount";
			student = true;
			temp -= 2;
		}
		
		price = temp;
		
	}

	public float getTicketPrice() {
		return price;
	}

	public String getDiscount() {
		return discount;
	}

	public CinemaEnum getCinemaTier() {
		return cinemaTier;
	}
	
	public MovieType getMovieType() {
		return movieType;
	}
	
	public int getTicketholderAge() {
		return ticketholderAge;
	}
	
	public LocalDateTime getTicketDate() {
		return ticketDate;
	}

}
