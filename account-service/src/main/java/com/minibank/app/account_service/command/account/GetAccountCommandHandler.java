package com.minibank.app.account_service.command.account;

import com.minibank.app.account_service.command.CommandHandler;
import com.minibank.app.account_service.dto.GetAccountRequest;
import com.minibank.app.account_service.dto.GetAccountResponse;
import com.minibank.app.account_service.entity.Accounts;
import com.minibank.app.account_service.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAccountCommandHandler implements
        CommandHandler<GetAccountRequest, GetAccountResponse> {

    private final AccountRepository accountRepository;

    @Override
    public GetAccountResponse handle(GetAccountRequest command) {
        Accounts accounts = accountRepository.findById(command.accountId())
                .orElseThrow(() -> new RuntimeException("Account ID "+command.accountId()+" not found"));
        return new GetAccountResponse(accounts.getId(), accounts
                .getAccountNumber(), accounts.getOwnerName(), accounts.getBalance(), accounts.getStatus());
    }
}
