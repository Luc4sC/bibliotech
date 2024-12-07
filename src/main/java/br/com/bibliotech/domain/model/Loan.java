package br.com.bibliotech.domain.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor
@Table(name = "loans")
@Entity(name = "Loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate finishedDate;
    @OneToOne
    @JoinColumn(name = "request_id", nullable = false, unique = true)
    private Request request;

    public Loan(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isEnded() {
        return finishedDate != null;
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
