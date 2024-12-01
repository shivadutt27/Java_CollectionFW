package dev.duttech.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * This class will help to create the deck of cards.
 * Attributes or Member variables of the class is: 
 * 		Suits (Heart, Spade, Club, Diamond)
 * 		Face(Ace, King, Queen, Jack, number from 2 to 10)
 *  	Rank
 * */

 public record Card(Suit suit, String face, int rank) {
	
	 enum Suit{
		CLUB,DIAMOND,HEART,SPADE;
		
		public char getImage() {
			return (new char[] {9827,9830,9829,9824})[this.ordinal()];
		}
	}
	
	public static Card createNumericalCard(Suit suit, int value) {
		if(value > 1 && value < 11) {
			return new Card(suit, "" + value, value-2);
		}else {
			System.out.println("Values are not correct");
			return null;
		}
	}
	
	public static Card createFaceCard(Suit suit, char abbr) {
		
		int foundChar = "JQKA".indexOf(abbr);
		if(foundChar > -1) {
			return new Card(suit, ""+abbr, foundChar+9);
		}else {
			System.out.println("No face card is present");
			return null;
		}
	}

	public static List<Card> createStandardDeck(){
		List<Card> standardDeck = new ArrayList<>(52);
		for(Suit suit: Suit.values()) {
			for(int i=2; i<11; i++) {
				standardDeck.add(createNumericalCard(suit, i));
			}
			for(char ch: new char[] {'J','Q','K','A'}) {
				standardDeck.add(createFaceCard(suit, ch));
			}
		}
		//System.out.println(standardDeck);
		return standardDeck;
	}

	
	public static void printDeck(List<Card> standardDeck, String Description, int rows) {
		// lets say rows = 4 i.e 13 cards per row
		// loop 1st time 1-13
		// loop second time 14-26
		// loop third time 27-39
		// loop 4th time 40-52 
		
		int numberOfCardsPerRow = standardDeck.size()/rows; //Total number of cards/ total number of rows
		int startIdx = 0;
		for(int i=0; i<rows; i++) {
			int endIdx = (numberOfCardsPerRow*i)+numberOfCardsPerRow;
			
			
//			for(int j=startIdx; j<endIdx; j++) {
//				System.out.println(standardDeck.get(j));
//			}
			 standardDeck.subList(startIdx, endIdx).forEach(card -> System.out.print(card+" "));
			
			startIdx = endIdx;
			System.out.println();
		}
	}
	
	public static Comparator<Card> sortRankReversedSuit(){
		return Comparator.comparing(Card::rank).reversed().thenComparing(Card::suit);
	}

	@Override
    public String toString() {

        int index = face.equals("10") ? 2 : 1;
        String faceString = face.substring(0, index);
        return "%s%c(%d)".formatted(faceString, suit.getImage(), rank);
    }
	
}

