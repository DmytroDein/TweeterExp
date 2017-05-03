package twitter.rest.exceptions;

public class ExceptionDescriptor {
    public  String name;
    public  Throwable exception;
    public  String message;

    public ExceptionDescriptor(String name, Throwable exception) {
        this.name = name;
        this.exception = exception;
    }


    public ExceptionDescriptor(String name, String message) {
        this.name = name;
        this.message = message;
    }
}
