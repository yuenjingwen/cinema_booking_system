package moblima;

import java.io.Serializable;

public class TicketDiscount implements Serializable {
	
	private static final long serialVersionUID = 96857686746573625L;
	
	/**
	 * The name of this discount. 
	 */
	private String discountName;
	/**
	 * The value of this discount. 
	 */
	private int discountValue;
	
	
	/**
	 * This is a default constructor. 
	 * Creates a new ticket discount with no discount name and zero value. 
	 */
	public TicketDiscount() { 
		discountName = null;
		discountValue = 0;
	}
	
	/**
	 * This is a constructor. 
	 * Creates a new ticket with a discount name and discount value. 
	 * @param type The name of this discount. 
	 * @param value The value of this discount. 
	 */
	public TicketDiscount(String type, int value)
	{
		discountName = type;
		discountValue = value;
	}
	
	/**
	 * Get the name of this discount. 
	 * @return discountName The name of this discount. 
	 */
	public String getDiscountName() {
		return discountName;
	}
	
	/**
	 * Change the name of this discount. 
	 * @param type The updated name of this discount.
	 */
	public void setDiscountName(String type) {
		discountName = type;
	}
	
	/**
	 * Get the value for this discount. 
	 * @return discountValue The value for this discount.
	 */
	public int getDiscountValue() {
		return discountValue;
	}
	
	/**
	 * Change the value of this discount. 
	 * @param value The updated value of this discount. 
	 */
	public void setDiscountValue(int value) {
		discountValue = value;
	}
	
	

}
