package br.com.bibliotech.presentation.converter;

import br.com.bibliotech.domain.model.LoanRequest;
import br.com.bibliotech.presentation.response.LoanRequestResponse;
import br.com.bibliotech.utils.UrlUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LoanRequestConverter {

    public LoanRequestResponse fromModel(LoanRequest loanRequest) {
        String userUrl = UrlUtils.getUserUrl(loanRequest.getUser());
        String booksUrl = UrlUtils.getBooksByLoanRequestUrl(loanRequest);

        return new LoanRequestResponse(userUrl, booksUrl, loanRequest.getRequestDate(), loanRequest.getStatus().getName());
    }

    public List<LoanRequestResponse> fromModelList(List<LoanRequest> loanRequests) {
        List<LoanRequestResponse> loanRequestResponses = new ArrayList<>();

        loanRequests.forEach(request -> loanRequestResponses.add(fromModel(request)));
        return loanRequestResponses;
    }

}
