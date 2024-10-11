package br.com.bibliotech.domain.model;

import br.com.bibliotech.application.dto.StudentDTO;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Table(name = "students")
@Entity(name = "Student")
@NoArgsConstructor
@EqualsAndHashCode(of = "rm")
@Getter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String rm;

    @Column(nullable = false)
    @Setter
    private String fullName;

    @Column(nullable = false)
    @Setter
    private String cellPhone;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthdate;

    @Column(nullable = false)
    @Setter
    private boolean blocked;

    @Column(nullable = false)
    @Setter
    private boolean deleted;

    @Setter
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "borrower")
    private List<Loan> loans;

    public Student(StudentDTO studentDTO){
        this.rm = studentDTO.rm();
        this.fullName = studentDTO.fullName();
        this.cellPhone = studentDTO.cellPhone();
        this.birthdate = studentDTO.birthdate();
        this.address = new Address(studentDTO.addressDTO());
        this.blocked = false;
        this.deleted = false;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", rm='" + rm + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
