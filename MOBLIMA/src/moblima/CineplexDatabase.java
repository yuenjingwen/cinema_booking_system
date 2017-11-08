package moblima;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CineplexDatabase{
	
	private static ArrayList<Cineplex> cineplexList;
	

	private static File cineplexFile = new File("Cineplex.dat");
	
	public void add() {

	}
	
	public void remove() {
		// TODO Auto-generated method stub

	}

	public void edit() {
		// TODO Auto-generated method stub

	}

	
	public static ArrayList<Cineplex> getArrayList() {
		return cineplexList;
	}
	
	// Fetching infomation of al cineplexes.
	public static void fetchCineplexes(File file){
		cineplexList = new ArrayList<Cineplex>();
		try{
			FileInputStream fi = new FileInputStream(file);
			ObjectInputStream input = new ObjectInputStream(fi);
				
			try{
				while(true){
					Cineplex c = (Cineplex)input.readObject();
					cineplexList.add(c);
				}
			} catch (EOFException e){
			} catch (ClassNotFoundException e) {
					e.printStackTrace();
			}
			
			fi.close();
			input.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
	}
		
	public static void updateCineplexes(File file){
		try{
			FileOutputStream fo = new FileOutputStream(file);
			ObjectOutputStream output = new ObjectOutputStream(fo);
			for(Cineplex c: cineplexList){
				output.writeObject(c);
			}
			fo.close();
			output.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}	
	

	public static void addShowTime(Scanner scanner){
		int i = 1;
		int movieIndex;
		int cineplexIndex;
		int cinemaIndex;
		System.out.println("===============================");
		for(Cineplex cp : cineplexList){
			System.out.println(i + ".\t" + cp.getName());
			System.out.println("===============================");
			i++;
		}
		System.out.print("Select cineplex: ");
		cineplexIndex = scanner.nextInt();
		scanner.nextLine();
		
		i = 1;
		System.out.println("\n===============================");
		for(Cinema c : cineplexList.get(cineplexIndex-1).getCinemaList()){
			System.out.println(i + ".\t" + c.getCinemaID());
			System.out.println("===============================");
			i++;
		}
		System.out.print("Select cinema: ");
		cinemaIndex = scanner.nextInt();
		scanner.nextLine();
		
		i = 1;
		System.out.println("\n===============================");
		for(Movie m : MovieDatabase.getArraylist()){
			System.out.println(i + ".\t" + m.getTitle());
			System.out.println("===============================");
			i++;
		}
		System.out.print("Select movie: ");
		movieIndex = scanner.nextInt();
		scanner.nextLine();
		
		int month, dayOfMonth, hour, minute;
		
		System.out.print("Enter month: ");
		month = scanner.nextInt();
		scanner.nextLine();
		
		System.out.print("Enter day of month: ");
		dayOfMonth = scanner.nextInt();
		scanner.nextLine(
				);
		
		System.out.print("Enter hour: ");
		hour = scanner.nextInt();
		scanner.nextLine();
		
		System.out.print("Enter minute: ");
		minute = scanner.nextInt();
		scanner.nextLine();
		
		LocalDateTime tempDateTime = LocalDateTime.of(2017, month, dayOfMonth, hour, minute);
		
		cineplexList.get(cineplexIndex - 1).getCinemaList().get(cinemaIndex-1)
		.getCinemaShowList().add(new CinemaShow(MovieDatabase.getArraylist().get(movieIndex-1), tempDateTime));
		
		Collections.sort(cineplexList.get(cineplexIndex-1).getCinemaList().get(cinemaIndex-1).getCinemaShowList());
		
		updateCineplexes(cineplexFile);
	}
	
		
}
