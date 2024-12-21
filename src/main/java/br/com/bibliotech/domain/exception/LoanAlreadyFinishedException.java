package br.com.bibliotech.domain.exception;

public class LoanAlreadyFinishedException extends RuntimeException {

    public LoanAlreadyFinishedException(String message) {
        super(message);
    }
}
