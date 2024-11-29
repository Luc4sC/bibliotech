package br.com.bibliotech.domain.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Table(name = "publishers")
@Entity(name = "Publisher")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String tradeName;

    @Column(nullable = false, unique = true)
    private String legalName;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate foundationDate;

    @Column(nullable = false)
    private boolean deleted;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "publisher")
    private List<Book> books;

    public Publisher(String tradeName, String legalName, LocalDate foundationDate, Address address){
        this.tradeName = tradeName;
        this.legalName = legalName;
        this.foundationDate = foundationDate;
        this.address = address;
        this.deleted = false;
    }

    public String getTradeName() {
        return tradeName;
    }

    public String getLegalName() {
        return legalName;
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
        this.legalName = publisher.legalName;
        this.foundationDate = publisher.foundationDate;
        this.address = publisher.address;
    }

    public void delete() {
        this.deleted = true;
    }

    public void addBook(Book book) {
        book.setPublisher(this);
        this.books.add(book);
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
