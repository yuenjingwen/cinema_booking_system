package moblima;

import java.io.Serializable;

public class PublicHoliday implements Serializable{

	private static final long serialVersionUID = 7423582918178200639L;
	private String description;
	private String date;
	
	public PublicHoliday(String description, String date) {
		this.description = description;
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
