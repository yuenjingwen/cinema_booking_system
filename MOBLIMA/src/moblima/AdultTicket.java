package moblima;

public class AdultTicket extends Ticket{
	
		/**
	 * 
	 */
	private static final long serialVersionUID = -4356253491233642936L;

	/**
	 * Creates a new adult ticket. 
	 * Takes in parameters and sets price of the Adult Ticket 
	 * to the appropriate pricing based on the parameters.
	 * @param seat seat is a String stating the seat of the Ticket. e.g: A1, B7, G2
	 * @param age Age of the customer.
	 * @param cinemaIndex Which cinema the ticket is assigned to.
	 * @param cineplexIndex Which cineplex the ticket is assigned to.
	 * @param showtimeIndex Which showtime the ticket is assigned to.
	 * @param ticketholderName The customer's name.
	 * @param email The adult's email.
	 * @param mobileNum The adult's contact.
	 */

	public AdultTicket(String seat, int age, int cinemaIndex, int cineplexIndex, int showtimeIndex,
			String ticketholderName, String email, int mobileNum) {
		super(seat, age, cinemaIndex, cineplexIndex, showtimeIndex, ticketholderName, email, mobileNum);
		
		super.setTicketPrice(this.calculateTicketPrice());
	}

	/**
	 * Calculates the appropriate ticket price based on the adult's age. 
	 * Overrides the Ticket object's calculateTicketPrice() function. 
	 */
	@Override
	public float calculateTicketPrice() {
		
		float temp = 0;
		
		temp += super.calculateGenericTicketDiscount();
		
		return BASE_PRICE_TICKET + temp;
	}

	

}
