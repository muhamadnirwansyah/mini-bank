package com.minibank.app.account_service.command.account;

import com.minibank.app.account_service.command.CommandHandler;
import com.minibank.app.account_service.dto.CreditAccountRequest;
import com.minibank.app.account_service.dto.CreditAccountResponse;
import com.minibank.app.account_service.entity.Accounts;
import com.minibank.app.account_service.entity.Status;
import com.minibank.app.account_service.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class CreditAccountCommandHandler implements
        CommandHandler<CreditAccountRequest, CreditAccountResponse> {

    private final AccountRepository accountRepository;

    @Override
    public CreditAccountResponse handle(CreditAccountRequest command) {
        Accounts accounts = accountRepository.findById(command.accountId())
                .orElseThrow(() -> new RuntimeException("Account ID "+command.accountId()+" not found"));

        if (!accounts.getStatus().equals(Status.ACTIVE)){
            throw new RuntimeException("Account status "+accounts.getAccountNumber()+" is not active");
        }
        accounts.setBalance(accounts.getBalance().add(command.amount()));
        accounts.setUpdatedAt(LocalDateTime.now());
        Accounts saved = accountRepository.save(accounts);
        return new CreditAccountResponse(saved.getId(),saved.getAccountNumber(),
                saved.getOwnerName(),saved.getBalance(), saved.getUpdatedAt());
    }
}
