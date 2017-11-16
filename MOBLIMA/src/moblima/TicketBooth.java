package moblima;

import java.time.format.DateTimeFormatter;
/**
 * Represents a ticket booth that processes the purchase of a movie ticket.
 */
public class TicketBooth {
	/**
	 * Processes purchase of ticket according to given details such as seat, cinema index,
	 * cineplex index, show time index, name, age, email, mobile number of customer purchasing ticket.
	 * @param 
	 */
	public Ticket buyTicket(String seat, int age, int cinemaIndex, int cineplexIndex, int showtimeIndex,
			 int mobileNum, String name, String emailAddress) {
		
		
		if(age < 12){
			ChildTicket cTicket = new ChildTicket(seat, age, cinemaIndex, cineplexIndex, showtimeIndex, name, emailAddress, mobileNum);
			return cTicket;
		}else if(age > 65){
			ElderlyTicket eTicket = new ElderlyTicket(seat, age, cinemaIndex, cineplexIndex, showtimeIndex, name, emailAddress, mobileNum);
			return eTicket;
		}else{
			AdultTicket aTicket = new AdultTicket(seat, age, cinemaIndex, cineplexIndex, showtimeIndex, name, emailAddress, mobileNum);
			return aTicket;
		}
	}
	
	public void printTicket(Ticket ticket){
		System.out.println("============================================");
		System.out.println("~~~~~~~~~~~~~~~~~~~Ticket~~~~~~~~~~~~~~~~~~~");
		System.out.println("	Customer: " + ticket.getTicketholderName());
		System.out.println("	Cineplex: " + ticket.getCineplex());
		System.out.println("	Cinema: " + ticket.getCinema());
		System.out.println("											");
		System.out.println("	Movie: " + ticket.getMovie());
		System.out.println("	Date: " + ticket.getMovieDay().format(DateTimeFormatter.ofPattern("dd-MM-YYYY")));
		System.out.println("	Time: " + ticket.getTime());
		System.out.println("	Seat: " + ticket.getSeat() + "					");
		System.out.println("											");
		System.out.println("	Price: $" + ticket.getTicketPrice()	+ " ");
		System.out.println("											");
		System.out.println("	Email: " + ticket.getEmail()	+ "		");
		System.out.println("	Number: " + ticket.getMobileNumber() + "");	
		System.out.println("	Transaction ID: " + ticket.getTID() + "		");

	}
	
	
}
