package com.minibank.app.account_service.command.account;

import com.minibank.app.account_service.command.CommandHandler;
import com.minibank.app.account_service.dto.CreateAccountRequest;
import com.minibank.app.account_service.dto.CreateAccountResponse;
import com.minibank.app.account_service.entity.Accounts;
import com.minibank.app.account_service.entity.Status;
import com.minibank.app.account_service.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@Transactional
@RequiredArgsConstructor
public class CreateAccountCommandHandler implements
        CommandHandler<CreateAccountRequest, CreateAccountResponse> {

    private final AccountRepository accountRepository;
    private final AccountNumberGenerator accountNumberGenerator;

    @Override
    public CreateAccountResponse handle(CreateAccountRequest command) {
        Accounts accounts = new Accounts();
        accounts.setAccountNumber(accountNumberGenerator.generateAccountNumber());
        accounts.setOwnerName(command.ownerName());
        accounts.setBalance(BigDecimal.ZERO);
        accounts.setStatus(Status.ACTIVE);
        accounts.setCreatedAt(LocalDateTime.now());
        Accounts response = accountRepository.save(accounts);
        return new CreateAccountResponse(accounts.getId(),
                response.getAccountNumber(),
                response.getOwnerName(),
                response.getBalance(),
                response.getStatus());
    }
}
