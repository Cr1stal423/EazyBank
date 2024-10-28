package com.cr1stal423.accounts.functions;

import com.cr1stal423.accounts.service.IAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@Slf4j
public class AccountsFunctions {
    @Bean
    public Consumer<Long> updateCommunications(IAccountService accountService) {
        return accountNumber -> {
            log.info("Updating communications for account number : " + accountNumber);
            accountService.updateCommunicationStatus(accountNumber);
        };
    }
}
