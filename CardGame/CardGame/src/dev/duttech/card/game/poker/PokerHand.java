package dev.duttech.card.game.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import dev.duttech.card.Card;

public class PokerHand {
	
	private List<Card> hand;
	private List<Card> keepers;
	private List<Card> discards;
	private Ranking score = Ranking.NONE;
	private int playerNo;
	
	public PokerHand(int playerNo, List<Card>hand) {
		hand.sort(Card.sortRankReversedSuit());
		this.playerNo = playerNo;
		this.hand = hand;
		this.keepers = new ArrayList<>(hand.size());
		this.discards = new ArrayList<>(hand.size());
	}
	
	public void setRank(int faceCount) {
		switch(faceCount) {
			case 4 -> score = Ranking.FOUR_OF_A_KIND;
			case 3 -> {
				if(score == Ranking.NONE) score = Ranking.THREE_OF_A_KIND;
				else score = Ranking.FULL_HOUSE;
			}
			case 2 -> {
				if(score == Ranking.NONE) score = Ranking.ONE_PAIR;
				else if(score == Ranking.THREE_OF_A_KIND) score = Ranking.FULL_HOUSE;
				else score = Ranking.TWO_PAIR;
			}
		}
	}
	
//	 public void evalHand() {
//
//	        List<String> faceList = new ArrayList<>(hand.size());
//	        hand.forEach(card -> faceList.add(card.face()));
//
//	        List<String> duplicateFaceCards = new ArrayList<>();
//	        faceList.forEach(face -> {
//	            if (!duplicateFaceCards.contains(face) &&
//	                    Collections.frequency(faceList, face) > 1) {
//	                duplicateFaceCards.add(face);
//	            }
//	        });
//
//	        for (String duplicate : duplicateFaceCards) {
//	            int start = faceList.indexOf(duplicate);
//	            int last = faceList.lastIndexOf(duplicate);
//	            setRank(last - start + 1);
//	            //List<Card> sub = hand.subList(start, last + 1);
//	            //keepers.addAll(sub);
//	        }
//
//	        //pickDiscards();
//	    }
	
	public void evalHand() {
		List<String> faceList = new ArrayList<>(hand.size());
		hand.forEach(card -> faceList.add(card.face()));
		List<String> duplicateCards = new ArrayList<>();
		
		faceList.forEach(face -> {
			System.out.println(face);
			System.out.println(duplicateCards);
			if(!duplicateCards.contains(face) && Collections.frequency(faceList, face) >1) {
				duplicateCards.add(face);
			}
			System.out.println(duplicateCards);
		});
		
		for(String duplicateCard: duplicateCards) {
			int startIdx = faceList.indexOf(duplicateCard);
			int lastIdx = faceList.lastIndexOf(duplicateCard);
			int rank = (lastIdx-startIdx+1);
			setRank(rank);
		}
	}
	

	@Override
    public String toString() {
        return "%d. %-16s Rank:%d %-40s Best:%-7s Worst:%-6s %s".formatted(
                playerNo, score, score.ordinal(), hand,
                Collections.max(hand, Comparator.comparing(Card::rank)),
                Collections.min(hand, Comparator.comparing(Card::rank)),
                (discards.size() > 0) ? "Discards:" + discards : "");
    }
	
	
	

}
