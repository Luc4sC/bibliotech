package br.com.bibliotech.domain.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor
@Table(name = "requests")
@Entity(name = "Request")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated
    private RequestStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @OneToMany(mappedBy = "request")
    private List<BookRequest> bookRequests;

    @OneToOne(mappedBy = "request")
    private Loan loan;

    public Request(User borrower) {
        this.status = RequestStatus.PENDING;
        this.user = borrower;
    }

    public User getUser() {
        return user;
    }

    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        bookRequests.forEach(bookRequest -> {
            books.add(bookRequest.getBook());
        });

        return Collections.unmodifiableList(books);
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void accept() {
        this.status = RequestStatus.ACCEPTED;
    }

    public void reject() {
        this.status = RequestStatus.REJECTED;
    }

}
