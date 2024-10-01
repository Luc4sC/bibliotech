package br.com.bibliotech.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "items")
@Entity(name = "Item")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"loan", "copy"})
@Getter
public class Item {

    @EmbeddedId
    private ItemId id;

    @ManyToOne
    @JoinColumn(name = "loanId", insertable = false, updatable = false)
    private Loan loan;

    @ManyToOne
    @JoinColumn(name = "copyId", insertable = false, updatable = false)
    private Copy copy;

    @Override
    public String toString() {
        return "Item{" +
                "loan=" + loan +
                ", copy=" + copy +
                '}';
    }

}
