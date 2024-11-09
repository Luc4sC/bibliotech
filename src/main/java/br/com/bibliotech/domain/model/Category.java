package br.com.bibliotech.domain.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Table(name = "categories")
@Entity(name = "Category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private boolean deleted;

    @OneToMany(mappedBy = "category")
    private List<Book> books;

    public Category(String name){
        this.name = name;
        this.deleted = false;
    }

    public String getName() {
        return name;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void update(Category category) {
        this.name = category.name;
    }

    public void delete() {
        this.deleted = true;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Category category = (Category) object;
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
