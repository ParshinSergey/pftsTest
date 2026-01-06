package ua.univer.pftsTest.exeptions;

public class PftsException extends RuntimeException{

    public PftsException(String message) {
        super(message);
    }

    public PftsException(String message, Throwable cause) {
        super(message, cause);
    }
}
