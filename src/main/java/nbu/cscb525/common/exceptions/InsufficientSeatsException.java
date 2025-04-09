package nbu.cscb525.common.exceptions;

public class InsufficientSeatsException extends RuntimeException {

    public InsufficientSeatsException() {
    }

    public InsufficientSeatsException(Throwable cause) {
        super(cause);
    }

    public InsufficientSeatsException(String message) {
        super(message);
    }

    public InsufficientSeatsException(String message, Throwable cause) {
        super(message, cause);
    }
}
