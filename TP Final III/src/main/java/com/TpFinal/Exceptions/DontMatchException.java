package com.TpFinal.Exceptions;

public class DontMatchException extends RuntimeException{
    public DontMatchException(String message) {
        super(message);
    }
}
