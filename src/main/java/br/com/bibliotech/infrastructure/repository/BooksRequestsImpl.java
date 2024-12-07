package br.com.bibliotech.infrastructure.repository;

import br.com.bibliotech.domain.model.BookRequest;
import br.com.bibliotech.domain.repository.BooksRequests;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
class BooksRequestsImpl implements BooksRequests {

    private final BookRequestRepository bookRequestRepository;

    @Autowired
    BooksRequestsImpl(BookRequestRepository bookRequestRepository) {
        this.bookRequestRepository = bookRequestRepository;
    }


    @Transactional
    @Override
    public void save(BookRequest bookRequest) {
        bookRequestRepository.save(bookRequest);
    }
}
