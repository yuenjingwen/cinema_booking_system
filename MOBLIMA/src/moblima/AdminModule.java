package moblima;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class AdminModule {
	
	private final static String USER = "Admin";
	private static String password;
	
	
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

	public static String getUser() {
		return USER;
	}



}
