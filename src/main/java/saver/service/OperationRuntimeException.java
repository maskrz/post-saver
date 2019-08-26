package saver.service;

public class OperationRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1776959329879623371L;

	public OperationRuntimeException(String errorMessage) {
		super(errorMessage);
	}

	public OperationRuntimeException(String errorMessage, Throwable ex) {
		super(errorMessage, ex);
	}
}
