package com.cr1stal423.accounts.service.impl;

import com.cr1stal423.accounts.dto.AccountsDto;
import com.cr1stal423.accounts.dto.CardsDto;
import com.cr1stal423.accounts.dto.CustomerDetailsDto;
import com.cr1stal423.accounts.dto.LoansDto;
import com.cr1stal423.accounts.entity.Accounts;
import com.cr1stal423.accounts.entity.Customer;
import com.cr1stal423.accounts.exception.ResourceNotFoundException;
import com.cr1stal423.accounts.mapper.AccountsMapper;
import com.cr1stal423.accounts.mapper.CustomerMapper;
import com.cr1stal423.accounts.repository.AccountsRepository;
import com.cr1stal423.accounts.repository.CustomerRepository;
import com.cr1stal423.accounts.service.ICustomerService;
import com.cr1stal423.accounts.service.client.CardsFeignClient;
import com.cr1stal423.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {
    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "CustomerID", customer.getCustomerId().toString())
        );
        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoan(correlationId, mobileNumber);
        if(null != loansDtoResponseEntity) {
            customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());
        }

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCard(correlationId, mobileNumber);
        if(null != cardsDtoResponseEntity) {
            customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());
        }

        return customerDetailsDto;
    }
}
