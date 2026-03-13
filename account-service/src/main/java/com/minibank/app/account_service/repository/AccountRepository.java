package com.minibank.app.account_service.repository;

import com.minibank.app.account_service.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Accounts, UUID> {
}
