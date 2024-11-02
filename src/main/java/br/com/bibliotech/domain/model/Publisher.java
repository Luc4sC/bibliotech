package br.com.bibliotech.domain.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Table(name = "publishers")
@Entity(name = "Publisher")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tradeName;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate foundationDate;

    @Column(nullable = false)
    private boolean deleted;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "publisher")
    private List<Book> books;

    public Publisher(String tradeName, String name, LocalDate foundationDate, Address address){
        this.tradeName = tradeName;
        this.name = name;
        this.foundationDate = foundationDate;
        this.address = address;
        this.deleted = false;
    }

    public String getTradeName() {
        return tradeName;
    }

    public String getName() {
        return name;
    }

    public LocalDate getFoundationDate() {
        return foundationDate;
    }

    public Address getAddress() {
        return address;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void update(Publisher publisher) {
        this.tradeName = publisher.tradeName;
        this.name = publisher.name;
        this.foundationDate = publisher.foundationDate;
        this.address = publisher.address;
    }

    public void delete() {
        this.deleted = true;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", tradeName='" + tradeName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Publisher publisher = (Publisher) object;
        return Objects.equals(id, publisher.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
