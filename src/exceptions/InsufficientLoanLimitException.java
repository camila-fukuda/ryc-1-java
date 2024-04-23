package exceptions;

public class InsufficientLoanLimitException extends RuntimeException {
    public InsufficientLoanLimitException(String msg) {
        super(msg);
    }

    public InsufficientLoanLimitException() {
        super();
    }
}
