package br.com.bibliotech.domain.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Table(name = "users")
@Entity(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthdate;

    @Embedded
    private Address address;

    @Column(nullable = false)
    private boolean blocked;

    @Column(nullable = false)
    private boolean deleted;

    @OneToMany(mappedBy = "user")
    List<LoanRequest> loanRequests;

    public User(String email, String fullName, LocalDate birthdate, Address address) {
        this.email = email;
        this.fullName = fullName;
        this.birthdate = birthdate;
        this.address = address;
        this.blocked = false;
        this.deleted = false;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public Address getAddress() {
        return address;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void update(User userUpdate) {
        this.email = userUpdate.email;
        this.fullName = userUpdate.fullName;
        this.birthdate = userUpdate.birthdate;
        this.address = userUpdate.address;
    }

    public void delete() {
        this.deleted = true;
    }

    public void block() {
        this.blocked = true;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        User user = (User) object;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
