package com.eazybytes.loan.Exception;

public class LoanAlreadyExistsException extends RuntimeException{

    public LoanAlreadyExistsException(String message)
    {
        super(message);
    }
}
