package com.ssafy.lovesol.domain.bank.repository;

import com.ssafy.lovesol.domain.bank.entity.Account;
import com.ssafy.lovesol.domain.bank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    Optional<Transaction> findFirstByAccountAndDepositAmountOrderByTransactionAtDesc(Account account,int depositAmount);
}
