package com.cr1stal423.accounts.service.client;

import com.cr1stal423.accounts.dto.CardsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFallback implements CardsFeignClient{
    @Override
    public ResponseEntity<CardsDto> fetchCard(String correlationId, String mobileNumber) {
        return null;
    }
}
