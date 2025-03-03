package fr.elite.authapi.exceptions;

public class BannedTokenException extends RuntimeException {

    public BannedTokenException() {
        super();
    }

    public BannedTokenException(String message) {
        super(message);
    }

    public BannedTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public BannedTokenException(Throwable cause) {
        super(cause);
    }
    
}
