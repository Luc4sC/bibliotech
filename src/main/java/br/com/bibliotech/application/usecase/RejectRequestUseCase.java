package br.com.bibliotech.application.usecase;

import br.com.bibliotech.application.exception.BadRequestException;
import br.com.bibliotech.domain.exception.CannotBeRejectedException;
import br.com.bibliotech.domain.model.LoanRequest;
import br.com.bibliotech.domain.service.LoanRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RejectRequestUseCase {

    private final LoanRequestService loanRequestService;

    @Autowired
    public RejectRequestUseCase(LoanRequestService loanRequestService) {
        this.loanRequestService = loanRequestService;
    }

    public void reject(Long requestId) {
        LoanRequest loanRequest = loanRequestService.findById(requestId);
        rejectLoanRequest(loanRequest);
    }

    private void rejectLoanRequest(LoanRequest loanRequest) {
        try {
            loanRequest.reject();
            loanRequestService.update(loanRequest);
            log.info("Loan request: " + loanRequest + " rejected!");
        } catch (CannotBeRejectedException cannotBeRejectedException) {
            log.info(cannotBeRejectedException.getMessage());
            throw new BadRequestException(cannotBeRejectedException.getMessage());
        }
    }

}
