package moblima;

public class AdultTicket extends Ticket{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4356253491233642936L;

	public AdultTicket(String seat, int age, int cinemaIndex, int cineplexIndex, int showtimeIndex,
			String ticketholderName, String email, int mobileNum) {
		super(seat, age, cinemaIndex, cineplexIndex, showtimeIndex, ticketholderName, email, mobileNum);
	}

}
