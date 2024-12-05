package br.com.bibliotech.domain.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

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
    private User borrower;

    @OneToMany(mappedBy = "request")
    private List<BookRequest> bookRequests;

    @OneToOne(mappedBy = "request")
    private Loan loan;

    public Request(User borrower) {
        this.status = RequestStatus.PENDING;
        this.borrower = borrower;
    }

    public void accept() {
        this.status = RequestStatus.ACCEPTED;
    }

    public void reject() {
        this.status = RequestStatus.REJECTED;
    }

}
