package br.com.bibliotech.responses;

public record CopyResponse(int numeration, boolean available, BookResponse bookResponse) {
}
