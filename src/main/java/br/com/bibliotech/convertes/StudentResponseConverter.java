package br.com.bibliotech.convertes;

import br.com.bibliotech.entities.Student;
import br.com.bibliotech.responses.AddressResponse;
import br.com.bibliotech.responses.LoanResponse;
import br.com.bibliotech.responses.StudentResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StudentResponseConverter implements Converter<StudentResponse, Student> {

    @Autowired
    private AddressResponseConverter addressResponseConverter;

    @Autowired
    private LoanResponseConverter loanResponseConverter;

    @Override
    public StudentResponse convert(Student student) {
        AddressResponse addressResponse = addressResponseConverter.convert(student.getAddress());
        List<LoanResponse> loanResponses = loanResponseConverter.convertEach(student.getLoans());

        return new StudentResponse(student.getRm(), student.getFullName(), student.getCellPhone(), student.isBlocked(),
                addressResponse, loanResponses);
    }

    @Override
    public List<StudentResponse> convertEach(List<Student> students) {
        return null;
    }
}
