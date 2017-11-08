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
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CineplexDatabase{
	
	public static ArrayList<Cineplex> cineplexList;
	

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
	public static void fetchCineplexes(){
		cineplexList = new ArrayList<Cineplex>();
		try{
			FileInputStream fi = new FileInputStream(cineplexFile);
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
	
	public static void printAllShowtimes() {
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
		
		int showtimeIndex =1;
		
		for(Cineplex cp : getArrayList()){
			System.out.println("		"+cp.getName());											//Cineplex Name
			System.out.println(															"-------------------------------------------------------");
			for(Cinema c : cp.getCinemaList()){									
				System.out.println(														"\n-------------------------------------------------------");
				System.out.println(c.getCinemaID() + "\t" + c.getClassOfCinema());		//Cinema Name				Cinema Type
				System.out.println(														"-------------------------------------------------------");	
				for(CinemaShow cs : c.getCinemaShowList()){
					
					System.out.println("Movie:		" + cs.getMovie().getTitle());
					System.out.println(													"Date:		" + cs.getShowtime().format(formatter));
					System.out.println(													"Time:		" + cs.getShowtime().toLocalTime());
					System.out.println(													"- - - - - - - - - - - - - - - - - - - - - - - - - - - -");
				 showtimeIndex++;
				}
				
			}
			System.out.println(															"=======================================================");
		}
	}
		


	public static void updateShowTime(Scanner scanner){
		int showtimeIndex, movieIndex, cineplexIndex, cinemaIndex, i;
		int month, day, hour, minute;

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
		System.out.println();
		System.out.println("===============================");
		for (CinemaShow cs : cineplexList.get(cineplexIndex-1).getCinemaList().get(cinemaIndex=1).getCinemaShowList()) {
			System.out.println(i + ".\t" + cs.);
			System.out.println("===============================");
			i++;
		}
		MovieDatabase.printMovieList();
		
		System.out.print("Select movie to update to: ");
		movieID = scanner.nextInt();
		scanner.nextLine();
		
		

	
		
		System.out.print("Enter month: ");
		month = scanner.nextInt();
		scanner.nextLine();
		
		System.out.print("Enter day of month: ");
		day = scanner.nextInt();
		scanner.nextLine(
				);
		
		System.out.print("Enter hour: ");
		hour = scanner.nextInt();
		scanner.nextLine();
		
		System.out.print("Enter minute: ");
		minute = scanner.nextInt();
		scanner.nextLine();
		
		LocalDateTime tempDateTime = LocalDateTime.of(2017, month, day, hour, minute);
		
		cineplexList.get(cineplexIndex - 1).getCinemaList().get(cinemaIndex-1)
		.getCinemaShowList().add(new CinemaShow(MovieDatabase.getArraylist().get(movieIndex-1), tempDateTime));
		
		Collections.sort(cineplexList.get(cineplexIndex-1).getCinemaList().get(cinemaIndex-1).getCinemaShowList());
		
		updateCineplexes(cineplexFile);
		}
	
	public void printShowtimeOfCinema(ArrayList<CinemaShow> cinemaShowList) {
		for(CinemaShow cs : cinemaShowList){
			
			System.out.println("Movie:		" + cs.getMovie().getTitle());
			System.out.println(													"Date:		" + cs.getShowtime().format(formatter));
			System.out.println(													"Time:		" + cs.getShowtime().toLocalTime());
			System.out.println(													"- - - - - - - - - - - - - - - - - - - - - - - - - - - -");
		}
	}

}
