package br.com.bibliotech.presentation.converter;

import br.com.bibliotech.domain.model.*;
import br.com.bibliotech.domain.service.AuthorService;
import br.com.bibliotech.domain.service.CategoryService;
import br.com.bibliotech.domain.service.GenreService;
import br.com.bibliotech.domain.service.PublisherService;
import br.com.bibliotech.presentation.dto.BookDTO;
import br.com.bibliotech.presentation.responses.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class BookConverter {

    private final AuthorConverter authorConverter = new AuthorConverter();
    private final CategoryConverter categoryConverter = new CategoryConverter();
    private final GenreConverter genreConverter = new GenreConverter();
    private final PublisherConverter publisherConverter = new PublisherConverter();
    private AuthorService authorService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private PublisherService publisherService;

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
}
