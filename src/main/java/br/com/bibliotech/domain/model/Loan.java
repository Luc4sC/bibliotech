package br.com.bibliotech.domain.model;

import br.com.bibliotech.domain.exception.LoanAlreadyFinishedException;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Table(name = "loans")
@Entity(name = "Loan")
public class Loan {

    @Deprecated
    Loan(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate endDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate finishedDate;

    @OneToOne
    @JoinColumn(name = "request_id", nullable = false, unique = true)
    private LoanRequest loanRequest;

    public Loan(LocalDate endDate, LoanRequest loanRequest) {
        this.startDate = LocalDate.now();
        this.endDate = endDate;
        this.loanRequest = loanRequest;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDate getFinishedDate() {
        return finishedDate;
    }

    public LoanRequest getLoanRequest() {
        return loanRequest;
    }

    public List<Book> getBooks() {
        return loanRequest.getBooks();
    }

    public void finish() {
        if (isFinished())
            throw new LoanAlreadyFinishedException("Loan: " + this + " is already finished");

        this.finishedDate = LocalDate.now();
    }

    public boolean isFinished() {
        return finishedDate != null;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", loanRequest=" + loanRequest +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Loan loan = (Loan) object;
        return Objects.equals(id, loan.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
