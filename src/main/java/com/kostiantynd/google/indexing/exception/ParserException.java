package com.kostiantynd.google.indexing.exception;

public class ParserException extends Exception {
    private static final long serialVersionUID = 1L;

    private String errorMessage;

    public ParserException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}