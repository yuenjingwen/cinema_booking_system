package moblima;

import java.time.LocalDateTime;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;

public abstract class Ticket implements Serializable {
	
	private static final long serialVersionUID = 84759385764544637L;
	
	static float BASE_PRICE_TICKET = 10;
	
	
	private float price;
	private String discount = "None";
	private String cineplex; 
	private String time;
	private String cinema; 
	private String movie; 
	private String seat;
	private String TID = "XXXYYYYMMDDhhmm";
	
	private String ticketholderName;
	private String email;
	private int mobileNumber;
	
	private MovieType mType;	
	private CinemaEnum classOfCinema;
	private LocalDateTime movieDay;
	
	public Ticket(String seat, int age, int cinemaIndex, int cineplexIndex, int showtimeIndex, String ticketholderName, String email, int mobileNum){	//create new constructor. use calculate ticket price to set the price
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		
		
		this.cineplex 			= CineplexDatabase.cineplexList.get(cineplexIndex -1).getName(); //use cineplex index to get
		this.cinema 			= CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaID();; //use cinema index to get
		this.movie 				= CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaShowList().get(showtimeIndex-1).getMovie().getTitle();
		this.time 				= CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaShowList().get(showtimeIndex-1).getShowtime().format(formatter);
		this.seat 				= seat;
		this.classOfCinema		= CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getClassOfCinema();
	    this.movieDay 			= CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaShowList().get(showtimeIndex-1).getShowtime();
	    this.ticketholderName	= ticketholderName;
	    this.email 				= email;
	    this.mobileNumber		= mobileNum;
	    this.mType				= CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaShowList().get(showtimeIndex-1).getMovie().getMovieType();		
		this.price 				= calculateTicketPrice();
		this.discount 			= getDiscount();		
		this.TID 				= this.cinema.concat(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm")));
		
	}
	
	public float getTicketPrice() {
		return price;
	}

	public void setTicketPrice(float price) {
		this.price = price;
	}

	public String getTID() {
		return TID;
	}

	public void setTID(String tID) {
		TID = tID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(int mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public CinemaEnum getClassOfCinema() {
		return classOfCinema;
	}

	public void setClassOfCinema(CinemaEnum classOfCinema) {
		this.classOfCinema = classOfCinema;
	}

	public LocalDateTime getMovieDay() {
		return movieDay;
	}

	public void setMovieDay(LocalDateTime movieDay) {
		this.movieDay = movieDay;
	}

	public MovieType getmType() {
		return mType;
	}

	public void setmType(MovieType mType) {
		this.mType = mType;
	}

	public String getCineplex() {
		return cineplex;
	}

	public String getTime() {
		return time;
	}

	public String getCinema() {
		return cinema;
	}

	public String getMovie() {
		return movie;
	}

	public String getSeat() {
		return seat;
	}

	public void setTicketholderName(String ticketholderName) {
		this.ticketholderName = ticketholderName;
	}


	public String getDiscount() {
		return discount;
	}
	public abstract float calculateTicketPrice();
	
	public String getTicketholderName() {
		return ticketholderName;
	}

	public MovieType getMovieType() {
		return mType;
	}
	
	public CinemaEnum getCinemaType(){
		return classOfCinema;
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
	
	public float calculateGenericTicketDiscount() {
			float temp = 0;
		
		switch(getCinemaType()){
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
		
		switch(getMovieType().toString()){
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
		
		switch (getMovieDay().getDayOfWeek().toString()){
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
		MonthDay tempMD = MonthDay.from(getMovieDay());
		
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
		
		return temp;
	}
	
}
