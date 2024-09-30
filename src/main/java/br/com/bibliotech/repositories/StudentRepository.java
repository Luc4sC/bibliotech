package br.com.bibliotech.repositories;

import br.com.bibliotech.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByRm(String rm);
}
