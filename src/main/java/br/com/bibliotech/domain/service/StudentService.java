package br.com.bibliotech.domain.service;

import br.com.bibliotech.application.converter.StudentResponseConverter;
import br.com.bibliotech.application.dto.StudentDTO;
import br.com.bibliotech.domain.model.Address;
import br.com.bibliotech.domain.model.Student;
import br.com.bibliotech.infrastructure.repository.StudentRepository;
import br.com.bibliotech.presentation.response.StudentResponse;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    private final StudentResponseConverter studentResponseConverter = new StudentResponseConverter();

    @Transactional
    public StudentResponse save(StudentDTO studentDTO){
        Student student = new Student(studentDTO);
        studentRepository.save(student);

        log.info("Student created: " + student);
        return studentResponseConverter.convert(student);
    }

    public StudentResponse getById(Long id){
        Student student = studentRepository.findById(id).orElseThrow();
        return studentResponseConverter.convert(student);
    }

    public List<StudentResponse> getAll(){
        List<Student> students = studentRepository.findAll();
        return studentResponseConverter.convertEach(students);
    }

    @Transactional
    public StudentResponse update(StudentDTO studentDTO, Long id){
        Student student = studentRepository.findById(id).orElseThrow();

        student.setFullName(studentDTO.fullName());
        student.setCellPhone(studentDTO.cellPhone());
        student.setAddress(new Address(null, 0, null, null, null, null));

        return studentResponseConverter.convert(student);
    }

    @Transactional
    public void delete(Long id){
        Student student = studentRepository.findById(id).orElseThrow();
        student.setDeleted(true);
        student.setBlocked(true);
    }

    @Transactional
    public StudentResponse block(Long id){
        Student student = studentRepository.findById(id).orElseThrow();
        student.setBlocked(true);

        return studentResponseConverter.convert(student);
    }
}
