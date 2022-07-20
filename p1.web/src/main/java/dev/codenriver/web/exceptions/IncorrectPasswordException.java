package dev.codenriver.web.exceptions;

public class IncorrectPasswordException extends Exception{
    public IncorrectPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectPasswordException(Throwable cause) {
        super(cause);
    }

    public IncorrectPasswordException(String message) {
        super(message);
    }

    public IncorrectPasswordException() {}
}
