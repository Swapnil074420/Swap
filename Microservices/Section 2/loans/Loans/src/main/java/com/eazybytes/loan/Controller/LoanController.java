package com.eazybytes.loan.Controller;


import com.eazybytes.loan.Constants.LoanConstants;
import com.eazybytes.loan.Dto.LoansDto;
import com.eazybytes.loan.Dto.ResponseDto;
import com.eazybytes.loan.services.ILoanService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@AllArgsConstructor
public class LoanController {

    private ILoanService iLoanService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createLoan(@RequestParam String mobileNumber)
    {
            iLoanService.createLoan(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(LoanConstants.MESSAGE_201,LoanConstants.STATUS_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<LoansDto> fetchLoanDetails(@RequestParam String mobileNumber)
    {
        LoansDto loansDto = iLoanService.fetchLoan(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(loansDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateLoanDetails( @RequestBody LoansDto loansDto) {
        boolean isUpdated = iLoanService.updateLoan(loansDto);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(LoanConstants.STATUS_417, LoanConstants.MESSAGE_417_UPDATE));
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteLoanDetails(@RequestParam String MobilNumber)
    {
        boolean isDeleted= iLoanService.deleteLoan(MobilNumber);
        if (isDeleted)
        {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));
        } else {

            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(LoanConstants.STATUS_417, LoanConstants.MESSAGE_417_DELETE));
        }
    }
}
