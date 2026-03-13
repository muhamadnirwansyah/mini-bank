package com.minibank.app.transaction_service.controller;

import com.minibank.app.transaction_service.command.transaction.TransferCommandHandler;
import com.minibank.app.transaction_service.dto.TransferRequest;
import com.minibank.app.transaction_service.dto.TransferResponse;
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

    @PostMapping
    public ResponseEntity<TransferResponse> transfer(@RequestBody TransferRequest request){
        return ResponseEntity.status(HttpStatus.OK)
                .body(transferCommandHandler.handle(request));
    }
}
