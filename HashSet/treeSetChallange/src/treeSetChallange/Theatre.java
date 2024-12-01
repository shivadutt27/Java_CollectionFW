package treeSetChallange;

import java.util.NavigableSet;
import java.util.TreeSet;

/*
 * what describes a theatre
 * 	---> theatre Name, how many seats are there, how many rows are there, 
 * */
public class Theatre{

	class Seat implements Comparable<Seat> {
		private String seatNum;
		private boolean isReserved;
		
		public Seat(int numberOfRows, int seatsPerRow) {
			while(numberOfRows > 0) {
				for(int i=0; i<seatsPerRow; i++) {
					char rowChar = (char) (i/seatsPerRow+(int) 'A');
					this.seatNum = "%c%03d".formatted(rowChar,i);
				}
				numberOfRows--;
			}
		}
		
		@Override
		public String toString() {
			return seatNum;
		}

		@Override
		public int compareTo(Seat o) {
			return this.seatNum.compareTo(o.seatNum);
		}
	}
	
	
	private String theatreName;
	private NavigableSet<Seat> seats;
	private int totalRows;
	private int seatsPerRow;
	
	public Theatre(String theatreName, int totalSeats, int totalRows) {
		this.theatreName = theatreName;
		this.totalRows = totalRows;
		this.seatsPerRow = totalSeats/totalRows;
		this.seats = new TreeSet<>();
		for(int i=1; i<totalSeats; i++) {
			
			seats.add(new Seat(totalRows,seatsPerRow));
		}
		seats.forEach(System.out::println);
	}
	
	public void printSeatMap() {
		String dottedLine = "_".repeat(90);
		System.out.printf("%1$s%n%2$s Seat Map %n%1$s%n",dottedLine,theatreName);
		seats.forEach(System.out::println);
		
		int index = 0;
		for(Seat s: seats) {
			System.out.printf("%-8s",
					s.isReserved?s+"reserved":s,
					(index++)%seatsPerRow==0?"/n":""		
					);
		}
	}
	
	
}

