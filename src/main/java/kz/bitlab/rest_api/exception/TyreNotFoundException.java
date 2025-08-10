package kz.bitlab.rest_api.exception;

public class TyreNotFoundException extends RuntimeException {
    public TyreNotFoundException(String message) {
        super(message);
    }
}
