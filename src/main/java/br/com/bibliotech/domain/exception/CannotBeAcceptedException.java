package br.com.bibliotech.domain.exception;

public class CannotBeAcceptedException extends RuntimeException {

    public CannotBeAcceptedException(String message) {
        super(message);
    }
}
