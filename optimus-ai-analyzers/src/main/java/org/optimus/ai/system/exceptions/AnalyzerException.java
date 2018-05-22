package org.optimus.ai.system.exceptions;

public class AnalyzerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8111366327221429728L;

	private String exceptionMessage;

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	public AnalyzerException(String exceptionMessage, Exception message) {
        super(message);
        this.exceptionMessage = exceptionMessage;
    }
	
	public String getExceptionMessage() {
		return exceptionMessage;
	}

}
