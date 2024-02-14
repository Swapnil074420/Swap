package com.eazybytes.accounts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer information")
public class CustomerDto {

    @NotNull (message = "customer name must be between 1 and 50") @Size(min=1, max=50)
    @Schema(
            name = "Customer Name",
            description = "Schema to hold Customer Name",
            example = "Swapnil Pawar")

    private String name;

    @NotNull  (message = "email address should not be blank")
    @Email (message = "please insert the valid email address")
    @Schema(
            name = "Customer Email",
            description = "Schema to hold Customer email address",
            example = "swapnilpr05@gmail.com")
    private  String email;

    @NotNull  (message = "mobile number should not be zero")
    @Pattern(regexp ="(^$|[0-9]{10})", message = "must contain 10 digit number")
    @Schema(
            name = "Customer mobile number",
            description = "Schema to hold Customer Mobile Number",
            example = "07442009659")
    private String mobileNumber;

    private AccountsDto accountsDto;
}
