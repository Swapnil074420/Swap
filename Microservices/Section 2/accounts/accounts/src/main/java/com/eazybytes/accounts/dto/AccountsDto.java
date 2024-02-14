package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
                name = "Accounts",
                description = "Schema to hold Customer and Account information"
)
public class AccountsDto{

    @NotNull (message = "account number should not be zero")
    @Pattern(regexp = "^$|[0-9]{10}")

    @Schema(
            name = "Customer account Number",
            description = "Schema to hold Customers Account number")
    private Long accountNumber;

    @Schema(
            name = "Customer Account type",
            description = "Schema to hold Customer and Account type")
    @NotNull (message = "account type must inserted")
    private String accountType;


    @Schema(
            name = "Customer",
            description = "Schema to hold Branch Address")
    @NotNull (message = "account number should not be zero")
    private String branchAddress;
}


