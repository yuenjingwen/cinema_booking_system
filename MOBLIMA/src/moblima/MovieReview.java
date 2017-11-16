package moblima;

import java.io.Serializable;

public class MovieReview implements Serializable {
	
	private static final long serialVersionUID = 48572957668402758L;
	/**
	 * This is the review paragraph inserted by the customer
	 */
	private String review;
	/**
	 * This is the rating for the movie
	 */
	private int rating;
	
	/**
	 * This the movie review constructor
	 */
	//constructor
	public MovieReview(String review, int rating)
	{
		this.review = review;
		this.rating = rating;
	}
	
	/**
	 * This function retrieves the review paragraph
	 */
	public String getReview()
	{
		return review;
	}
	/**
	 * This function retrieves the rating
	 */
	public int getRating()
	{
		return rating;
	}
	/**
	 * This function adds the review and rating with inputs
	 * @param review is the review paragraph
	 * @param rating is the score input by the customer
	 */
	public void setReview(String review, int rating)
	{
		this.review = review;
		this.rating = rating;
	}
}