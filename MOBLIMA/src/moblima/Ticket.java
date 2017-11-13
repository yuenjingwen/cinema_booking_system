package moblima;

import java.time.LocalDateTime;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;

public class Ticket implements Serializable {
	
	private static final long serialVersionUID = 84759385764544637L;
	static float BASE_PRICE_TICKET = 10;
	
	
	private float price;
	private String discount = "None";
	private String cineplex; 
	private String cinema; 
	private String movie; 
	private String time; 
	private String seat;
	
	private String ticketholderName;
	private String email;
	private int mobileNumber;
	
	private CinemaEnum classOfCinema;
	private LocalDateTime movieDay;
	private MovieType mType;
	
	
	public Ticket(String seat, int age, int cinemaIndex, int cineplexIndex, int showtimeIndex){	//create new constructor. use calculate ticket price to set the price
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		
		
		this.cineplex 		= CineplexDatabase.cineplexList.get(cineplexIndex -1).getName(); //use cineplex index to get
		this.cinema 		= CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaID();; //use cinema index to get
		this.movie 			= CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaShowList().get(showtimeIndex-1).getMovie().getTitle();
		this.time 			= CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaShowList().get(showtimeIndex-1).getShowtime().format(formatter);
		this.seat 			= seat;
		this.classOfCinema	= CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getClassOfCinema();
	    this.movieDay 		= CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaShowList().get(showtimeIndex-1).getShowtime();
	    this.mType			= CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaShowList().get(showtimeIndex-1).getMovie().getMovieType();		
		this.price 			= calculateTicketPrice(classOfCinema, mType, age, movieDay);
		this.discount 		= getDiscount();
			
	}
	
	public float getTicketPrice() {
		return price;
	}

	public String getDiscount() {
		return discount;
	}



	public float calculateTicketPrice(CinemaEnum cEnum, MovieType mEnum, int age, LocalDateTime movieDay){
		
		float temp = 0;
		
		switch(cEnum){
		case DIGITAL:
			try {
			temp += TicketDatabase.getDiscount("Digital");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			break;
		case PLATINIUM:
			try {
				temp += TicketDatabase.getDiscount("Platinum");
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
				temp += TicketDatabase.getDiscount("Normal");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			break;
		case "BLOCKBUSTER":
			try {
				temp += TicketDatabase.getDiscount("Blockbuster");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			break;
		case "THREE_D":
			try {
				temp += TicketDatabase.getDiscount("3D");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			break;
		default:
			System.out.println("Error in ticket pricing.");
			break;
		}
		
		if(age < 12){
			discount = "Child Discount";
			try {
				temp -= TicketDatabase.getDiscount("Child Discount");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}else if(age > 65){
			discount = "Elderly Discount";
			try {
				temp -= TicketDatabase.getDiscount("Elderly Discount");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
		
		switch (movieDay.getDayOfWeek().toString()){
		case "MONDAY":
		case "TUESDAY":
		case "WEDNESDAY":
		case "THURSDAY":
		case "FRIDAY":
			break;
		case "SATURDAY": 
		case "SUNDAY": temp += 2;
			break;
		default: 
			System.out.println("Error in ticket pricing.");
			break;
					
		}
		
		
		//checks if the movie date lands on a public holiday and adds the appropriate charges accordingly
		MonthDay tempMD = MonthDay.from(movieDay);
		
		for (PublicHoliday ph: PublicHolidaysDatabase.getArrayList()) {
			if (tempMD == ph.getDate()) {
				try {
					temp += TicketDatabase.getDiscount("Public Holiday");
					break;
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
			}
		}
		
		return BASE_PRICE_TICKET + temp;
	}
	
	public void setCineplex(String cineplexName){
		cineplex = cineplexName;
	}
	public void setCinema(String cinemaName){
		cinema = cinemaName;
	}
	public void setMovie(String movie){
		this.movie = movie;
	}
	public void setTime(String time){
		this.time = time;
	}
	public void setSeat(String seat){
		this.seat= seat;
	}
	public void printTicket(){
		System.out.println("===========================================================================\n"+
						   "                                   Ticket                                  \n"+
				           "           "+cineplex+ "\n"+
				           "           "+cinema+ "\n\n"+
				           
				           "                                "+movie+ "\n\n" +			
				           "							Price:" + this.getTicketPrice() + "\n" +
				           "							Time:"+time+ "\n" +
				           "							Seat:"+seat+ "\n" +  
						   "===========================================================================\n");
	}
}
