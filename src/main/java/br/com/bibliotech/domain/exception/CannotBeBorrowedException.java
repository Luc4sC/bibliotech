package br.com.bibliotech.domain.exception;

public class CannotBeBorrowedException extends RuntimeException {

    public CannotBeBorrowedException(String message) {
        super(message);
    }
}
