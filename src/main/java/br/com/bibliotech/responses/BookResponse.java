package br.com.bibliotech.responses;

import br.com.bibliotech.entities.*;

import java.time.LocalDate;

public record BookResponse(String title, String subtitle, String synopsis, LocalDate publishDate, String isbn,
                           AuthorResponse author, CategoryResponse category, GenreResponse genre,
                           PublisherResponse publisher) {

    public static BookResponse converter(Book book){
        AuthorResponse authorResponse = AuthorResponse.converter(book.getAuthor());
        CategoryResponse categoryResponse = CategoryResponse.converter(book.getCategory());
        GenreResponse genreResponse = GenreResponse.converter(book.getGenre());
        PublisherResponse publisherResponse = PublisherResponse.converter(book.getPublisher());

        return new BookResponse(book.getTitle(), book.getSubtitle(), book.getSynopsis(), book.getPublishDate(),
                book.getIsbn(), authorResponse, categoryResponse, genreResponse, publisherResponse);
    }

}
