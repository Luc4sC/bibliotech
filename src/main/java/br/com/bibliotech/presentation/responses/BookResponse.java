package br.com.bibliotech.presentation.responses;

import java.time.LocalDate;

public record BookResponse(String isbn, String title, String subtitle, String synopsis, int pages, LocalDate publishDate,
                           int quantity, String authorUrl, String categoryUrl, String genreUrl, String publisherUrl) {
}
