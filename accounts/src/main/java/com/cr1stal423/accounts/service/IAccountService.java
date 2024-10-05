package com.cr1stal423.accounts.service;

import com.cr1stal423.accounts.dto.CustomerDto;

public interface IAccountService {
    /**
     * Creates a new account for the given customer.
     *
     * @param customerDto The customer details to create the account for.
     */
    void createAccount(CustomerDto customerDto);

    /**
     * Fetches the account details for a customer based on the provided mobile number.
     *
     * @param mobileNumber The mobile number of the customer.
     * @return The customer's account details.
     */
    CustomerDto fetchAccount(String mobileNumber);

    /**
     * Updates an existing account based on the provided customer details.
     *
     * @param customerDto The customer details to update the account with.
     * @return True if the account was updated successfully, false otherwise.
     */
    boolean updateAccount(CustomerDto customerDto);

    /**
     * Deletes an account based on the provided mobile number.
     *
     * @param mobileNumber the mobile number of the account to be deleted
     * @return true if the account is deleted successfully, false otherwise
     */
    boolean deleteAccount(String mobileNumber);
}
