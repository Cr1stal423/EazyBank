package com.cr1stal423.accounts.service.client;

import com.cr1stal423.accounts.dto.LoansDto;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LoansFallback implements LoansFeignClient{
    @Override
    public ResponseEntity<LoansDto> fetchLoan(String correlationId, String mobileNumber) {
        return null;
    }
}
