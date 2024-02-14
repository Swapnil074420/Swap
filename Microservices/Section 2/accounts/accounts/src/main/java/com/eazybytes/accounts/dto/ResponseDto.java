package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
@Schema
        (

                        name = "Response",
                        description = "Schema for successfully information "
        )
public class ResponseDto {

    @Schema(
            name = "Status Code",
            description = "Schema to hold status code if any error occur it will show an error")
    private String statusCode;

    @Schema(
            name = "Status Message",
            description = "Schema to hold a status message whether status completed or is there any error")
    private String statusMsg;
}
