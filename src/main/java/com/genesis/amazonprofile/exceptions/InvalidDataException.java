package com.genesis.amazonprofile.exceptions;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String message){
        super(message);
    }

    public InvalidDataException(String message, Throwable e){
        super(message, e);
    }

    public InvalidDataException(Throwable e){
        super(e);
    }

    public InvalidDataException(){
        super();
    }
}
