package moblima;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;

public abstract class Ticket implements Serializable {
	
	private static final long serialVersionUID = 84759385764544637L;
	
	static float BASE_PRICE_TICKET = 10;
	
	/**
	 * This is the price of the ticket
	 */
	private float price;
	/**
	 * This is the discount for this ticket.
	 */
	private String discount = "None";
	/**
	 * This is the cineplex that this ticket belongs to.
	 */
	private String cineplex; 
	/**
	 * This is the time stated on this ticket. 
	 */
	private String time;
	/**
	 * This is the cinema this ticket belongs to.
	 */
	private String cinema; 
	/**
	 * This is the movie stated on this ticket.
	 */
	private String movie; 
	/**
	 * This is the seat stated on this ticket.
	 */
	private String seat;
	/**
	 * This is the ticket ID of this ticket.
	 */
	private String TID = "XXXYYYYMMDDhhmm";
	
	/**
	 * This is the name of the ticket holder.
	 */
	private String ticketholderName;
	/**
	 * This is the email of the ticket holder. 
	 */
	private String email;
	/**
	 * This is the mobile number of the ticket holder. 
	 */
	private int mobileNumber;
	
	/**
	 * This is the movie type stated on this ticket.
	 */
	private MovieType mType;	
	/**
	 * This is the class of cinema stated on this ticket. 
	 */
	private CinemaEnum classOfCinema;
	/**
	 * This is the day of the movie stated on this ticket. 
	 */
	private LocalDateTime movieDay;
	
