package br.com.bibliotech.services;

import br.com.bibliotech.convertes.StudentResponseConverter;
import br.com.bibliotech.dtos.StudentDTO;
import br.com.bibliotech.entities.Address;
import br.com.bibliotech.entities.Student;
import br.com.bibliotech.repositories.StudentRepository;
import br.com.bibliotech.responses.StudentResponse;
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

    @Autowired
    private StudentResponseConverter studentResponseConverter;

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
        student.setAddress(new Address(studentDTO.addressDTO()));

        return studentResponseConverter.convert(student);
    }

    @Transactional
    public void delete(Long id){
        Student student = studentRepository.findById(id).orElseThrow();
        student.setDeleted(true);
    }

    @Transactional
    public StudentResponse block(Long id){
        Student student = studentRepository.findById(id).orElseThrow();
        student.setBlocked(true);

        return studentResponseConverter.convert(student);
    }
}
