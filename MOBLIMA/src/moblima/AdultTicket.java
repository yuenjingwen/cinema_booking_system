package moblima;

public class AdultTicket extends Ticket{
	
		/**
	 * 
	 */
	private static final long serialVersionUID = -4356253491233642936L;


	public AdultTicket(String seat, int age, int cinemaIndex, int cineplexIndex, int showtimeIndex,
			String ticketholderName, String email, int mobileNum) {
		super(seat, age, cinemaIndex, cineplexIndex, showtimeIndex, ticketholderName, email, mobileNum);
		
		super.setTicketPrice(this.calculateTicketPrice());
	}

	@Override
	public float calculateTicketPrice() {
		
		float temp = 0;
		
		temp += super.calculateGenericTicketDiscount();
		
		return BASE_PRICE_TICKET + temp;
	}

}
