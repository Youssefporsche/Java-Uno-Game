package model.cards;

public abstract class WildCard extends Card implements Changeable{
	
	public WildCard() {
		super(Color.WILD);
	}

	public void changeColor(Color color) {
		this.setColor(color);
	}

}
