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

    @ManyToOne
    private Loan loan;

    @ManyToOne
    private Copy copy;

    @Override
    public String toString() {
        return "Item{" +
                "loan=" + loan +
                ", copy=" + copy +
                '}';
    }

}
