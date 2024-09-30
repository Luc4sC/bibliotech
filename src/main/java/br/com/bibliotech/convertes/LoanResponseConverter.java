package br.com.bibliotech.convertes;

import br.com.bibliotech.entities.Loan;
import br.com.bibliotech.responses.ItemResponse;
import br.com.bibliotech.responses.LoanResponse;
import br.com.bibliotech.responses.StudentResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class LoanResponseConverter implements Converter<LoanResponse, Loan> {

    @Autowired
    private ItemResponseConverter itemResponseConverter;

    @Autowired
    private StudentResponseConverter studentResponseConverter;

    @Override
    public LoanResponse convert(Loan loan) {
        List<ItemResponse> itemResponses = itemResponseConverter.convertEach(loan.getItems());
        StudentResponse borrower = studentResponseConverter.convert(loan.getBorrower());

        return new LoanResponse(loan.getStartDate(), loan.getEndDate(), loan.getFinishedDate(), loan.isFinished(),
                borrower, itemResponses);
    }

    @Override
    public List<LoanResponse> convertEach(List<Loan> loans) {
        return null;
    }
}
