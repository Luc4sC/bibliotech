package br.com.bibliotech.entities;

import br.com.bibliotech.dtos.StudentDTO;
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

    private String rm;

    @Setter
    private String fullName;

    @Setter
    private String cellPhone;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthdate;

    @Setter
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "borrower")
    private List<Loan> loans;

    @Setter
    private boolean blocked;

    @Setter
    private boolean deleted;

    public Student(StudentDTO studentDTO){
        this.rm = studentDTO.rm();
        this.fullName = studentDTO.fullName();
        this.cellPhone = studentDTO.cellPhone();
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
                ", cellPhone='" + cellPhone + '\'' +
                ", address=" + address +
                '}';
    }

}
