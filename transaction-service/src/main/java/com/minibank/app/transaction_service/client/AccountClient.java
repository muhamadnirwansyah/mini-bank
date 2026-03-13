package com.minibank.app.transaction_service.client;

import com.minibank.app.transaction_service.dto.AccountResponse;
import com.minibank.app.transaction_service.dto.CreditRequest;
import com.minibank.app.transaction_service.dto.DebitRequest;
import com.minibank.app.transaction_service.dto.GetAccountRequest;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.UUID;


@Component
public class AccountClient {

    private final WebClient webClient;

    public AccountClient(WebClient.Builder builder){
        this.webClient = builder.baseUrl("http://account-service")
                .build();
    }

    public AccountResponse credit(@NonNull UUID accountId, BigDecimal amount) {
        return webClient.post()
                .uri("/accounts/credit")
                .bodyValue(new CreditRequest(accountId, amount))
                .retrieve()
                .bodyToMono(AccountResponse.class)
                .log()
                .block();
    }

    public AccountResponse debit(@NonNull UUID accountId, BigDecimal amount){
        return webClient.post()
                .uri("/accounts/debit")
                .bodyValue(new DebitRequest(accountId, amount))
                .retrieve()
                .bodyToMono(AccountResponse.class)
                .log()
                .block();
    }

    public AccountResponse getAccountById(UUID accountId) {
        return webClient.post()
                .uri("/accounts/get-account")
                .bodyValue(new GetAccountRequest(accountId))
                .retrieve()
                .bodyToMono(AccountResponse.class)
                .log()
                .block();
    }
}
