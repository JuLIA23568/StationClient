package by.bsuir.station.exceptions;

public class DataRetrieveException extends Exception {

    public DataRetrieveException() {
    }

    public DataRetrieveException(String message) {
        super(message);
    }

    public DataRetrieveException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataRetrieveException(Throwable cause) {
        super(cause);
    }

    public DataRetrieveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
