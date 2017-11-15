package moblima;

public class ElderlyTicket extends Ticket{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8697050021125495205L;

	public ElderlyTicket(String seat, int age, int cinemaIndex, int cineplexIndex, int showtimeIndex,
			String ticketholderName, String email, int mobileNum) {
		super(seat, age, cinemaIndex, cineplexIndex, showtimeIndex, ticketholderName, email, mobileNum);
		
	}

}
