package saver.service;

public class InvalidActionTypeException extends IllegalArgumentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3094172821611294218L;

	public InvalidActionTypeException(String errorMessage) {
		super(errorMessage);
	}
}
