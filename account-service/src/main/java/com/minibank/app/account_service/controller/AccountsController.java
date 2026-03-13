package com.minibank.app.account_service.controller;

import com.minibank.app.account_service.command.account.CreateAccountCommandHandler;
import com.minibank.app.account_service.command.account.CreditAccountCommandHandler;
import com.minibank.app.account_service.command.account.DebitAccountCommandHandler;
import com.minibank.app.account_service.command.account.GetAccountCommandHandler;
import com.minibank.app.account_service.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/accounts")
@RequiredArgsConstructor
public class AccountsController {

    private final CreateAccountCommandHandler createAccountCommandHandler;
    private final DebitAccountCommandHandler debitAccountCommandHandler;
    private final CreditAccountCommandHandler creditAccountCommandHandler;
    private final GetAccountCommandHandler getAccountCommandHandler;

    @Operation(
            summary = "Buat data akun bank baru",
            description = "Endpoint untuk membuat akun bank baru",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Akun berhasil terbuat",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CreateAccountResponse.class),
                                    examples = @ExampleObject(
                                            name = "Sample Response",
                                            value = """
                                                    {
                                                        "id": "af0ade46-06a7-4717-b9c6-ad7f93f13b3e",
                                                        "accountNumber": "283553284763",
                                                        "ownerName": "Muhamad Dicka Nirwansyah",
                                                        "balance": 0,
                                                        "status": "ACTIVE"
                                                    }""")
                            )
                    )
            }
    )
    @PostMapping(value = "/create")
    public ResponseEntity<CreateAccountResponse> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Data payload request untuk membuat akun bank",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Sample Request",
                                    value = """
                                            {
                                                "ownerName" : "Muhamad Dicka Nirwansyah"
                                            }"""
                            )
                    )
            )
            @RequestBody CreateAccountRequest request){
        return ResponseEntity.ok(createAccountCommandHandler.handle(request));
    }

    @Operation(
            summary = "Proses debit",
            description = "Endpoint untuk melakukan proses pendebitan",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Proses Debit berhasil",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = DebitAccountResponse.class),
                                    examples = @ExampleObject(
                                            name = "Sample Response",
                                            value = """
                                                    {
                                                        "accountId": "2610e1be-96a2-46ce-865b-47915edeaad4",
                                                        "accountNumber": "331202840933",
                                                        "ownerName": "Muhamad Dicka Nirwansyah",
                                                        "balance": 1990000.00,
                                                        "updatedAt": "2026-03-12T09:23:31.825603"
                                                    }
                                                    """
                                    )
                            )
                    )
            }
    )
    @PostMapping(value = "/debit")
    public ResponseEntity<DebitAccountResponse> debit(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Data payload request untuk proses debit",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Sample Request",
                                    value = """
                                            {
                                                "accountId" : "2610e1be-96a2-46ce-865b-47915edeaad4",
                                                "amount" : 30000
                                            }
                                            """
                            )
                    )
            )
            @RequestBody DebitAccountRequest request){
        return ResponseEntity.ok(debitAccountCommandHandler.handle(request));
    }

    @Operation(
            summary = "Proses Credit",
            description = "Endpoint untuk melakukan proses pengkreditan",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Proses Credit berhasil",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = DebitAccountResponse.class),
                                    examples = @ExampleObject(
                                            name = "Sample Response",
                                            value = """
                                                    {
                                                        "accountId": "aec22886-4cfd-4d49-8dd9-aa7a522a5d97",
                                                        "accountNumber": "128738417739",
                                                        "ownerName": "Irma Khairunnisa",
                                                        "balance": 3000000.00,
                                                        "updatedAt": "2026-02-28T15:37:29.823694"
                                                    }
                                                    """
                                    )
                            )
                    )
            }
    )
    @PostMapping(value = "/credit")
    public ResponseEntity<CreditAccountResponse> credit(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Data payload request untuk proses credit",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Sample Request",
                                    value = """
                                            {
                                                "accountId" : "aec22886-4cfd-4d49-8dd9-aa7a522a5d97",
                                                "amount" : 1000000
                                            }
                                            """
                            )
                    )
            )
            @RequestBody CreditAccountRequest request){
        return ResponseEntity.ok(creditAccountCommandHandler.handle(request));
    }

    @Operation(
            summary = "Proses Get Account",
            description = "Endpoint untuk melakukan proses inquiry get account by ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Proses Get Akun berhasil",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = DebitAccountResponse.class),
                                    examples = @ExampleObject(
                                            name = "Sample Response",
                                            value = """
                                                    {
                                                        "accountId": "aec22886-4cfd-4d49-8dd9-aa7a522a5d97",
                                                        "accountNumber": "128738417739",
                                                        "ownerName": "Irma Khairunnisa",
                                                        "balance": 3000000.00,
                                                        "updatedAt": "2026-02-28T15:37:29.823694"
                                                    }
                                                    """
                                    )
                            )
                    )
            }
    )
    @PostMapping(value = "/get-account")
    public ResponseEntity<GetAccountResponse> getAccount(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Data payload request untuk proses get akun",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Sample Request",
                                    value = """
                                            {
                                                "accountId" : "2610e1be-96a2-46ce-865b-47915edeaad4"
                                            }
                                            """
                            )
                    )
            )
            @RequestBody GetAccountRequest request){
        return ResponseEntity.ok(getAccountCommandHandler.handle(request));
    }
}
