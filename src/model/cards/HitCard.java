package model.cards;

public class HitCard extends ActionCard {
	
	private int hitNumber;

	public HitCard(Color color) {
		super(color);
		this.hitNumber = (int)(Math.random() * 5) + 1;	
	}

	public int getHitNumber() {
		return hitNumber;
	}

}
