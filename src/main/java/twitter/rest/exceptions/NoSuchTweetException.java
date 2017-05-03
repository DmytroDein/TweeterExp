package twitter.rest.exceptions;


public class NoSuchTweetException extends RuntimeException {

    public NoSuchTweetException() {
        super();
    }

    public NoSuchTweetException(String message) {
        super(message);
    }

    public NoSuchTweetException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchTweetException(Throwable cause) {
        super(cause);
    }

    protected NoSuchTweetException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
