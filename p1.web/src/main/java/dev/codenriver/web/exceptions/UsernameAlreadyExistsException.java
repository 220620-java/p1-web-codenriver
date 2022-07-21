package dev.codenriver.web.exceptions;

public class UsernameAlreadyExistsException extends Exception{
    public UsernameAlreadyExistsException(String message, Throwable cause) { super(message, cause);}

    public UsernameAlreadyExistsException(Throwable cause) { super(cause); }

    public UsernameAlreadyExistsException(String message) { super(message); }

    public UsernameAlreadyExistsException() {}
}
