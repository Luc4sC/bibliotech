package br.com.bibliotech.presentation.converter;

import br.com.bibliotech.domain.model.Loan;
import br.com.bibliotech.presentation.response.LoanResponse;
import br.com.bibliotech.utils.UrlUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LoanConverter {

    public LoanResponse fromModel(Loan loan) {
        String loanRequestUrl = UrlUtils.getLoanRequestUrl(loan.getLoanRequest());
        return new LoanResponse(loan.getStartDate(), loan.getEndDate(), loan.getFinishedDate(), loanRequestUrl);
    }

    public List<LoanResponse> fromModelList(List<Loan> loans) {
        List<LoanResponse> loanResponses = new ArrayList<>();
        loans.forEach(loan -> loanResponses.add(fromModel(loan)));

        return loanResponses;
    }

}
