package br.com.bibliotech.convertes;

import br.com.bibliotech.entities.Book;
import br.com.bibliotech.responses.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class BookResponseConverter implements Converter<BookResponse, Book>{

    @Autowired
    private AuthorResponseConverter authorResponseConverter;

    @Autowired
    private CategoryResponseConverter categoryResponseConverter;

    @Autowired
    private GenreResponseConverter genreResponseConverter;

    @Autowired
    private PublisherResponseConverter publisherResponseConverter;

    @Autowired
    private CopyResponseConverter copyResponseConverter;

    @Override
    public BookResponse convert(Book book){
        AuthorResponse authorResponse = authorResponseConverter.convert(book.getAuthor());
        CategoryResponse categoryResponse = categoryResponseConverter.convert(book.getCategory());
        GenreResponse genreResponse = genreResponseConverter.convert(book.getGenre());
        PublisherResponse publisherResponse = publisherResponseConverter.convert(book.getPublisher());
        List<CopyResponse> copyResponses = copyResponseConverter.convertEach(book.getCopies());

        return new BookResponse(book.getTitle(), book.getSubtitle(), book.getSynopsis(), book.getPages(), book.getPublishDate(),
                book.getIsbn(), authorResponse, categoryResponse, genreResponse, publisherResponse, copyResponses);
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
