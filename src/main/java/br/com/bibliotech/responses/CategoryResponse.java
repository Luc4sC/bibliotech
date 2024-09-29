package br.com.bibliotech.responses;

import java.util.List;

public record CategoryResponse(String name, List<BookResponse> books) {
}
