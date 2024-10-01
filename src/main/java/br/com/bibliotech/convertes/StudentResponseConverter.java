package br.com.bibliotech.convertes;

import br.com.bibliotech.entities.Student;
import br.com.bibliotech.responses.AddressResponse;
import br.com.bibliotech.responses.StudentResponse;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class StudentResponseConverter implements Converter<StudentResponse, Student> {


    @Override
    public StudentResponse convert(Student student) {
        AddressResponseConverter addressResponseConverter = new AddressResponseConverter();
        AddressResponse addressResponse = addressResponseConverter.convert(student.getAddress());

        return new StudentResponse(student.getRm(), student.getFullName(), student.getCellPhone(), student.isBlocked(),
                addressResponse);
    }

    @Override
    public List<StudentResponse> convertEach(List<Student> students) {
        return null;
    }
}
