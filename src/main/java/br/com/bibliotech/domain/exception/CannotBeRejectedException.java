package br.com.bibliotech.domain.exception;

public class CannotBeRejectedException extends RuntimeException {

    public CannotBeRejectedException(String message) {
        super(message);
    }
}
