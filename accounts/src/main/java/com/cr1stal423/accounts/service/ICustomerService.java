package com.cr1stal423.accounts.service;

import com.cr1stal423.accounts.dto.CustomerDetailsDto;
import com.cr1stal423.accounts.dto.CustomerDto;

public interface ICustomerService {
    /**
     * Fetches the customer details for a given mobile number.
     *
     * @param mobileNumber The mobile number of the customer.
     * @return The customer details.
     */
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber,String correlationId);
}
