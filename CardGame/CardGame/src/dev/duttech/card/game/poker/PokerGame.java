package dev.duttech.card.game.poker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

import dev.duttech.card.Card;

public class PokerGame {
	
	private static final List<Card> deck = Card.createStandardDeck();
	private int cardsInHand;
	private int playerCount;
	private List<PokerHand> pokerHands;
	private List<Card> remainingCards;
	
	public PokerGame(int playerCount, int cardsInHand) {
		this.playerCount = playerCount;
		this.cardsInHand = cardsInHand;
		this.pokerHands = new ArrayList<>(cardsInHand);
	}
	
	
	public void playGame() {
		Collections.shuffle(deck);
		System.out.println(deck);
		
		System.out.println("=====================================");
		
		int randomMid = new Random().nextInt(15,35);
		Collections.rotate(deck, randomMid);
		System.out.println(deck);
		
		deal();
		
//		Consumer<PokerHand> checkHand = PokerHand::evalHand;
//        pokerHands.forEach(checkHand.andThen(System.out::println));
		
		System.out.println("=====================");
		
		pokerHands.forEach(pokerHand -> pokerHand.evalHand());
		
		pokerHands.forEach(pokerHand -> System.out.println(pokerHand));
		
		
	}
	
	private void deal() {
		Card[][] hands = new Card[playerCount][cardsInHand];
		for(int deckSize = 0,i=0; i<cardsInHand;i++) {
			for(int j=0; j<playerCount; j++) {
				hands[j][i] = deck.get(deckSize++);
			}
		}
		
		int playerNo = 1;
		for(Card[] hand: hands) {
			pokerHands.add(new PokerHand(playerNo++,Arrays.asList(hand)));
		}
		
		pokerHands.forEach(ph -> System.out.println(ph));
	
	}
	
}