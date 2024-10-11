package br.com.bibliotech.domain.service;

import br.com.bibliotech.application.converter.BookResponseConverter;
import br.com.bibliotech.domain.model.*;
import br.com.bibliotech.application.dto.BookDTO;
import br.com.bibliotech.domain.repository.Authors;
import br.com.bibliotech.infrastructure.repository.BookRepository;
import br.com.bibliotech.infrastructure.repository.CategoryRepository;
import br.com.bibliotech.infrastructure.repository.GenreRepository;
import br.com.bibliotech.infrastructure.repository.PublisherRepository;
import br.com.bibliotech.presentation.response.BookResponse;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private Authors authors;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    private final BookResponseConverter bookResponseConverter = new BookResponseConverter();

    @Transactional
    public BookResponse save(BookDTO bookDTO){
        Author author = authors.findById(bookDTO.authorId());
        Category category = categoryRepository.findById(bookDTO.categoryId()).orElseThrow();
        Genre genre = genreRepository.findById(bookDTO.genreId()).orElseThrow();
        Publisher publisher = publisherRepository.findById(bookDTO.publishCompanyId()).orElseThrow();

        Book book = new Book(bookDTO, author, category, genre, publisher);
        bookRepository.save(book);

        log.info("Book Created: " + book);

        return bookResponseConverter.convert(book);
    }

    public List<BookResponse> getAll(){
        List<Book> books = bookRepository.findAll();

        return bookResponseConverter.convertEach(books);
    }

    public BookResponse getById(Long id){
        Book book = bookRepository.findById(id).orElseThrow();

        return bookResponseConverter.convert(book);
    }

    @Transactional
    public BookResponse update(BookDTO bookDTO, Long id){
        Book book = bookRepository.findById(id).orElseThrow();

        Author author = authors.findById(bookDTO.authorId());
        Category category = categoryRepository.findById(bookDTO.categoryId()).orElseThrow();
        Genre genre = genreRepository.findById(bookDTO.genreId()).orElseThrow();
        Publisher publisher = publisherRepository.findById(bookDTO.publishCompanyId()).orElseThrow();

        book.setTitle(bookDTO.title());
        book.setSubtitle(bookDTO.subtitle());
        book.setSynopsis(bookDTO.synopsis());
        book.setPages(bookDTO.pages());
        book.setPublishDate(bookDTO.publishDate());
        book.setAuthor(author);
        book.setCategory(category);
        book.setGenre(genre);
        book.setPublisher(publisher);

        log.info("Book updated: " + book);

        return bookResponseConverter.convert(book);
    }

    @Transactional
    public void delete(Long id){
        Book book = bookRepository.findById(id).orElseThrow();
        book.setDeleted(true);

        log.info("Book deleted: " + book);
    }

}
