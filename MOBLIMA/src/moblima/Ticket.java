package moblima;

public class Ticket {
	
	private float price;
	private String discount = "None";

	public float getTicketPrice() {
		return price;
	}

	public String getDiscount() {
		return discount;
	}



	public Ticket(CinemaEnum cEnum, MovieType mEnum, int age, int movieDate, boolean student){
		
		float temp = 0;
		
		switch(cEnum){
		case DIGITAL:
			temp += 5;
			break;
		case PLATINIUM:
			temp += 8;
			break;
		default:
			System.out.println("Error in ticket pricing.");
			break;
		}
		
		switch(mEnum){
		case NORMAL:
			temp += 2;
			break;
		case BLOCKBUSTER:
			temp += 3;
			break;
		case THREE_D:
			temp += 5;
			break;
		default:
			System.out.println("Error in ticket pricing.");
			break;
		}
		
		if(age < 12){
			discount = "Child Discount";
			temp -= 2;
		}else if(age > 65){
			discount = "Elderly Discount";
			temp -= 4;
		}else if(student){
			discount = "Student Discount";
			temp -= 2;
		}
		
		
	}
}
