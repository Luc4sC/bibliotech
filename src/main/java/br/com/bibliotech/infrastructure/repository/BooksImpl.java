package br.com.bibliotech.infrastructure.repository;

import br.com.bibliotech.domain.model.Book;
import br.com.bibliotech.domain.repository.Books;
import br.com.bibliotech.infrastructure.exception.ConflictException;
import br.com.bibliotech.infrastructure.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

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
        try {
            bookRepository.save(book);
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new ConflictException("Book with ISBN: " + book.getIsbn() + " already exist!");
        }
    }

    @Override
    @Transactional
    public void update(Book book) {
        try {
            bookRepository.flush();
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new ConflictException("Cannot update isbn");
        }
    }

    @Override
    @Transactional
    public void delete(Book book) {
        book.delete();
        bookRepository.flush();
    }

    @Override
    public Book findById(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty())
            throw new NotFoundException("Book with id: " + id + " not found");

        return bookOptional.get();
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }


    @Override
    public Book findByIsbn(String isbn) {
        Optional<Book> bookOptional = bookRepository.findByIsbn(isbn);
        if (bookOptional.isEmpty())
            throw new NotFoundException("Book with ISBN: " + isbn + " not found");

        return bookOptional.get();
    }

    @Override
    public Page<Book> findByLoanRequestId(Long loanRequestId, Pageable pageable) {
        return bookRepository.findByLoanRequestId(loanRequestId, pageable);
    }
}
