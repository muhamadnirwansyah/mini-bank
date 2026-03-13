package com.minibank.app.account_service.command.account;

import com.minibank.app.account_service.command.CommandHandler;
import com.minibank.app.account_service.dto.DebitAccountRequest;
import com.minibank.app.account_service.dto.DebitAccountResponse;
import com.minibank.app.account_service.entity.Accounts;
import com.minibank.app.account_service.entity.Status;
import com.minibank.app.account_service.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class DebitAccountCommandHandler implements
        CommandHandler<DebitAccountRequest, DebitAccountResponse> {

    private final AccountRepository accountRepository;

    @Override
    public DebitAccountResponse handle(DebitAccountRequest command) {
        Accounts accounts = accountRepository.findById(command.accountId())
                .orElseThrow(() -> new RuntimeException("Account ID "+command.accountId()+" Not Found"));

        if (accounts.getStatus() != Status.ACTIVE){
            throw new RuntimeException("Account Status "+accounts.getStatus()+" is not Active");
        }

        if (accounts.getBalance().compareTo(command.amount()) < 0){
            throw new RuntimeException("Insufficient Balance");
        }

        accounts.setBalance(accounts.getBalance().subtract(command.amount()));
        accounts.setUpdatedAt(LocalDateTime.now());
        Accounts saved = accountRepository.save(accounts);
        return new DebitAccountResponse(saved.getId(),
                saved.getAccountNumber(),
                saved.getOwnerName(),
                saved.getBalance(),
                saved.getUpdatedAt()
                );
    }
}
