package com.genesis.amazonprofile.exceptions;

public class InvalidIdException extends MarketException{

    public InvalidIdException(String message){
        super(message);
    }

    public InvalidIdException(String message, Throwable e){
        super(message, e);
    }

    public InvalidIdException(Throwable e){
        super(e);
    }

    public InvalidIdException(){
        super();
    }
}
