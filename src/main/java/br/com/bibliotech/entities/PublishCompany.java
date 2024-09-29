package br.com.bibliotech.entities;

import br.com.bibliotech.dtos.PublishingCompanyDTO;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Table(name = "publish_companies")
@Entity(name = "PublishCompany")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class PublishCompany {

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

    public PublishCompany(PublishingCompanyDTO publishingCompanyDTO){
        this.tradeName = publishingCompanyDTO.tradeName();
        this.name = publishingCompanyDTO.name();
        this.foundationDate = publishingCompanyDTO.foundationDate();
        this.address = new Address(publishingCompanyDTO.addressDTO());
        this.deleted = false;
    }
}
