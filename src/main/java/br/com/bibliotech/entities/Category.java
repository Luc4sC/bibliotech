package br.com.bibliotech.entities;

import br.com.bibliotech.dtos.CategoryDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "categories")
@Entity(name = "Category")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String name;

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
