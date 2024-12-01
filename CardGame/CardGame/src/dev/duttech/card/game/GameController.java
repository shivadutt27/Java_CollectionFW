package dev.duttech.card.game;

import dev.duttech.card.game.poker.PokerGame;

public class GameController {
	
	public static void main(String[] args) {
		PokerGame pg = new PokerGame(5,5);
		pg.playGame();
	}
}
