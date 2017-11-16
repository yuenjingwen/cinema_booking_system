package moblima;

import java.io.Serializable;
import java.time.MonthDay;

public class PublicHoliday implements Serializable, Comparable<PublicHoliday>{

	private static final long serialVersionUID = 7423582918178200639L;
	/**
	 * The description of this public holiday.
	 */
	private String description;
	/**
	 * The date of this public holiday.
	 */
	private MonthDay date;
	
	/**
	 * Creates a new public holiday with its description and date. 
	 * @param description The description of this public holiday.
	 * @param date The date of this public holiday. 
	 */
	public PublicHoliday(String description, MonthDay date) {
		this.description = description;
		this.date = date;
	}

	/**
	 * Gets the description of this public holiday.
	 * @return description The description of this public holiday. 
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Changes the description of this public holiday.
	 * @param description The updated description of this public holiday.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the date of this public holiday.
	 * @return date The date of this public holiday.
	 */
	public MonthDay getDate() {
		return date;
	}

	/**
	 * Changes the date of this public holiday.
	 * @param date The updated date of this public holiday.
	 */
	public void setDate(MonthDay date) {
		this.date = date;
	}

	/* Compares the show times between this cinema show and given cinema show.
	 * @param o Given cinema show.
	 * @return Positive integer if show time of this cinema show is earlier than show time of given cinema show.
	 * 		   Negative integer if show time of this cinema show is later than show time of given cinema show.
	 * 		   0 integer if show time of this cinema show is same as show time of given cinema show.
	
	*/
	
	/**
	 * Compares the date between this public holiday and a given public holiday.
	 * @param o Given public holiday.
	 * @return 
	 */
	@Override
	public int compareTo(PublicHoliday o) {
		return getDate().compareTo(o.getDate());
	}
}
