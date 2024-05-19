package exception;

public class InvalidAmountException extends RuntimeException {

    public InvalidAmountException() {
        super("Amount given is invalid!");
    }

}
