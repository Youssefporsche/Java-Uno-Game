package engine;

import java.util.ArrayList;

import model.cards.Card;

public class Player {
	
	private String name;
	private ArrayList<Card> hand;

	public Player(String name) {
		this.name = name;
		this.hand = new ArrayList<Card>();
	}

	public String getName() {
		return name;
	}

	public ArrayList<Card> getHand() {
		return hand;
	}
	

}
