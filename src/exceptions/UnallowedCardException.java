package exceptions;

public class UnallowedCardException extends GameActionException {

	public UnallowedCardException() {
	}

	public UnallowedCardException(String message) {
		super(message);
	}

}
