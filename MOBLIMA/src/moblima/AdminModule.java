package moblima;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class AdminModule {
	
	/**
	 * The username entered by the admin.
	 */
	private static String inputUser;
	/**
	 * The password entered by the admin.
	 */
	private static String inputPass;
	/**
	 * The user's status. 
	 * This is either Customer or Admin.
	 */
	private final static String USER = "Admin";
	/**
	 * The actual password of the admin. 
	 */
	private static String password;
	
	/**
	 * Gets the actual password of the admin.
	 * @return password The actual password of the admin.
	 */
	public static String getPassword() {
		File file = new File("AdminPassword.txt");
		try {
			Scanner scanPass = new Scanner(file);
			password = scanPass.nextLine();
			scanPass.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return password;
	}

	/**
	 * Change the password of the admin.
	 * @param password The new updated admin password.
	 */
	public static void setPassword(String password) {
		File file = new File("AdminPassword.txt");
		try{
			PrintWriter output = new PrintWriter(file);
			output.println(password);
			output.close();
		} catch (IOException e){
			e.printStackTrace();
		}
		
		AdminModule.password = password;
		
		System.out.println("\n=============================");
		System.out.println("Password successfully changed");
		System.out.println("=============================\n");
	}

	/**
	 * To show the status of the person using this application.
	 * This is either Customer or Admin. 
	 * @return USER The status of this person.
	 */
	public static String getUser() {
		return USER;
	}

	/**
	 * Login page for the admin.
	 * Checks the entered password with the actual password.
	 * Access is allowed only if entered password matches exactly with the actual password.
	 * @param scanner Scanner object
	 */
	public static void Adminlogin(Scanner scanner) {
		System.out.print("Enter Username: ");										// Admin to login
		inputUser = scanner.nextLine();
		
		System.out.print("Enter Password: ");
		inputPass = scanner.nextLine();
																					// Login check
		if(!inputUser.equals(AdminModule.getUser()) || !inputPass.equals(AdminModule.getPassword())){
			System.out.println("\n============================\n"
					+ "Invalid Username or Password\n"
					+ "============================\n");							// If invalid, won't execute printAdminMenu
		} else {
			System.out.println("\n================");
			System.out.println("Successful Login");										// Successful login
			System.out.println("================\n");
			
			
			MainMenuManager.choice = 0;
			while(MainMenuManager.choice != 4){
				printAdminMenu(scanner);	// Opens Admin menu
			}
		}
	}
	
	/**
	 * Prints out a menu of what an admin can do.
	 * Allows admin to select what he/she would like to do in this application.
	 * @param scanner Scanner object
	 */
	private static void printAdminMenu(Scanner scanner){
		System.out.print("1. Create/Update/Remove movie listing\n"
				+ "2. Create/Update/Remove cinema showtimes and the movies to be shown\n"
				+ "3. Configure system settings\n"
				+ "4. Back\n"
				+ "Please enter choice: ");
		try{
			MainMenuManager.choice = scanner.nextInt();
			scanner.nextLine();
			switch (MainMenuManager.choice) {
			case 1:
				MovieDatabase.printFullMovieList();
				while(MainMenuManager.choice != 6){					
					printAdminMovieMenu(scanner);
				}
				MainMenuManager.choice = 1;
				break;
			case 2:
				while(MainMenuManager.choice != 4){
					printAdminShowtimeMenu(scanner);
				}
				MainMenuManager.choice = 2;
				break;
			case 3:
				printAdminSystemConfig(scanner);
				MainMenuManager.choice = 3;
				break;
				
			case 4:
				System.out.println("\n~Logout Successful~\n");
				break;
				
			default:
				System.out.println("\n===========");
				System.out.println("Error Input!");
				System.out.println("===========\n");				
				break;
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Invalid input. Please re-enter choice.");
			scanner.nextLine();
		}
	}

	/**
	 * Prints out the list of things that an admin can do to modify or view and movie related things.
	 * @param scanner Scanner object
	 */
	private static void printAdminMovieMenu(Scanner scanner){
		
		System.out.print("\n=====================\n"
				+ "Movie Listing Editor\n"
				+ "=====================\n"
				+ "1. Create\n"
				+ "2. Update\n"
				+ "3. Remove\n"
				+ "4. List top 5 movie ranking by ticket sales\n"
				+ "5. List top 5 movie ranking by ratings\n"
				+ "6. Back\n"
				+ "Please enter choice: ");
		try{
			MainMenuManager.choice = scanner.nextInt();
			scanner.nextLine();
			switch (MainMenuManager.choice) {
			case 1:
				MovieDatabase.addMovie(scanner);
				break;
			case 2:
				MovieDatabase.editMovie(scanner);
				break;
			case 3:
				MovieDatabase.removeMovie(scanner);
				break;
			case 4:
				MovieDatabase.printTop5Sales(scanner);
				break;
			case 5:
				MovieDatabase.printTop5Ratings(scanner);
				break;
			case 6:
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
	 * Prints out the show time menu.
	 * Allows admin to edit movie show time.
	 * @param scanner Scanner object
	 */
	private static void printAdminShowtimeMenu(Scanner scanner){
		System.out.println("=======================================================");
		System.out.println("                    Cinema & Showtime                  ");
		System.out.println("=======================================================");
		
		CineplexDatabase.printAllShowtimes();
		
		System.out.print("\n=====================\n"
				+ "Movie Showtime Editor\n"
				+ "=====================\n"
				+ "1. Create\n"
				+ "2. Update\n"
				+ "3. Remove\n"
				+ "4. Back\n"
				+ "Please enter choice: ");
		try{
			MainMenuManager.choice = scanner.nextInt();
			scanner.nextLine();
			switch (MainMenuManager.choice) {
			case 1:
				CineplexDatabase.addShowTime(scanner);
				break;
			case 2:
				CineplexDatabase.editShowTime(scanner);
				break;
			case 3:
				CineplexDatabase.removeShowTime(scanner);
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
	 * Prints out the menu for admin system configuration. 
	 * Allows admin to edit or change password, public holidays, ticket discounts, customers' booking history.
	 * @param scanner Scanner object
	 */
	private static void printAdminSystemConfig(Scanner scanner){
		System.out.print("\n=====================\n"
				+ "SYSTEM CONFIG\n"
				+ "=====================\n"
				+ "1. Change password\n"
				+ "2. Manage Public Holidays\n"
				+ "3. Manage ticket discounts\n"
				+ "4. See full customer booking history\n"
				+ "5. Back\n"
				+ "Please enter choice: ");
		try{
			MainMenuManager.choice = scanner.nextInt();
			scanner.nextLine();
			switch (MainMenuManager.choice) {
			case 1:
				System.out.print("Enter current password: ");
				if(scanner.nextLine().equals(AdminModule.getPassword())){
					System.out.print("Enter new password: ");
					AdminModule.setPassword(scanner.nextLine());
				}else{
					System.out.println("\n================");
					System.out.println("Invalid password");
					System.out.println("================\n");
				}
				break;
			case 2:
				while(MainMenuManager.choice!=3){
					PublicHolidayDatabase.printPublicHolidayMenu(scanner);
				}
				MainMenuManager.choice = 2;
				break;
			case 3:
				while(MainMenuManager.choice!=4){
					TicketDatabase.printTicketDiscountMenu(scanner);
				}
				MainMenuManager.choice = 3;
				break;
			case 4:
				for (Ticket t: TicketDatabase.getTicketArrayList()) {
					TicketBooth ticketBooth = new TicketBooth();
					ticketBooth.printTicket(t);
				}
				
				if (TicketDatabase.getTicketArrayList().size() == 0) {
					System.out.println("Empty booking history.");
				}
				
				break;
			case 5:
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
	
	
}
