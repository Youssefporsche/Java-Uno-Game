package exceptions;

import java.lang.Exception;

public abstract class GameActionException extends Exception {

	public GameActionException() {
	}

	public GameActionException(String message) {
		super(message);
	}

}
