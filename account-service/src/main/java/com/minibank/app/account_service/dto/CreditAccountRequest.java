package com.minibank.app.account_service.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record CreditAccountRequest(UUID accountId, BigDecimal amount) {
}
