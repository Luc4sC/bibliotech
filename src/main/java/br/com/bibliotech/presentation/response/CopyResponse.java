package br.com.bibliotech.presentation.response;

public record CopyResponse(int numeration, boolean available, String isbn, BookResponse bookResponse) {
}
