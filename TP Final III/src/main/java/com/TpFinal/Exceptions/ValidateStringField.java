package com.TpFinal.Exceptions;

public class ValidateStringField extends RuntimeException{
    public ValidateStringField() {
    }

    public ValidateStringField(String message) {
        super(message);
    }
}
