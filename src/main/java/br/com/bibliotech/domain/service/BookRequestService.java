package br.com.bibliotech.domain.service;

import br.com.bibliotech.domain.model.BookRequest;
import br.com.bibliotech.domain.repository.BooksRequests;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookRequestService {

    private final BooksRequests booksRequests;

    @Autowired
    public BookRequestService(BooksRequests booksRequests) {
        this.booksRequests = booksRequests;
    }

    public void save(BookRequest bookRequest) {
        booksRequests.save(bookRequest);
        log.info("Book Request created: " + bookRequest);
    }

}
