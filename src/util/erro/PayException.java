package util.erro;

public class PayException extends RuntimeException{
    public PayException(String message) {
        super(message);
    }
    public String getMessage() {
        return super.getMessage();
    }
}
