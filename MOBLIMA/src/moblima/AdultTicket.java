package moblima;

import java.time.LocalDateTime;
import java.time.MonthDay;

public class AdultTicket extends Ticket{

<<<<<<< HEAD
	static {BASE_PRICE_TICKET = 10;}
	
=======
	/**
	 * 
	 */
	private static final long serialVersionUID = -4356253491233642936L;

>>>>>>> branch 'master' of https://github.com/jirehcwe/MOBLIMA.git
	public AdultTicket(String seat, int age, int cinemaIndex, int cineplexIndex, int showtimeIndex,
			String ticketholderName, String email, int mobileNum) {
		super(seat, age, cinemaIndex, cineplexIndex, showtimeIndex, ticketholderName, email, mobileNum);
<<<<<<< HEAD
	}

	@Override
	public float calculateTicketPrice(CinemaEnum cEnum, MovieType mEnum, int age, LocalDateTime movieDay) {
		
		float temp = 0;
		
		switch(cEnum){
		case DIGITAL:
			try {
			temp += TicketDatabase.searchDiscountByName("Digital");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			break;
		case PLATINIUM:
			try {
				temp += TicketDatabase.searchDiscountByName("Platinum");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			break;
		default:
			System.out.println("Error in ticket pricing.");
			break;
		}
		
		switch(mEnum.toString()){
		case "NORMAL":
			try {
				temp += TicketDatabase.searchDiscountByName("Normal");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			break;
		case "BLOCKBUSTER":
			try {
				temp += TicketDatabase.searchDiscountByName("Blockbuster");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			break;
		case "THREE_D":
			try {
				temp += TicketDatabase.searchDiscountByName("3D");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			break;
		default:
			System.out.println("Error in ticket pricing.");
			break;
		}
		
//		if(age < 12){
//			discount = "Child Discount";
//			try {
//				temp += TicketDatabase.searchDiscountByName("Child Discount");
//			} catch (Exception e) {
//				e.printStackTrace();
//				System.out.println(e.getMessage());
//			}
//		}else if(age > 65){
//			discount = "Elderly Discount";
//			try {
//				temp += TicketDatabase.searchDiscountByName("Elderly Discount");
//			} catch (Exception e) {
//				e.printStackTrace();
//				System.out.println(e.getMessage());
//			}
//		}
		
		switch (movieDay.getDayOfWeek().toString()){
		case "MONDAY":
		case "TUESDAY":
		case "WEDNESDAY":
		case "THURSDAY":
		case "FRIDAY":
			break;
		case "SATURDAY": 
		case "SUNDAY": try {
				temp += TicketDatabase.searchDiscountByName("Weekend");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			break;
		default: 
			System.out.println("Error in ticket pricing.");
			break;
					
		}
		
	
		
		//checks if the movie date lands on a public holiday and adds the appropriate charges accordingly
		MonthDay tempMD = MonthDay.from(movieDay);
		
		for (PublicHoliday ph: PublicHolidayDatabase.getArrayList()) {
			if (tempMD == ph.getDate()) {
				try {
					temp += TicketDatabase.searchDiscountByName("Public Holiday");
					break;
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
			}
		}
		
		return BASE_PRICE_TICKET + temp;
=======
>>>>>>> branch 'master' of https://github.com/jirehcwe/MOBLIMA.git
	}

}
