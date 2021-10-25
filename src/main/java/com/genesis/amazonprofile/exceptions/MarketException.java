package com.genesis.amazonprofile.exceptions;

public class MarketException extends RuntimeException {

    public MarketException(String message) {
        super(message);
    }

    public MarketException(String message, Throwable e) {
        super(message, e);
    }

    public MarketException() {
        super();
    }

    public MarketException(Throwable e) {
        super(e);
    }

}
