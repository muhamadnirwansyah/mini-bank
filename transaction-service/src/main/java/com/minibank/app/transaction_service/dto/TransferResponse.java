package com.minibank.app.transaction_service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransferResponse(
        UUID fromAccountId,
        UUID toAccountId,
        String fromOwnerName,
        String toOwnerName,
        BigDecimal amount,
        LocalDateTime completedAt) {
}
