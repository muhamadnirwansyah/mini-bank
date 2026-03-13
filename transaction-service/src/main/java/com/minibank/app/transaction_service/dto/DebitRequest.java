package com.minibank.app.transaction_service.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record DebitRequest(UUID accountId, BigDecimal amount) {
}
