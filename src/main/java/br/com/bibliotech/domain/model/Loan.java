package br.com.bibliotech.domain.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor
@Table(name = "loans")
@Entity(name = "Loan")
public class Loan {

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
    private Request request;

    public Loan(LocalDate endDate, Request request) {
        this.startDate = LocalDate.now();
        this.endDate = endDate;
        this.request = request;
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

    public Request getRequest() {
        return request;
    }

    public boolean isFinished() {
        return finishedDate != null;
    }

    public void finish() {
        this.finishedDate = LocalDate.now();
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
