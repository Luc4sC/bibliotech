package br.com.bibliotech.responses;

import java.util.List;

public record GenreResponse(String name, List<BookResponse> books) {
}
