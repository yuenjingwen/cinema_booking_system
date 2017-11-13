package moblima;

import java.time.format.DateTimeFormatter;
import java.io.Serializable;

public class Ticket implements Serializable {
	
	private static final long serialVersionUID = 84759385764544637L;
	float PRICE_TICKET = 10;
	
	
	private float price;
	private String discount = "None";
	private String cineplex; 
	private String cinema; 
	private String movie; 
	private String time; 
	private String seat;
	private CinemaEnum classOfCinema;
	private String movieDay;
	private MovieType mType;
	
	
	public Ticket(String seat, int age, int cinemaIndex, int cineplexIndex, int showtimeIndex){	//create new constructor. use calculate ticket price to set the price
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		
		
		this.cineplex 		= CineplexDatabase.cineplexList.get(cineplexIndex -1).getName(); //use cineplex index to get
		this.cinema 		= CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaID();; //use cinema index to get
		this.movie 			= CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaShowList().get(showtimeIndex-1).getMovie().getTitle();
		this.time 			= CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaShowList().get(showtimeIndex-1).getShowtime().format(formatter);
		this.seat 			= seat;
		this.classOfCinema	= CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getClassOfCinema();
	    this.movieDay 		= CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaShowList().get(showtimeIndex-1).getShowtime().getDayOfWeek().name();
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



	public float calculateTicketPrice(CinemaEnum cEnum, MovieType mEnum, int age, String movieDay){
		
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
		
		switch(mEnum.toString()){
		case "NORMAL":
			temp += 2;
			break;
		case "BLOCKBUSTER":
			temp += 3;
			break;
		case "THREE_D":
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
		}
		
		switch (movieDay){
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
		
		return PRICE_TICKET + temp;
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
