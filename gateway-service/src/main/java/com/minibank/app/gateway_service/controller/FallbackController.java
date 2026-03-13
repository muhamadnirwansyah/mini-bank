package com.minibank.app.gateway_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class FallbackController {

    @RequestMapping(value = "/fallback/transaction")
    public ResponseEntity<Map<String,Object>> transactionFallback(){
        Map<String,Object> mapData = new HashMap<>();
        mapData.put("status", HttpStatus.SERVICE_UNAVAILABLE);
        mapData.put("message", "Transaction Service currently unavailable");
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(mapData);
    }
}
