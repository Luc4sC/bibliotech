package br.com.bibliotech.domain.model;

import br.com.bibliotech.application.dto.CopyDTO;
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

    @OneToMany(mappedBy = "copy")
    private List<Item> items;

    public Copy(CopyDTO copyDTO){
        this.numeration = copyDTO.numeration();
        this.isbn = copyDTO.isbn();
        this.available = true;
        this.deleted = false;
    }

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
