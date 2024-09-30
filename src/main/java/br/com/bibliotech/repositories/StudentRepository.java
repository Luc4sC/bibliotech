package br.com.bibliotech.repositories;

import br.com.bibliotech.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
