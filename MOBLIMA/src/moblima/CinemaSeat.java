package moblima;

import java.io.Serializable;

public class CinemaSeat implements Serializable{

	
	private static final long serialVersionUID = -3379918496585890262L;
	/**
	 * The status of the cinema seat. 
	 * This shows if the cinema seat is occupied or not.
	 */
	private boolean isTaken;
	/**
	 * The seat type of the cinema seat. 
	 * Either normal or couple seat. 
	 */
	private SeatType seatType;

	/**
	 * Creates a cinema seat.
	 * Set the status of the cinema seat to unoccupied.
	 * @param seatType The seat type of the created cinema seat.
	 */
	public CinemaSeat(SeatType seatType){
		isTaken = false;
		this.seatType = seatType;
	}
	
	/**
	 * Gets the status of the cinema seat.
	 * Lets user know if the cinema seat is occupied or unoccupied.
	 * @return isTaken The status of the cinema seat.
	 */
	public boolean getIsTaken() {
		return isTaken;
	}
	
	/**
	 * Set the status of the cinema seat to either occupied or unoccupied.
	 * @param isTaken The status of the cinema seat. 
	 */
	public void seatIsTaken(boolean isTaken) {
		this.isTaken = isTaken;
	}
	
	/**
	 * Gets the seat type of this cinema seat.
	 * @return seatType The seat type of this cinema seat.
	 */
	public SeatType getSeatType() {
		return seatType;
	}
	
	
	public static class SeatOccupiedException extends Exception {
		
		private static final long serialVersionUID = -3520635930531900084L;
		
		public SeatOccupiedException() {
			
		}
		
		public SeatOccupiedException(String message){
	        super(message);
	    }
	}
}
