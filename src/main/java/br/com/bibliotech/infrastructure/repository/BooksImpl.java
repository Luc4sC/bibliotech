package br.com.bibliotech.infrastructure.repository;

import br.com.bibliotech.domain.model.Book;
import br.com.bibliotech.domain.repository.Books;
import br.com.bibliotech.infrastructure.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
class BooksImpl implements Books {

    private final BookRepository bookRepository;

    @Autowired
    BooksImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    @Transactional
    public void update(Book book, Book bookUpdate) {
        book.update(book);
    }

    @Override
    @Transactional
    public void delete(Book book) {
        book.delete();
    }

    @Override
    public Book findById(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty())
            throw new NotFoundException("Book with id: " + id + " not found");

        return bookOptional.get();
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }


    @Override
    public Book findByIsbn(String isbn) {
        Optional<Book> bookOptional = bookRepository.findByIsbn(isbn);
        if (bookOptional.isEmpty())
            throw new NotFoundException("Book with ISBN: " + isbn + " not found");

        return bookOptional.get();
    }
}
