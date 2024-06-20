package com.TpFinal.Exceptions;

public class DontExistException extends RuntimeException{
    public DontExistException(String message) {
        super(message);
    }
}
