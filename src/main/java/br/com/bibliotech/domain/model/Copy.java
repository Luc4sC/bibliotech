package br.com.bibliotech.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "copies")
@Entity(name = "Copy")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Copy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Setter
    private int numeration;

    @Column(nullable = false)
    @Setter
    private String isbn;

    @Column(nullable = false)
    @Setter
    private boolean available;

    @Column(nullable = false)
    @Setter
    private boolean deleted;

    @ManyToOne
    private Book book;

    @Override
    public String toString() {
        return "Copy{" +
                "id=" + id +
                ", numeration=" + numeration +
                ", available=" + available +
                ", isbn='" + isbn + '\'' +
                '}';
    }

}
