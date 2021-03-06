package moblima;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class TicketDatabase implements Database{

	private static ArrayList<Ticket> ticketList;
	private static File ticketFile = new File("Ticket.dat"); 
	
	private static ArrayList<TicketDiscount> discountList;
	private static File discountFile = new File("TicketDiscount.dat"); 

	/**
	 * This adds a ticket into the ticket list and updates the file afterwards.
	 * @param temp is just a placeholder to temporarily store a ticket object then places it into the arrayList.
	 */
	public static void add(Ticket temp) { 
		ticketList.add(temp);	
		updateTickets();
	} 
	
	/**
	 * Gets the arrayList
	 * @return ticketList to anyone whos calls the function.
	 */
	public static ArrayList<Ticket> getTicketArrayList() {
		return ticketList;
	}
	
	/**
	 * Update the latest ticket arrayList to the Ticket.dat file
	 */
	public static void updateTickets(){
		try{
			FileOutputStream fo = new FileOutputStream(ticketFile);
			ObjectOutputStream output = new ObjectOutputStream(fo);
			for(Ticket t: ticketList){
				output.writeObject(t);
			}
			fo.close();
			output.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}	
	
	/**
	 * Adds a discount to the list of discounts available when purchasing a ticket.
	 * @param scanner Scanner object
	 */
	public static void addDiscount(Scanner scanner) { 
		
		String type = null;
		int value = 0;
	
		try{
			System.out.print("Enter the type of discount: ");
			type = scanner.nextLine();
			//scanner.nextLine();
			System.out.print("Enter the value of the discount: ");
			value = scanner.nextInt();
			scanner.nextLine();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		discountList.add(new TicketDiscount(type, value));
		updateTicketDiscount();
	} 
	
	/**
	 * Removes a discount from the list of discounts available when purchasing a ticket.
	 * @param scanner Scanner object
	 */
	public static void removeDiscount(Scanner scanner) { 
		
		int index;
		System.out.print("Enter ID to remove: ");
		try{
			index = scanner.nextInt();
			scanner.nextLine();
			discountList.remove(index - 1);
		} catch (Exception e){
			e.printStackTrace();
		}
		updateTicketDiscount();
	} 
	
	/**
	 * Edit the value of individual discount within the list of discounts
	 * @param scanner Scanner Object
	 */
	public static void editDiscount(Scanner scanner) {
		
		int index, newValue;
		System.out.println("Enter ID to edit: ");
		try{
			index = scanner.nextInt();
			scanner.nextLine();
			
			System.out.println("Enter new discount value: ");
			newValue = scanner.nextInt();
			scanner.nextLine();
			
			discountList.get(index - 1).setDiscountValue(newValue);
		} catch (Exception e){
			e.printStackTrace();
		}
		updateTicketDiscount();
	}
	
	/**
	 * Gets a discountList
	 * @return discountList
	 */
	public static ArrayList<TicketDiscount> getDiscountArrayList() {
		return discountList;
	}
	
	/**
	 * Search for a discount by the name
	 * @param discountName takes in discount name
	 * @return an Integer which reflects the value of the discount. 
	 * @throws Exception
	 */
	public static int searchDiscountByName (String discountName) throws Exception{
		for (TicketDiscount td: getDiscountArrayList()) {
			if (discountName.equals(td.getDiscountName())) {
				return td.getDiscountValue();
			}
		}
		throw new Exception("No discount found.");
	}
	
	/**
	 * Updates the latest TicketDIscount arrayList to file
	 */
	public static void updateTicketDiscount(){
		try{
			FileOutputStream fo = new FileOutputStream(discountFile);
			ObjectOutputStream output = new ObjectOutputStream(fo);
			for(TicketDiscount d: discountList){
				output.writeObject(d);
			}
			fo.close();
			output.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}	
	
	/**
	 * Prints out the ticket discount menu. Following that, prints the name of the discount and the value
	 * @param scanner Scanner Object
	 */
	public static void printTicketDiscountMenu(Scanner scanner) {
		System.out.println("\n========================\n"
				+ "Ticket Discount Settings\n"
				+ "========================\n"
				+ "Current List of Ticket Discounts:");
		
		System.out.println("==============================================");
		System.out.println("ID\tDISCOUNT TYPE\t\tDISCOUNT VALUE");
		System.out.println("==============================================");
		
		int i = 1;
		for(TicketDiscount d : discountList)
		{
			System.out.print(i + ".	");
			System.out.printf("%-16s", d.getDiscountName());
			System.out.print("	" + d.getDiscountValue()+ "\n");
			i++;
		}
		
		System.out.println("==============================================");
		
		System.out.print(""
				+ "1. Add Ticket Discount\n"
				+ "2. Edit Ticket Discount Value\n"
				+ "3. Remove Ticket Discount\n"
				+ "4. Back\n"
				+ "Please enter choice: ");
		
		try{
			MainMenuManager.choice = scanner.nextInt();
			scanner.nextLine();
			switch (MainMenuManager.choice) {
			case 1:
				addDiscount(scanner);
				break;
			case 2:
				editDiscount(scanner);
				break;
			case 3:
				removeDiscount(scanner);
				break;
			case 4:
				break;
			default:
				System.out.println("\n===========");
				System.out.println("Error Input!");
				System.out.println("===========\n");
				break;
			}
		}catch(Exception e){
			System.out.println("Invalid input.");
			scanner.nextLine();
		}
		
	}
	
	
	/**
	 * Loads all the ticket and discount from external files onto the the 2 arrayLists.
	 */
	@Override
	public void fetchDatabase() {

		ticketList = new ArrayList<Ticket>();

		try{
			FileInputStream fi = new FileInputStream(ticketFile);
			ObjectInputStream input = new ObjectInputStream(fi);

			try{
				while(true){
					Ticket t = (Ticket)input.readObject();
					ticketList.add(t);
				}
			} catch (EOFException e){
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			fi.close();
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		discountList = new ArrayList<TicketDiscount>();

		try{
			FileInputStream fi = new FileInputStream(discountFile);
			ObjectInputStream input = new ObjectInputStream(fi);

			try{
				while(true){
					TicketDiscount d = (TicketDiscount)input.readObject();
					discountList.add(d);
				}
			} catch (EOFException e){
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			fi.close();
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}




	}

	@Override
	public void updateDatabase() {
		// TODO Auto-generated method stub
		
	}
}