package br.com.bibliotech.presentation.converter;

import br.com.bibliotech.domain.model.Loan;
import br.com.bibliotech.presentation.responses.LoanResponse;
import br.com.bibliotech.presentation.responses.RequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LoanConverter {

    private final RequestConverter requestConverter;

    @Autowired
    public LoanConverter(RequestConverter requestConverter) {
        this.requestConverter = requestConverter;
    }

    public LoanResponse fromModel(Loan loan) {
        RequestResponse requestResponse = requestConverter.fromModel(loan.getRequest());
        return new LoanResponse(loan.getStartDate(), loan.getEndDate(), loan.getFinishedDate(), requestResponse);
    }

    public List<LoanResponse> fromModelList(List<Loan> loans) {
        List<LoanResponse> loanResponses = new ArrayList<>();
        loans.forEach(loan -> loanResponses.add(fromModel(loan)));

        return loanResponses;
    }

}
