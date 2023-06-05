package model.cards;

public class NumberCard extends ColorCard {
	
	private int number;

	public NumberCard(int number, Color color) {
		super(color);
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

}