	/**
	 * Creates a new ticket. 
	 * @param seat The seat this ticket is for. 
	 * @param age The age of this ticket holder.
	 * @param cinemaIndex The cinema index this ticket is for.
	 * @param cineplexIndex The cineplex index this ticket is for.
	 * @param showtimeIndex The show time index this ticket is for.
	 * @param ticketholderName The name of this ticket holder.
	 * @param email The email of this ticket holder. 
	 * @param mobileNum The mobile number of this ticket holder.
	 */
	public Ticket(String seat, int age, int cinemaIndex, int cineplexIndex, int showtimeIndex, String ticketholderName, String email, int mobileNum){	//create new constructor. use calculate ticket price to set the price
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		
		
		this.cineplex 			= CineplexDatabase.cineplexList.get(cineplexIndex -1).getName(); //use cineplex index to get
		this.cinema 			= CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaID();; //use cinema index to get
		this.movie 				= CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaShowList().get(showtimeIndex-1).getMovieFromID().getTitle();
		this.time 				= CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaShowList().get(showtimeIndex-1).getShowtime().format(formatter);
		this.seat 				= seat;
		this.classOfCinema		= CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getClassOfCinema();
	    this.movieDay 			= CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaShowList().get(showtimeIndex-1).getShowtime();
	    this.ticketholderName	= ticketholderName;
	    this.email 				= email;
	    this.mobileNumber		= mobileNum;
	    this.mType				= CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaShowList().get(showtimeIndex-1).getMovieFromID().getMovieType();		
		this.price 				= calculateTicketPrice();
		this.discount 			= getDiscountName();		
		this.TID 				= this.cinema.concat(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm")));
		
	}
	
	/**
	 * Gets the price of this ticket.
	 * @return price The price of this ticket.
	 */
	public float getTicketPrice() {
		return price;
	}

	/**
	 * Change the price of this ticket.
	 * @param price The updated price of this ticket.
	 */
	public void setTicketPrice(float price) {
		this.price = price;
	}

	/**
	 * Get the ticket ID of this ticket. 
	 * @return TID The ticket ID of this ticket. 
	 */
	public String getTID() {
		return TID;
	}

	/**
	 * Change the ticket ID of this ticket. 
	 * @param tID The ticket ID of this ticket. 
	 */
	public void setTID(String tID) {
		TID = tID;
	}

	/**
	 * Get the email of this ticket's holder. 
	 * @return email The email of this ticket's holder.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Change the email of this ticket's holder. 
	 * @param email The updated email of this ticket's holder.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Get the mobile number of this ticket's holder.
	 * @return mobileNumber The mobile number of this ticket's holder.
	 */
	public int getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * Change the mobile number of this ticket's holder.
	 * @param mobileNumber
	 */
	public void setMobileNumber(int mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	/**
	 * Get the class of cinema stated in this ticket.
	 * @return classOfCinema The class of cinema stated in this ticket.
	 */
	public CinemaEnum getClassOfCinema() {
		return classOfCinema;
	}

	/**
	 * Change the class of cinema stated in this ticket.
	 * @param classOfCinema The updated class of cinema stated in this ticket.
	 */
	public void setClassOfCinema(CinemaEnum classOfCinema) {
		this.classOfCinema = classOfCinema;
	}

	/**
	 * Get the day of the movie stated on this ticket.
	 * @return movieDay The day of the movie stated on this ticket.
	 */
	public LocalDateTime getMovieDay() {
		return movieDay;
	}

	/**
	 * Change the day of the movie stated on this ticket.
	 * @param movieDay The updated day of the movie stated on this ticket. 
	 */
	public void setMovieDay(LocalDateTime movieDay) {
		this.movieDay = movieDay;
	}

	/**
	 * Get the type of movie stated on this ticket. 
	 * @return mType The type of movie stated on this ticket.
	 */
	public MovieType getmType() {
		return mType;
	}

	/**
	 * Change the type of movie stated on this ticket.
	 * @param mType The updated type of movie stated on this ticket.
	 */
	public void setmType(MovieType mType) {
		this.mType = mType;
	}

	/**
	 * Get the cineplex this ticket belongs to.
	 * @return cineplex The cineplex this ticket belongs to.
	 */
	public String getCineplex() {
		return cineplex;
	}

	/**
	 * Get the time stated on this ticket. 
	 * @return time The time stated on this ticket.
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Get the cinema this ticket belongs to.
	 * @return cinema The cinema this ticket belongs to.
	 */
	public String getCinema() {
		return cinema;
	}

	/**
	 * Get the movie stated on this ticket.
	 * @return movie The movie stated on this ticket.
	 */
	public String getMovie() {
		return movie;
	}

	/**
	 * Get the seat stated on this ticket.
	 * @return seat The seat stated on this ticket.
	 */
	public String getSeat() {
		return seat;
	}

	/**
	 * Change the name of this ticket's holder. 
	 * @param ticketholderName The updated name of this ticket's holder. 
	 */
	public void setTicketholderName(String ticketholderName) {
		this.ticketholderName = ticketholderName;
	}

	/**
	 * Get the name of discount for this ticket.
	 * @return discount The name of discount for this ticket. 
	 */
	public String getDiscountName() {
		return discount;
	}
		
	/**
	 * Get the type of discount for different cinema types for this ticket. 
	 * @param cEnum The enum for the type of cinema of this ticket. 
	 */
	public int getCinemaTypeDiscount(CinemaEnum cEnum) {
		switch(cEnum){
		case DIGITAL:
			try {
			return TicketDatabase.searchDiscountByName("Digital");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			break;
		case PLATINIUM:
			try {
				return TicketDatabase.searchDiscountByName("Platinum");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			break;
		default:
			System.out.println("Error in ticket pricing.");
			break;
		}
		return 0;
	}
	
	/**
	 * Get the type of discount for different movie types for this ticket. 
	 * @param movie The movie stated on this ticket.
	 */
	public int getMovieTypeDiscount(String movie) {
			
		try {
			return MovieDatabase.searchForMovie(movie).getMovieDiscount();
			
		} catch (Exception e) {
			return 0;
		}
	}
	
	/**
	 * Get the type of discount for different weekend discounts for this ticket.
	 * @param time The time stated on this ticket. 
	 */
	public int getWeekendDiscount(LocalDateTime time) {
		if (time.getDayOfWeek().equals(DayOfWeek.SATURDAY) || time.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
			try {
				return TicketDatabase.searchDiscountByName("Weekend");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			return 0;
		}
		return 0;

	}
	
	/**
	 * Get the type of discount for different public holiday discounts for this ticket. 
	 * @param time The time stated on this ticket. 
	 */
	public int getPublicHolidayDiscount(LocalDateTime time) {
		MonthDay tempMD = MonthDay.from(time);
		
		for (PublicHoliday ph: PublicHolidayDatabase.getArrayList()) {
			if (tempMD.compareTo(ph.getDate()) == 0) {
				try {
					return TicketDatabase.searchDiscountByName("Public Holiday");
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
			}
		}
		return 0;
	}
	
	/**
	 * Get this ticket holder's name.
	 * @return ticketholderName This ticket holder's name.
	 */
	public String getTicketholderName() {
		return ticketholderName;
	}

	/**
	 * Get the type of movie stated on this ticket. 
	 * @return mType The type of movie stated on this ticket. 
	 */
	public MovieType getMovieType() {
		return mType;
	}
	
	/**
	 * Get the type of cinema stated on this ticket. 
	 * @return classOfCinema The type of cinema stated on this ticket. 
	 */
	public CinemaEnum getCinemaType(){
		return classOfCinema;
	}
	

	/**
	 * Change the name of the cineplex this ticket belongs to.
	 * @param cineplexName The name of this cineplex this ticket belongs to.
	 */
	public void setCineplex(String cineplexName){
		cineplex = cineplexName;
	}
	
	/**
	 * Change the name of the cinema this ticket belongs to. 
	 * @param cinemaName The updated name of the cinema this ticket belongs to.
	 */
	public void setCinema(String cinemaName){
		cinema = cinemaName;
	}

	/**
	 * Change the movie name stated on this ticket. 
	 * @param movie The movie name stated on this ticket. 
	 */
	public void setMovie(String movie){
		this.movie = movie;
	}
	
	/**
	 * Change the time stated on this ticket.
	 * @param time The updated time stated on this ticket. 
	 */
	public void setTime(String time){
		this.time = time;
	}
	
	/**
	 * Change the seat stated on this ticket. 
	 * @param seat The seat stated on this ticket.
	 */
	public void setSeat(String seat){
		this.seat= seat;
	}
	
	/**
	 * This calculates the generic ticket discount.
	 * @return temp The total generic discount. 
	 */
	public float calculateGenericTicketDiscount() {
			float temp = 0;
		
		temp = 	getCinemaTypeDiscount(getCinemaType()) + 
				getMovieTypeDiscount(getMovie()) + 
				getWeekendDiscount(getMovieDay()) + 
				getPublicHolidayDiscount(getMovieDay());
		
		
		return temp;
		
		
		// Legacy code for discounting ticket
		/*
		 
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
			if (tempMD.compareTo(ph.getDate()) == 0) {
				try {
					temp += TicketDatabase.searchDiscountByName("Public Holiday");
					break;
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
			}
		}
		
		*/
		
		
	}
	
	/**
	 * An abstract method to calculate the ticket price. 
	 * This method will be over-ridden in the respective ticket sub classes. 
	 * @return
	 */
	public abstract float calculateTicketPrice();
	
}
