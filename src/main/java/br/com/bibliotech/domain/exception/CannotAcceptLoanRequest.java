package br.com.bibliotech.domain.exception;

public class CannotAcceptLoanRequest extends RuntimeException {

    public CannotAcceptLoanRequest(String message) {
        super(message);
    }
}
