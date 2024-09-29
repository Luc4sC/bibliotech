package br.com.bibliotech.responses;

import java.time.LocalDate;

public record BookResponse(String title, String subtitle, String synopsis, LocalDate publishDate, String isbn,
                           AuthorResponse author, CategoryResponse category, GenreResponse genre,
                           PublisherResponse publisher) {
}
