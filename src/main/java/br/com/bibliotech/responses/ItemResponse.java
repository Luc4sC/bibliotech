package br.com.bibliotech.responses;

public record ItemResponse(LoanResponse loan, CopyResponse copy) {
}
