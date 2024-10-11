package br.com.bibliotech.application.converter;

import br.com.bibliotech.domain.model.Loan;
import br.com.bibliotech.presentation.response.ItemResponse;
import br.com.bibliotech.presentation.response.LoanResponse;
import br.com.bibliotech.presentation.response.StudentResponse;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class LoanResponseConverter implements Converter<LoanResponse, Loan> {

    @Override
    public LoanResponse convert(Loan loan) {
        ItemResponseConverter itemResponseConverter = new ItemResponseConverter();
        List<ItemResponse> itemResponses = itemResponseConverter.convertEach(loan.getItems());

        StudentResponseConverter studentResponseConverter = new StudentResponseConverter();
        StudentResponse borrower = studentResponseConverter.convert(loan.getBorrower());

        return new LoanResponse(loan.getStartDate(), loan.getEndDate(), loan.getFinishedDate(), loan.isFinished(),
                borrower, itemResponses);
    }

    @Override
    public List<LoanResponse> convertEach(List<Loan> loans) {
        return null;
    }
}
