package com.zyppysselfhelp.exception;

public class ApplicationException extends Exception {

	private static final long serialVersionUID = 1L;
    private int errorCode;
    private int errorCategoryCode = 100;

    public ApplicationException(String message){
    	super(message);
    }
    public ApplicationException(String message, Throwable cause){
    	super(message, cause);
    }
    public ApplicationException(String message, int errorCategoryCode){
    	super(message);
    	this.errorCategoryCode = errorCategoryCode;
    }
    public ApplicationException(int errorCode, int errorCategoryCode){
    	this.errorCode = errorCode;
    	this.errorCategoryCode = errorCategoryCode;
    }
    public ApplicationException(int errorCode, String message) {
        super(message);
    	this.errorCode = errorCode;
    }
    public ApplicationException(int errorCode, String message, int errorCategoryCode) {
        super(message);
    	this.errorCode = errorCode;
    	this.errorCategoryCode = errorCategoryCode;
    }

    public ApplicationException(int errorCode, String message, Throwable cause, int errorCategoryCode) {
        super(message, cause);
    	this.errorCode = errorCode;
    	this.errorCategoryCode = errorCategoryCode;
    }

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public int getErrorCategoryCode() {
		return errorCategoryCode;
	}
	public void setErrorCategoryCode(int errorCategoryCode) {
		this.errorCategoryCode = errorCategoryCode;
	}

}
