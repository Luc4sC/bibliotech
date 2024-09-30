package br.com.bibliotech.convertes;

import br.com.bibliotech.entities.Book;
import br.com.bibliotech.responses.*;

import java.util.ArrayList;
import java.util.List;

public class BookResponseConverter {

    public static BookResponse convert(Book book){
        AuthorResponse authorResponse = AuthorResponseConverter.convert(book.getAuthor());
        CategoryResponse categoryResponse = CategoryResponseConverter.convert(book.getCategory());
        GenreResponse genreResponse = GenreResponseConverter.convert(book.getGenre());
        PublisherResponse publisherResponse = PublisherResponseConverter.convert(book.getPublisher());

        return new BookResponse(book.getTitle(), book.getSubtitle(), book.getSynopsis(), book.getPages(), book.getPublishDate(),
                book.getIsbn(), authorResponse, categoryResponse, genreResponse, publisherResponse);
    }

    public static List<BookResponse> convertList(List<Book> books){
        List<BookResponse> bookResponses = new ArrayList<>();

        books.forEach(book -> {
            BookResponse bookResponse = convert(book);
            bookResponses.add(bookResponse);
        });

        return bookResponses;
    }
}
