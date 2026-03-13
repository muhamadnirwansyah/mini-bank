package com.minibank.app.transaction_service.controller;

import com.minibank.app.transaction_service.command.transaction.TransferCommandHandler;
import com.minibank.app.transaction_service.dto.TransferRequest;
import com.minibank.app.transaction_service.dto.TransferResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/transfer")
@RequiredArgsConstructor
public class TransferController {

    private final TransferCommandHandler transferCommandHandler;
    @Operation(
            summary = "Transfer Uang",
            description = "Endpoint untuk transfer uang",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Transfer berhasil",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TransferResponse.class),
                                    examples = @ExampleObject(
                                            name = "Sample Response",
                                            value = """
                                                    {
                                                        "fromAccountId": "aec22886-4cfd-4d49-8dd9-aa7a522a5d97",
                                                        "toAccountId": "2610e1be-96a2-46ce-865b-47915edeaad4",
                                                        "fromOwnerName": "Irma Khairunnisa",
                                                        "toOwnerName": "Muhamad Dicka Nirwansyah",
                                                        "amount": 50000,
                                                        "completedAt": "2026-03-14T00:46:40.654489"
                                                    }""")
                            )
                    )
            }
    )
    @PostMapping
    public ResponseEntity<TransferResponse> transfer(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Data payload request untuk transfer akun bank",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Sample Request",
                                    value = """
                                            {
                                                "fromAccount" : "aec22886-4cfd-4d49-8dd9-aa7a522a5d97",
                                                "toAccount" : "2610e1be-96a2-46ce-865b-47915edeaad4",
                                                "amount" : 50000
                                            }"""
                            )
                    ))
            @RequestBody TransferRequest request){
        return ResponseEntity.status(HttpStatus.OK)
                .body(transferCommandHandler.handle(request));
    }
}
