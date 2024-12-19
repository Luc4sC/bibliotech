package br.com.bibliotech.domain.model;

import br.com.bibliotech.domain.exception.CannotBeAcceptedException;
import br.com.bibliotech.domain.exception.CannotBeRejectedException;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Table(name = "requests")
@Entity(name = "Request")
public class LoanRequest {

    @Deprecated
    LoanRequest(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false, nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate requestDate;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @OneToMany(mappedBy = "request")
    private List<BookLoanRequest> bookLoanRequests;

    @OneToOne(mappedBy = "request")
    private Loan loan;

    public LoanRequest(User user) {
        this.requestDate = LocalDate.now();
        this.status = RequestStatus.PENDING;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public User getUser() {
        return user;
    }

    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        bookLoanRequests.forEach(bookRequest -> books.add(bookRequest.getBook()));

        return Collections.unmodifiableList(books);
    }

    public RequestStatus getStatus() {
        return status;
    }

    boolean isNotPending() {
        return !status.isPending();
    }

    public void accept() {
        if (isNotPending())
            throw new CannotBeAcceptedException("Cannot accept a loan request with the following status: " + status.getName());

        this.status = RequestStatus.ACCEPTED;
    }

    public void reject() {
        if (isNotPending())
            throw new CannotBeRejectedException("Cannot reject a loan request with the following status: " + status.getName());

        this.status = RequestStatus.REJECTED;
    }

}
