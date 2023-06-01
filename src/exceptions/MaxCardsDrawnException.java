package exceptions;

public class MaxCardsDrawnException extends GameActionException {

	public MaxCardsDrawnException() {
	}

	public MaxCardsDrawnException(String message) {
		super(message);
	}

}
