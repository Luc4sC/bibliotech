package br.com.bibliotech.application.converter;

import br.com.bibliotech.domain.model.Book;
import br.com.bibliotech.presentation.response.*;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class BookResponseConverter implements Converter<BookResponse, Book>{

    @Override
    public BookResponse convert(Book book){
        AuthorResponseConverter authorResponseConverter = new AuthorResponseConverter();
        AuthorResponse authorResponse = authorResponseConverter.convert(book.getAuthor());

        CategoryResponseConverter categoryResponseConverter = new CategoryResponseConverter();
        CategoryResponse categoryResponse = categoryResponseConverter.convert(book.getCategory());

        GenreResponseConverter genreResponseConverter = new GenreResponseConverter();
        GenreResponse genreResponse = genreResponseConverter.convert(book.getGenre());

        PublisherResponseConverter publisherResponseConverter = new PublisherResponseConverter();
        PublisherResponse publisherResponse = publisherResponseConverter.convert(book.getPublisher());

        return new BookResponse(book.getTitle(), book.getSubtitle(), book.getSynopsis(), book.getPages(), book.getPublishDate(),
                authorResponse, categoryResponse, genreResponse, publisherResponse);
    }

    @Override
    public List<BookResponse> convertEach(List<Book> books){
        List<BookResponse> bookResponses = new ArrayList<>();

        books.forEach(book -> {
            BookResponse bookResponse = convert(book);
            bookResponses.add(bookResponse);
        });

        return bookResponses;
    }
}
