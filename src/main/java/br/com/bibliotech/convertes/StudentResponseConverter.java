package br.com.bibliotech.convertes;

import br.com.bibliotech.entities.Student;
import br.com.bibliotech.responses.StudentResponse;

import java.util.List;

public class StudentResponseConverter implements Converter<StudentResponse, Student> {
    @Override
    public StudentResponse convert(Student student) {
        return null;
    }

    @Override
    public List<StudentResponse> convertEach(List<Student> students) {
        return null;
    }
}
