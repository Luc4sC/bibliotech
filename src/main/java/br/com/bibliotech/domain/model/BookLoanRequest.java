package br.com.bibliotech.domain.model;

import jakarta.persistence.*;

import java.util.Objects;

@Table(name = "books_loan_requests")
@Entity(name = "BookLoanRequest")
public class BookLoanRequest {

    @Deprecated
    BookLoanRequest(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", updatable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "loan_request_id", updatable = false)
    private LoanRequest loanRequest;

    public BookLoanRequest(Book book, LoanRequest loanRequest) {
        this.book = book;
        this.loanRequest = loanRequest;
    }

    public Book getBook() {
        return book;
    }

    @Override
    public String toString() {
        return "BookLoan{" +
                "id=" + id +
                ", book=" + book +
                ", request=" + loanRequest +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        BookLoanRequest bookLoanRequest = (BookLoanRequest) object;
        return Objects.equals(id, bookLoanRequest.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
