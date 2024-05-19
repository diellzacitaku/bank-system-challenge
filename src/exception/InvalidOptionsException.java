package exception;

public class InvalidOptionsException extends RuntimeException {

    public InvalidOptionsException() {
        super("Option is invalid!");
    }

}
