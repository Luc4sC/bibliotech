package br.com.bibliotech.domain.exception;

public class AllBooksInStockException extends RuntimeException {

    public AllBooksInStockException(String message) {
        super(message);
    }
}
