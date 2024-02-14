package com.eazybytes.accounts.contoller;

import com.eazybytes.accounts.constants.AccountsConstants;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ErrorResponseDto;
import com.eazybytes.accounts.dto.ResponseDto;
import com.eazybytes.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Tag(
        name= "crud opertions",
        description= "here we can perform all the opertions such as update delete insert and edit operations"
)

@RestController
@RequestMapping(path="/api",produces={MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountsController {
    private IAccountsService iAccountsService;

    @Operation(
            description = "this microservices is for create a new customer and save data",
            summary = "this API will create a new customer account inside database"
    )
    @ApiResponse
            (
                    responseCode = "Status code 201",
                    description = "HTTPS Status code"
            )
        @Schema(
            implementation = ErrorResponseDto.class
                )

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto)
    {
        iAccountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }


    @GetMapping("/fetch")
public ResponseEntity<CustomerDto> FetchAccountDeatils (@RequestParam @Pattern(regexp ="(^$|[0-9]{10})", message = "must contain 10 digit number")
                                                           String mobileNumber)
{
    CustomerDto customerDto= iAccountsService.fetchAccount(mobileNumber);

    return ResponseEntity.status(HttpStatus.OK).body(customerDto);
}
    @Operation(
            description = "this microservices is for update customer data using customers mobile number",
            summary = "this API will update a present customer account inside database"
    )
    @ApiResponse
            (
                    responseCode = "Status code 200",
                    description = "HTTP 200 OK"
            )
    @ApiResponse
            (
                    responseCode = "Status code 417",
                    description = "HTTPS code 417 means exception failed"
            )
@PutMapping("/update")
public ResponseEntity<ResponseDto> updatedAccountDetails(@Valid @RequestBody CustomerDto customerDto)
{
    boolean isUpdated= iAccountsService.updateAccount(customerDto);
    if(isUpdated)
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
    }
    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
            .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
}
    @Operation(
            description = "This Microservices will delete record permanently from the database",
            summary = "This API will delete customer account inside database"
    )
    @ApiResponse
            (
                    responseCode = "Status code OK",
                    description = "it will  using customers mobile number"
            )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam @Pattern(regexp ="(^$|[0-9]{10})",message = "must contain 10 digit number ")
                                                                String mobileNumber)
    {
        boolean isDeleted=iAccountsService.deleteAccount(mobileNumber);
        if(isDeleted)
        {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));

        }else
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500));
        }

    }

}
