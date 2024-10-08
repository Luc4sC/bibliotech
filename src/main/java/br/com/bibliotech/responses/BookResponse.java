package br.com.bibliotech.responses;

import java.time.LocalDate;

public record BookResponse(String title, String subtitle, String synopsis, int pages, LocalDate publishDate,
                           AuthorResponse author, CategoryResponse category, GenreResponse genre,
                           PublisherResponse publisher) {
}
