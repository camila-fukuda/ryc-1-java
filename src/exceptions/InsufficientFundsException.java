package exceptions;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String msg) {
        super(msg);
    }

    public InsufficientFundsException() {
        super();
    }
}
