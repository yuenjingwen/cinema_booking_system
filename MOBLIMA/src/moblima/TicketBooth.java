package moblima;

import java.time.format.DateTimeFormatter;
/**
 * Represents a ticket booth that processes the purchase of a movie ticket.
 */
public class TicketBooth {
	/**
	 * Processes purchase of ticket according to given details such as seat, cinema index,
	 * cineplex index, show time index, name, age, email, mobile number of customer purchasing ticket.
	 * @param seat Seat of ticket to be purchased.
	 * @param age Age of Customer User buying ticket.
	 * @param cinemaIndex Index of cinema of the ticket to be purchased.
	 * @param cineplexIndex Index of cineplex of the ticket to be purchased.
	 * @param showtimeIndex Index of show time of the ticket to be purchased.
	 * @param mobileNum Mobile number of Customer User buying ticket.
	 * @param name Name of Customer User buying ticket.
	 * @param emailAddress Email address of Customer User buying ticket.
	 * 
	 * @return Child ticket if age of Customer User buying ticket is below 12.
	 * 		   Elderly ticket if age of Customer User buying ticket is above 65.
	 * 		   Adult ticket if age of Customer User buying ticket is between 12 and 65 (both inclusive).
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
	/**
	 * Displays printed ticket of given ticket bought by Customer User.
	 * @param ticket Ticket to be printed, bought by Customer User
	 */
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
