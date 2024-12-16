package br.com.bibliotech.presentation.converter;

import br.com.bibliotech.domain.model.*;
import br.com.bibliotech.domain.service.AuthorService;
import br.com.bibliotech.domain.service.CategoryService;
import br.com.bibliotech.domain.service.GenreService;
import br.com.bibliotech.domain.service.PublisherService;
import br.com.bibliotech.presentation.dto.BookDTO;
import br.com.bibliotech.presentation.responses.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookConverter {

    private final AuthorConverter authorConverter;
    private final CategoryConverter categoryConverter;
    private final GenreConverter genreConverter;
    private final PublisherConverter publisherConverter;
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final GenreService genreService;
    private final PublisherService publisherService;

    @Autowired
    public BookConverter(AuthorService authorService, CategoryService categoryService, GenreService genreService,
                         PublisherService publisherService, AuthorConverter authorConverter,
                         CategoryConverter categoryConverter, GenreConverter genreConverter,
                         PublisherConverter publisherConverter) {
        this.authorConverter = authorConverter;
        this.categoryConverter = categoryConverter;
        this.genreConverter = genreConverter;
        this.publisherConverter = publisherConverter;
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.genreService = genreService;
        this.publisherService = publisherService;
    }

    public Book fromDTO(BookDTO bookDTO) {
        Author author = authorService.findById(bookDTO.authorId());
        Category category = categoryService.findById(bookDTO.categoryId());
        Genre genre = genreService.findById(bookDTO.genreId());
        Publisher publisher = publisherService.findById(bookDTO.publisherId());

        return new Book(bookDTO.isbn(), bookDTO.title(), bookDTO.subtitle(), bookDTO.synopsis(), bookDTO.pages(),
                bookDTO.publishDate(), bookDTO.quantity(), author, category, genre, publisher);
    }

    public BookResponse fromModel(Book book) {
        AuthorResponse authorResponse = authorConverter.fromModel(book.getAuthor());
        CategoryResponse categoryResponse = categoryConverter.fromModel(book.getCategory());
        GenreResponse genreResponse = genreConverter.fromModel(book.getGenre());
        PublisherResponse publisherResponse = publisherConverter.fromModel(book.getPublisher());

        return new BookResponse(book.getIsbn(), book.getTitle(), book.getSubtitle(), book.getSynopsis(), book.getPages(),
                book.getPublishDate(), book.getQuantity(), authorResponse, categoryResponse, genreResponse,
                publisherResponse);
    }

    public List<BookResponse> fromModelList(List<Book> books) {
        List<BookResponse> bookResponses = new ArrayList<>();
        books.forEach(book -> bookResponses.add(fromModel(book)));

        return bookResponses;
    }

    public Book fromDTO(Long id, BookDTO bookDTO) {
        Author author = authorService.findById(bookDTO.authorId());
        Category category = categoryService.findById(bookDTO.categoryId());
        Genre genre = genreService.findById(bookDTO.genreId());
        Publisher publisher = publisherService.findById(bookDTO.publisherId());

        return new Book(id, bookDTO.isbn(), bookDTO.title(), bookDTO.subtitle(), bookDTO.synopsis(), bookDTO.pages(),
                bookDTO.publishDate(), bookDTO.quantity(), author, category, genre, publisher);
    }

}
