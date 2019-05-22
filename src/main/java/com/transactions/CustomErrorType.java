package com.transactions;

public class CustomErrorType {
	
	private String code;
    private String message;

    public CustomErrorType(String code, String message){
    	this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }

}