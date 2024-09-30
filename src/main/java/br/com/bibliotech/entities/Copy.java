package br.com.bibliotech.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "copies")
@Entity(name = "Copy")
@NoArgsConstructor
@EqualsAndHashCode(of = {"numeration", "book"})
@Getter
public class Copy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private int numeration;

    @Setter
    private boolean deleted;

    @Setter
    private boolean available;

    @ManyToOne
    private Book book;

    @OneToMany(mappedBy = "copy")
    private List<Item> items;

    @Override
    public String toString() {
        return "Copy{" +
                "id=" + id +
                ", numeration=" + numeration +
                ", deleted=" + deleted +
                ", available=" + available +
                '}';
    }

}
