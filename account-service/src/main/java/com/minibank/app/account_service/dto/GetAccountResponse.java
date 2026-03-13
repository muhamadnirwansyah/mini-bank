package com.minibank.app.account_service.dto;

import com.minibank.app.account_service.entity.Status;

import java.math.BigDecimal;
import java.util.UUID;

public record GetAccountResponse (UUID accountId, String accountNumber, String ownerName, BigDecimal balance, Status status){
}
