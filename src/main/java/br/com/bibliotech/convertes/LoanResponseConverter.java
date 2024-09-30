package br.com.bibliotech.convertes;

import br.com.bibliotech.entities.Loan;
import br.com.bibliotech.responses.LoanResponse;

import java.util.List;

public class LoanResponseConverter implements Converter<LoanResponse, Loan> {
    @Override
    public LoanResponse convert(Loan loan) {
        return null;
    }

    @Override
    public List<LoanResponse> convertEach(List<Loan> loans) {
        return null;
    }
}
