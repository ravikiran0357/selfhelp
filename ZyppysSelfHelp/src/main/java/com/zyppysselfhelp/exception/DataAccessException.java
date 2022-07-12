package com.zyppysselfhelp.exception;


public class DataAccessException extends ApplicationException {

    private static final long serialVersionUID = 1L;
    private int errorCode;
    private static final int errorCategoryCode = 102;

    public DataAccessException(String message){
    	super(message);
    }
    public DataAccessException(String message, Throwable cause){
    	super(message, cause);
    }
    public DataAccessException(int errorCode, String message) {
        super(errorCode,message,errorCategoryCode);
    	this.errorCode = errorCode;
    }

    public DataAccessException(int errorCode, String message, Throwable cause) {
        super(errorCode,message, cause,errorCategoryCode);
    	this.errorCode = errorCode;
    }

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}
