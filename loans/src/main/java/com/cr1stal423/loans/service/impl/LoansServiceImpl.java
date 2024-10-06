package com.cr1stal423.loans.service.impl;

import com.cr1stal423.loans.constats.LoansConstants;
import com.cr1stal423.loans.dto.LoansDto;
import com.cr1stal423.loans.entity.Loans;
import com.cr1stal423.loans.exception.LoansAlreadyExistsException;
import com.cr1stal423.loans.exception.ResourceNotFoundException;
import com.cr1stal423.loans.mapper.LoansMapper;
import com.cr1stal423.loans.repository.LoansRepository;
import com.cr1stal423.loans.service.ILoansService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService {
    private final LoansRepository loansRepository;
    /**
     * @param mobileNumber - Mobile Number of the Customer
     */
    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans> optionalLoans = loansRepository.findByMobileNumber(mobileNumber);
        if (optionalLoans.isPresent()){
            throw new LoansAlreadyExistsException("Loan already registered with given mobileNumber " + mobileNumber);
        }
        loansRepository.save(createNewLoan(mobileNumber));
    }
    /**
     * @param mobileNumber - Mobile Number of the Customer
     * @return the new loan details
     */
    private Loans createNewLoan(String mobileNumber) {
        Loans loans = new Loans();
        long randomNumber = 100000000000L + new Random().nextInt(900000000);
        loans.setLoanNumber(Long.toString(randomNumber));
        loans.setMobileNumber(mobileNumber);
        loans.setLoanType(LoansConstants.HOME_LOAN);
        loans.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        loans.setAmountPaid(0);
        loans.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return loans;
    }
    /**
     *
     * @param mobileNumber - Input mobile Number
     * @return Loan Details based on a given mobileNumber
     */
    @Override
    public LoansDto fetchLoan(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loans","mobileNumber",mobileNumber)
        );
        return LoansMapper.mapToLoansDto(loans,new LoansDto());
    }

    /**
     *
     * @param loansDto - LoansDto Object
     * @return boolean indicating if the update of loan details is successful or not
     */
    @Override
    public boolean updateLoan(LoansDto loansDto) {
        boolean isUpdated = true;
        Loans loans = loansRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loans","loansNumber", loansDto.getLoanNumber())
        );
        LoansMapper.mapToLoans(loansDto,loans);
        loansRepository.save(loans);
        return isUpdated;
    }
    /**
     * @param mobileNumber - Input MobileNumber
     * @return boolean indicating if the delete of loan details is successful or not
     */
    @Override
    public boolean deleteLoan(String mobileNumber) {
        boolean isDeleted = true;
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loans","mobileNumber", mobileNumber)
        );
        loansRepository.delete(loans);
        return isDeleted;
    }
}
