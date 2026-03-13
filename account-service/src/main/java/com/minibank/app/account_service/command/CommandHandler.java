package com.minibank.app.account_service.command;

public interface CommandHandler<C,R> {
    R handle(C command);
}
