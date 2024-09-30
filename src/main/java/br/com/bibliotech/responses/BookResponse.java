package br.com.bibliotech.responses;

import java.time.LocalDate;
import java.util.List;

public record BookResponse(String title, String subtitle, String synopsis, int pages, LocalDate publishDate, String isbn,
                           AuthorResponse author, CategoryResponse category, GenreResponse genre,
                           PublisherResponse publisher, List<CopyResponse> copies) {
}
