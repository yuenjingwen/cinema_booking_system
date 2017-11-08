package moblima;

import java.time.LocalDateTime;

public class DBManager {
	

	//========================================================================
	//Cineplex methods
	
	public void addShowTime(Movie movie, LocalDateTime dt) {
		
	}
	
	public void deleteShowTime(int stIndex) {
		
	}
	
	public void editShowTime(Movie movie, LocalDateTime dt) {
		
	}
	
	public void addSeating(int x, int y, SeatType st) {
		
	}
	
	

	//========================================================================
	//Ticket Database methods
	
	public void addTicket(Ticket temp) {
		
	}
	

	//========================================================================
	//Public Holiday Database methods
	
	public void addPH(LocalDateTime dt, String description) {
		
	}
	
	public void deletePH(int phIndex) {
		
	}
	
	
	//========================================================================
	//Review Database methods
	
	public void addReview(String reviewText, int rating) {
		
	}
	
	public void deleteReview(int reviewIndex) {
		
	}
	
	public void editReview(int reviewIndex, String reviewText, int rating) {
		
	}
}
