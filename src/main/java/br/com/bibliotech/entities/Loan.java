package br.com.bibliotech.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Table(name = "loans")
@Entity(name = "Loan")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate startDate;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate endDate;

    @Setter
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate finishedDate;

    @Column(nullable = false)
    @Setter
    private boolean finished;

    @Column(nullable = false)
    @ManyToOne
    private Student borrower;

    @OneToMany(mappedBy = "loan")
    private List<Item> items;

    public Loan(LocalDate startDate,LocalDate endDate, Student borrower){
        this.startDate = startDate;
        this.endDate = endDate;
        this.borrower = borrower;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", borrower=" + borrower +
                '}';
    }
}
