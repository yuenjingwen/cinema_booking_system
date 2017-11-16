package moblima;

public class ChildTicket extends Ticket{


	/**
	 * 
	 */
	private static final long serialVersionUID = -3438706294914093104L;

	public ChildTicket(String seat, int age, int cinemaIndex, int cineplexIndex, int showtimeIndex,
			String ticketholderName, String email, int mobileNum) {
		super(seat, age, cinemaIndex, cineplexIndex, showtimeIndex, ticketholderName, email, mobileNum);
		
		super.setTicketPrice(this.calculateTicketPrice());
		
	}

	@Override
	public float calculateTicketPrice() {
		
		float temp = 0;
		
		try {
			temp += TicketDatabase.searchDiscountByName("Child Discount");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		temp += super.calculateGenericTicketDiscount();

		
		return BASE_PRICE_TICKET + temp;
	}



	
	

}
