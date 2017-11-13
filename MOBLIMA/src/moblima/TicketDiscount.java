package moblima;

import java.io.Serializable;

public class TicketDiscount implements Serializable {
	
	private static final long serialVersionUID = 96857686746573625L;
	
	private String typeDiscount;
	private int valueDiscount;
	
	//default constructor
	public TicketDiscount() { 
		typeDiscount = null;
		valueDiscount = 0;
	}
	
	//constructor
	public TicketDiscount(String type, int value)
	{
		typeDiscount = type;
		valueDiscount = value;
	}
	
	public String getTypeDiscount() {
		return typeDiscount;
	}
	
	public void setTypeDiscount(String type) {
		typeDiscount = type;
	}
	
	public int getvalueDiscount() {
		return valueDiscount;
	}
	
	public void setvalueDiscount(int value) {
		valueDiscount = value;
	}
	
	

}
