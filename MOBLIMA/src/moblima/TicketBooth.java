package moblima;

import java.time.format.DateTimeFormatter;

public class TicketBooth {

	public Ticket buyTicket() {
		return 0;
	}
	
	public void printTicket(Ticket ticket){
		System.out.println("============================================");
		System.out.println("~~~~~~~~~~~~~~~~~~~Ticket~~~~~~~~~~~~~~~~~~~");
		System.out.println("	Customer: " + ticket.getTicketholderName() + "		");
		System.out.println("	Cineplex: " + cineplex + "				");
		System.out.println("	Cinema: " + cinema + "					");
		System.out.println("											");
		System.out.println("	Movie: " + this.movie + "				");
		System.out.println("	Date: " + this.movieDay.format(DateTimeFormatter.ofPattern("dd-MM-YYYY")));
		System.out.println("	Time: " + this.time + "					");
		System.out.println("	Seat: " + this.seat + "					");
		System.out.println("											");
		System.out.println("											");
		System.out.println("	Email: " + email + "					");
		System.out.println("	Number: " + mobileNumber + "			");	
		System.out.println("	Transaction ID: " + this.TID + "		");

	}
	
	
}
