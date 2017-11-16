package moblima;

import java.time.format.DateTimeFormatter;

public class TicketBooth {

	public Ticket buyTicket(String seat, int age, int cinemaIndex, int cineplexIndex, int showtimeIndex,
			String ticketholderName, String email, int mobileNum, String name, String emailAddress) {
		if(age < 12){
			return new ChildTicket(seat, age, cinemaIndex, cineplexIndex, showtimeIndex, name, emailAddress, mobileNum);
		}else if(age > 65){
			return new ElderlyTicket(seat, age, cinemaIndex, cineplexIndex, showtimeIndex, name, emailAddress, mobileNum);
		}else{
			return new AdultTicket(seat, age, cinemaIndex, cineplexIndex, showtimeIndex, name, emailAddress, mobileNum);
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
		System.out.println("											");
		System.out.println("	Email: " + ticket.getEmail()	+ "		");
		System.out.println("	Number: " + ticket.getMobileNumber() + "");	
		System.out.println("	Transaction ID: " + ticket.getTID() + "		");

	}
	
	
}
