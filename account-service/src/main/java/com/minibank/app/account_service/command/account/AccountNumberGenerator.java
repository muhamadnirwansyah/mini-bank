package com.minibank.app.account_service.command.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class AccountNumberGenerator {

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    public String generateAccountNumber() {
        long number = 100000000000L + (long)(SECURE_RANDOM.nextDouble() * 900000000000L);
        return String.valueOf(number);
    }
}
