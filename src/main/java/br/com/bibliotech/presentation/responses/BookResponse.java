package br.com.bibliotech.presentation.responses;

import java.time.LocalDate;

public record BookResponse(String isbn, String title, String subtitle, String synopsis, int pages, LocalDate publishDate,
                           int quantity, AuthorResponse authorResponse, CategoryResponse categoryResponse,
                           GenreResponse genreResponse, PublisherResponse publisherResponse) {
}
