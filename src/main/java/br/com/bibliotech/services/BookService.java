package br.com.bibliotech.services;

import br.com.bibliotech.dtos.BookDTO;
import br.com.bibliotech.entities.*;
import br.com.bibliotech.repositories.*;
import br.com.bibliotech.responses.BookResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    public BookResponse save(BookDTO bookDTO){
        Author author = authorRepository.findById(bookDTO.authorId()).orElseThrow();
        Category category = categoryRepository.findById(bookDTO.categoryId()).orElseThrow();
        Genre genre = genreRepository.findById(bookDTO.genreId()).orElseThrow();
        Publisher publisher = publisherRepository.findById(bookDTO.publishCompanyId()).orElseThrow();

        Book book = new Book(bookDTO, author, category, genre, publisher);
        bookRepository.save(book);

        log.info("Book Created: " + book);

        return BookResponse.converter(book);
    }

    public List<BookResponse> getAll(){
        List<Book> books = bookRepository.findAll();

        return convertEach(books);
    }

    public BookResponse getById(Long id){
        Book book = bookRepository.findById(id).orElseThrow();

        return BookResponse.converter(book);
    }

    public BookResponse update(BookDTO bookDTO, Long id){
        Book book = bookRepository.findById(id).orElseThrow();

        Author author = authorRepository.findById(bookDTO.authorId()).orElseThrow();
        Category category = categoryRepository.findById(bookDTO.categoryId()).orElseThrow();
        Genre genre = genreRepository.findById(bookDTO.genreId()).orElseThrow();
        Publisher publisher = publisherRepository.findById(bookDTO.publishCompanyId()).orElseThrow();

        book.setTitle(bookDTO.title());
        book.setSubtitle(bookDTO.subtitle());
        book.setIsbn(bookDTO.isbn());
        book.setSynopsis(bookDTO.synopsis());
        book.setPublishDate(bookDTO.publishDate());
        book.setAuthor(author);
        book.setCategory(category);
        book.setGenre(genre);
        book.setPublisher(publisher);

        log.info("Book updated: " + book);

        return BookResponse.converter(book);
    }

    public void delete(Long id){
        Book book = bookRepository.findById(id).orElseThrow();
        book.setDeleted(true);

        log.info("Book deleted: " + book);
    }

    private List<BookResponse> convertEach(List<Book> books){
        List<BookResponse> bookResponses = new ArrayList<>();

        books.forEach(book -> {
            BookResponse bookResponse = BookResponse.converter(book);
            bookResponses.add(bookResponse);
        });

        return bookResponses;
    }
}
