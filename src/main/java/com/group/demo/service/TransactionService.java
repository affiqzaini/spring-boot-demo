package com.group.demo.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.group.demo.compositeKey.AccountBalanceCK;
import com.group.demo.dto.general.PagedData;
import com.group.demo.dto.transaction.FormCreateTransaction;
import com.group.demo.dto.transaction.FormUpdateTransaction;
import com.group.demo.dto.transaction.TransactionDto;
import com.group.demo.entity.primary.AccountBalance;
import com.group.demo.entity.primary.AuditTrail;
import com.group.demo.entity.primary.Transaction;
import com.group.demo.enumerated.TransactionType;
import com.group.demo.repository.primary.AccountBalanceRepository;
import com.group.demo.repository.primary.AuditTrailRepository;
import com.group.demo.repository.primary.TransactionRepository;
import com.group.demo.specification.TransactionSpecification;
import com.group.demo.utils.DataPatcher;
import com.group.demo.utils.Logger;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountBalanceRepository accountBalanceRepository;
    private final AuditTrailRepository auditTrailRepository;

    @Transactional(readOnly = true)
    public PagedData<Transaction> getAll(String accountNo, String receiptNo, TransactionType type, LocalDate dateFrom,
            LocalDate dateTo, int page, int itemsPerPage) throws Exception {

        Sort sort = Sort.by("id").ascending();
        Pageable pageable = PageRequest.of(page - 1, itemsPerPage, sort);

        Page<Transaction> data = transactionRepository.findAll(
                TransactionSpecification.findByAccountNo(accountNo)
                        .and(TransactionSpecification.findByReceiptNo(receiptNo))
                        .and(TransactionSpecification.findAfter(dateFrom))
                        .and(TransactionSpecification.findBefore(dateTo))
                        .and(TransactionSpecification.findByTransactionType(type)),
                pageable);

        PagedData<Transaction> pagedData = new PagedData<>(data, TransactionDto.class);

        return pagedData;

    }

    @Transactional(readOnly = true)
    public TransactionDto getOneById(Long id) throws Exception {
        Optional<Transaction> trx = transactionRepository.findOne(TransactionSpecification.findById(id));
        TransactionDto dto = null;
        if (trx.isPresent()) {
            dto = new TransactionDto();
            dto.convert(trx.get());
        }

        return dto;

    }

    @Transactional(rollbackFor = { SQLException.class, Exception.class })
    public TransactionDto createNewTransaction(FormCreateTransaction payload, Logger logger) throws Exception {

        Transaction trx = new Transaction();
        trx.modelToObject(payload);
        trx = transactionRepository.save(trx);

        logger.writeLog("Transaction inserted successfully.");
        logger.writeLog("Transaction: " + trx.toString());

        AccountBalanceCK accountKey = new AccountBalanceCK(payload.getAccountNo(), LocalDate.now().getYear());
        Optional<AccountBalance> optAccount = accountBalanceRepository.findById(accountKey);

        if (optAccount.isEmpty()) {
            return null;
        }

        // Update account balance
        AccountBalance account = optAccount.get();
        AuditTrail auditTrail = new AuditTrail();
        auditTrail.prepareAuditTrail(account, trx);

        if (payload.getTransactionType().name().equalsIgnoreCase(TransactionType.DEBIT.name())) {

            int newDebitAmt = account.getDebitAmt() + payload.getAmount();
            auditTrail.setNewDebitAmt(newDebitAmt);
            account.setDebitAmt(newDebitAmt);

        } else {
            int newCreditAmt = account.getCreditAmt() + payload.getAmount();
            auditTrail.setNewCreditAmt(newCreditAmt);
            account.setCreditAmt(newCreditAmt);
        }

        // uncomment to test transactional
        // boolean ok = true;
        // if (ok) {
        // throw new Exception("Error here. Rollback transaction");
        // }

        account = accountBalanceRepository.save(account);
        logger.writeLog("Account Balance updated successfully.");
        logger.writeLog("Account Balance: " + account.toString());

        auditTrail = auditTrailRepository.save(auditTrail);
        logger.writeLog("Audit Trail inserted successfully.");
        logger.writeLog("Audit Trail: " + auditTrail.toString());

        TransactionDto dto = new TransactionDto();
        dto.convert(trx);

        return dto;

    }

    @Transactional(rollbackFor = { SQLException.class, Exception.class })
    public TransactionDto update(Long id, FormUpdateTransaction payload, Logger logger) throws Exception {

        Optional<Transaction> optTrx = transactionRepository.findById(id);
        if (optTrx.isEmpty()) {
            throw new Exception("Invalid transaction ID");
        }

        Transaction trx = optTrx.get();
        int originalAmt = trx.getAmount();
        DataPatcher<Transaction, FormUpdateTransaction> patcher = new DataPatcher<>();
        patcher.patch(trx, payload);
        trx = transactionRepository.save(trx);

        logger.writeLog("Transaction inserted successfully.");
        logger.writeLog("Transaction: " + trx.toString());

        if (payload.getAmount() != originalAmt) {
            AccountBalanceCK accountKey = new AccountBalanceCK(trx.getAccountNo(), LocalDate.now().getYear());
            Optional<AccountBalance> optAccount = accountBalanceRepository.findById(accountKey);

            if (optAccount.isEmpty()) {
                throw new Exception("Failed to get account data. Please try again later.");

            }

            // Update account balance
            AccountBalance account = optAccount.get();
            AuditTrail auditTrail = new AuditTrail();
            auditTrail.prepareAuditTrail(account, trx);

            int diff = (payload.getAmount() - originalAmt);
            if (trx.getTransactionType().name().equalsIgnoreCase(TransactionType.DEBIT.name())) {

                int newDebitAmt = account.getDebitAmt() + diff;
                auditTrail.setNewDebitAmt(newDebitAmt);
                account.setDebitAmt(newDebitAmt);

            } else {
                int newCreditAmt = account.getCreditAmt() + diff;
                auditTrail.setNewCreditAmt(newCreditAmt);
                account.setCreditAmt(newCreditAmt);
            }
            // uncomment to test transactional
            // boolean ok = true;
            // if (ok) {
            // throw new Exception("Error here. Rollback transaction");
            // }

            account = accountBalanceRepository.save(account);
            logger.writeLog("Account Balance updated successfully.");
            logger.writeLog("Account Balance: " + account.toString());

            auditTrail = auditTrailRepository.save(auditTrail);
            logger.writeLog("Audit Trail inserted successfully.");
            logger.writeLog("Audit Trail: " + auditTrail.toString());
        }

        TransactionDto dto = new TransactionDto();
        dto.convert(trx);

        return dto;

    }

    @Transactional(rollbackFor = { SQLException.class, Exception.class })
    public void delete(Long id, Logger logger) {

    }
}
