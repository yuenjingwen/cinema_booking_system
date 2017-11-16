package moblima;

public class ElderlyTicket extends Ticket{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8697050021125495205L;

	/**
	 * Creates a new elderly ticket. 
	 * Takes in parameters and sets price of the Elderly Ticket 
	 * to the appropriate pricing based on the parameters.
	 * @param seat seat is a String stating the seat of the Ticket. e.g: A1, B7, G2
	 * @param age Age of the elderly.
	 * @param cinemaIndex Which cinema the ticket is assigned to.
	 * @param cineplexIndex Which cineplex the ticket is assigned to.
	 * @param showtimeIndex Which showtime the ticket is assigned to.
	 * @param ticketholderName The elderly's name.
	 * @param email The elderly's email.
	 * @param mobileNum The elderly's contact.
	 */
	public ElderlyTicket(String seat, int age, int cinemaIndex, int cineplexIndex, int showtimeIndex,
			String ticketholderName, String email, int mobileNum) {
		super(seat, age, cinemaIndex, cineplexIndex, showtimeIndex, ticketholderName, email, mobileNum);
		
		super.setTicketPrice(this.calculateTicketPrice());
		
	}

	/**
	 * Calculates the appropriate ticket price based on the elderly's age. 
	 * Overrides the Ticket object's calculateTicketPrice() function. 
	 */
	@Override
	public float calculateTicketPrice() {
		
		float temp = 0;
		
		try {
			temp += TicketDatabase.searchDiscountByName("Elderly Discount");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		temp += super.calculateGenericTicketDiscount();
		return BASE_PRICE_TICKET + temp;
	}

	

	

}
