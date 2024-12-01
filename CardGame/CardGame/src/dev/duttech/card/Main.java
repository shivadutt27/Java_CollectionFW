package dev.duttech.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import dev.duttech.card.Card.Suit;

public class Main {
	
	public static void main(String[] args) {
		
		Card.printDeck(Card.createStandardDeck(), "Standard Deck", 4);
		
		//Card.sortRankReversedSuit();
	}
	
}
//
//record Player(String playerStatus, List<Card> playerCards, int discardEvent) {
//	
//	
//	public Player(String playerStatus) {
//		this(playerStatus, new ArrayList<>(),0);
//	}
//	
//	public void incrementDiscardEvent(int i) {
//		discardEvent = discardEvent+1; 
//	}
//	
//	public void setPlayerCards(Card card) {
//		this.playerCards.add(card);
//	}
//	
//	public List<Card> shuffleCards(List<Card> deck) {
//		if(playerStatus.equals("Dealer")) {
//			Collections.shuffle(deck);
//			return deck;
//		}else {
//			System.out.println("Only dealer can shuffle the cards");
//			return null;
//		}
//	}
//	
//	public List<Card> cutDeck(List<Card> shuffledDeck) {
//		List<Card> newDeck = new ArrayList<>(13);
//		
//		for(int i=0; i<26; i++) {
//			newDeck.add(shuffledDeck.get(i));
//			shuffledDeck.remove(i);
//		}
//		return newDeck;
//	}
//	
//	public void playerCards(List<Player> players, Card card) {
//		players.forEach(player -> player.playerCards.add(card));
//	}
//	
//	public void distributeCards(Player player,List<Card> newDeck) {
//		for(int i=0; i<5; i++) {
//			player.playerCards.add(newDeck.get(i));
//			newDeck.remove(i);
//		}
//	}
//	
//	/**
//	 * @param deck
//	 * @param discardedCard
//	 */
//	public void discard(List<Card> deck, Card discardedCard) {
//		if(discardEvent<=3) {
//			System.out.println(discardEvent);
//			 Card cardToBeDiscarded = this.playerCards.get(this.playerCards.indexOf(discardedCard));
//			 this.playerCards.remove(cardToBeDiscarded);
//			 int randomNumber = new Random().nextInt(1, 6);
//			 this.playerCards.add(deck.get(randomNumber));
//			 deck.add(cardToBeDiscarded);
//			 discardEvent++;
//		}
//		else {
//			System.out.println("Discarded limit is over!");
//		}
//		
//	}
//	
//	@Override
//	public String toString() {
//		return this.playerStatus+" have "+this.playerCards;
//	}
//}

