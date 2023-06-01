package model.cards;

public class HitAndChangeCard extends WildCard {
	
	private int hitNumber;

	public HitAndChangeCard() {
		super();
		this.hitNumber = (int) (Math.random() * 5) + 4;
	}

	public int getHitNumber() {
		return hitNumber;
	}

}
