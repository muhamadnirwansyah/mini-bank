package com.minibank.app.transaction_service.command.transaction;

import com.minibank.app.transaction_service.client.AccountClient;
import com.minibank.app.transaction_service.command.CommandHandler;
import com.minibank.app.transaction_service.dto.AccountResponse;
import com.minibank.app.transaction_service.dto.TransferRequest;
import com.minibank.app.transaction_service.dto.TransferResponse;
import com.minibank.app.transaction_service.entity.Transaction;
import com.minibank.app.transaction_service.entity.TransactionStatus;
import com.minibank.app.transaction_service.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferCommandHandler implements CommandHandler<TransferRequest, TransferResponse> {

    private final TransactionRepository transactionRepository;
    private final AccountClient accountClient;

    @Override
    public TransferResponse handle(TransferRequest command) {
        log.info("===== Process Transfer =====");
        log.info("From Account : {} ",command.fromAccount());
        log.info("To Account : {}",command.toAccount());
        log.info("Amount : {}",command.amount());

        Transaction transaction = new Transaction();
        transaction.setFromAccountId(command.fromAccount().toString());
        transaction.setToAccountId(command.toAccount().toString());
        transaction.setAmount(command.amount());
        transaction.setTransactionStatus(TransactionStatus.PENDING);
        transaction.setCreatedAt(LocalDateTime.now());
        Transaction initSaveTransaction = transactionRepository.save(transaction);

        try{
            AccountResponse fromAccountResponse = accountClient.getAccountById(command.fromAccount());
            log.info("Check From Account Response : [{} | {} | {}]",
                    fromAccountResponse.accountId(),
                    fromAccountResponse.accountNumber(),
                    fromAccountResponse.ownerName());
            AccountResponse toAccountResponse = accountClient.getAccountById(command.toAccount());
            log.info("Check To Account Response : [{} | {} | {}]",
                    toAccountResponse.accountId(),
                    toAccountResponse.accountNumber(),
                    toAccountResponse.ownerName());

            AccountResponse processDebit = accountClient.debit(command.fromAccount(), command.amount());
            log.info("Process Debit : [{} | {} | {} ]", processDebit.accountId(), processDebit.accountNumber(), processDebit.ownerName());
            AccountResponse processCredit = accountClient.credit(command.toAccount(), command.amount());
            log.info("Process Credit : [{} | {} | {} ]",processCredit.accountId(), processCredit.accountNumber(), processCredit.ownerName());
            initSaveTransaction.setTransactionStatus(TransactionStatus.COMPLETED);
            initSaveTransaction.setCompletedAt(LocalDateTime.now());
            transactionRepository.save(initSaveTransaction);
            return new TransferResponse(
                    command.fromAccount(),
                    command.toAccount(),
                    fromAccountResponse.ownerName(),
                    toAccountResponse.ownerName(),
                    command.amount(),
                    initSaveTransaction.getCompletedAt());
        }catch (Exception e){
            log.error("Failed to save / transfer ");
            initSaveTransaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(initSaveTransaction);
        }
        return new TransferResponse(command.fromAccount(), command.toAccount(),"", "", command.amount(), null);
    }
}
