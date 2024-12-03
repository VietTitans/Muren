package controller;


public class GeneralException extends Exception {
	private static final long serialVersionUID = 1L;

	public GeneralException(String message, Throwable e) {
		super(message, e);
	}
}
