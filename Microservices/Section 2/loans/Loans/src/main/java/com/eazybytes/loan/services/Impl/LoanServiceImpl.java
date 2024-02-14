package com.eazybytes.loan.services.Impl;
import com.eazybytes.accounts.exception.ResourceNotFoundException;
import com.eazybytes.loan.Constants.LoanConstants;
import com.eazybytes.loan.Dto.LoansDto;
import com.eazybytes.loan.Mapper.LoansMapper;
import com.eazybytes.loan.Exception.LoanAlreadyExistsException;
import com.eazybytes.loan.Repository.LoanRepository;
import com.eazybytes.loan.entity.Loans;
import com.eazybytes.loan.services.ILoanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements ILoanService {
  private LoanRepository loanRepository;
    @Override
    public void createLoan(String mobileNumber) {
       Optional<Loans> optionalLoans =loanRepository.findByMobileNumber(mobileNumber);
       if (optionalLoans.isPresent())
       {
           throw new LoanAlreadyExistsException("Loan Number Already exist in database with given mobile number"+mobileNumber);
       }
            loanRepository.save(createNewLoan(mobileNumber));
    }
    private Loans createNewLoan(String mobileNumber) {
        Loans loan= new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        loan.setLoanNumber(Long.toString(randomLoanNumber));
        loan.setMobileNumber(mobileNumber);
        loan.setLoanType(LoanConstants.HOME_LOAN);
        loan.setTotalLoan(LoanConstants.NEW_LOAN_LIMIT);
        loan.setAmountPaid(0);
        loan.setOutstandingAmount(LoanConstants.NEW_LOAN_LIMIT);
        return loan;
    }
    @Override
    public LoansDto fetchLoan(String mobileNumber) {
        Loans loans = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );
        return LoansMapper.mapToLoansDto(loans, new LoansDto());
    }

    @Override
    public boolean updateLoan(LoansDto loansDto)
    {
        Loans loans=loanRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(
                ()->new ResourceNotFoundException("Loan","Loan number", loansDto.getLoanNumber())
        );
                 LoansMapper.mapToLoans(loansDto, loans);
                loanRepository.save(loans);
                 return  true;

    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loans loans = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );
        loanRepository.deleteById(loans.getLoanId());
        return true;
    }
}

