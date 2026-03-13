package com.minibank.app.transaction_service.command;

public interface CommandHandler<C,R> {
    R handle(C command);
}
