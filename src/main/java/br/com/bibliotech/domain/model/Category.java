package br.com.bibliotech.domain.model;

import br.com.bibliotech.application.dto.CategoryDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "categories")
@Entity(name = "Category")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Setter
    private String name;

    @Column(nullable = false)
    @Setter
    private boolean deleted;

    @OneToMany(mappedBy = "category")
    private List<Book> books;

    public Category(CategoryDTO categoryDTO){
        this.name = categoryDTO.name();
        this.deleted = false;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
