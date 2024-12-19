package br.com.bibliotech.domain.exception;

public class CannotRejectLoanRequest extends RuntimeException {

    public CannotRejectLoanRequest(String message) {
        super(message);
    }
}
