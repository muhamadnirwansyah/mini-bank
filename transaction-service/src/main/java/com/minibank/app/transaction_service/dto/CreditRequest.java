package com.minibank.app.transaction_service.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record CreditRequest(UUID accountId, BigDecimal amount) {
}
