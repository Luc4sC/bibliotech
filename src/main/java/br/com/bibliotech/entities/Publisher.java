package br.com.bibliotech.entities;

import br.com.bibliotech.dtos.PublisherDTO;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Table(name = "publishers")
@Entity(name = "Publisher")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Publisher {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String tradeName;

    @Setter
    private String name;

    @Setter
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate foundationDate;

    @Setter
    private boolean deleted;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "publishCompany")
    private List<Book> books;

    public Publisher(PublisherDTO publisherDTO){
        this.tradeName = publisherDTO.tradeName();
        this.name = publisherDTO.name();
        this.foundationDate = publisherDTO.foundationDate();
        this.address = new Address(publisherDTO.addressDTO());
        this.deleted = false;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", tradeName='" + tradeName + '\'' +
                ", name='" + name + '\'' +
                ", foundationDate=" + foundationDate +
                ", deleted=" + deleted +
                ", address=" + address +
                ", books=" + books +
                '}';
    }
}
