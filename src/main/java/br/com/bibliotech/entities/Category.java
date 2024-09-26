package br.com.bibliotech.entities;

import br.com.bibliotech.dtos.CategoryDTO;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "categories")
@Entity(name = "Category")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String name;

    public Category(CategoryDTO categoryDTO){
        this.name = categoryDTO.name();
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
