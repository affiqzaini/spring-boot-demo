package com.group.demo.utils;

import java.time.LocalDate;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import com.group.demo.dto.transaction.FormCreateTransaction;
import com.group.demo.entity.primary.Transaction;
import com.group.demo.enumerated.TransactionType;
import com.group.demo.repository.primary.TransactionRepository;
import com.group.demo.service.TransactionService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    @Qualifier("primary")
    @Autowired
    private DataSource primaryDs;

    private final TransactionRepository transactionRepository;
    private final TransactionService transactionService;

    @EventListener(ApplicationReadyEvent.class)
    public void loadData() {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator(false, false, "UTF-8",
                new ClassPathResource("seed.sql"));
        resourceDatabasePopulator.execute(primaryDs);

        try {
            insertTransactions();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertTransactions() throws Exception {

        Logger logger = new Logger("SeedData");
        List<Transaction> list = transactionRepository.findByAccountNo("2024215993");
        if (list.size() > 0) {
            return;
        }

        // +200
        FormCreateTransaction form = new FormCreateTransaction();
        form.setAccountNo("2024215993");
        form.setAmount(20000);
        form.setReceiptNo("TRF0000456");
        form.setReference("Transfer from John Doe");
        form.setTransactionType(TransactionType.DEBIT);
        form.setTransactionDate(LocalDate.now().minusDays(9));
        transactionService.createNewTransaction(form, logger);

        // +2350
        form = new FormCreateTransaction();
        form.setAccountNo("2024215993");
        form.setAmount(235000);
        form.setReceiptNo("REFUND-000123");
        form.setReference("House Reno Deposit Refund");
        form.setTransactionType(TransactionType.DEBIT);
        form.setTransactionDate(LocalDate.now().minusDays(9));
        transactionService.createNewTransaction(form, logger);

        // -145.90
        form = new FormCreateTransaction();
        form.setAccountNo("2024215993");
        form.setAmount(14590);
        form.setReceiptNo("TNB-12345");
        form.setReference("TNB Nov 2024");
        form.setTransactionType(TransactionType.CREDIT);
        form.setTransactionDate(LocalDate.now().minusDays(9));
        transactionService.createNewTransaction(form, logger);

        // -14.67
        form = new FormCreateTransaction();
        form.setAccountNo("2024215993");
        form.setAmount(1467);
        form.setReceiptNo("SYABAS-1234");
        form.setReference("Syabas Bill Nov 2024");
        form.setTransactionType(TransactionType.CREDIT);
        form.setTransactionDate(LocalDate.now().minusDays(8));
        transactionService.createNewTransaction(form, logger);

        // -139.80
        form = new FormCreateTransaction();
        form.setAccountNo("2024215993");
        form.setAmount(13980);
        form.setReceiptNo("UMOBILE-1234");
        form.setReference("UMOBILE Bill Nov 2024");
        form.setTransactionType(TransactionType.CREDIT);
        form.setTransactionDate(LocalDate.now().minusDays(8));
        transactionService.createNewTransaction(form, logger);

        // +500
        form = new FormCreateTransaction();
        form.setAccountNo("2024215993");
        form.setAmount(50000);
        form.setReceiptNo(null);
        form.setReference("Monthly Savings");
        form.setTransactionType(TransactionType.DEBIT);
        form.setTransactionDate(LocalDate.now().minusDays(5));
        transactionService.createNewTransaction(form, logger);

        // +980
        form = new FormCreateTransaction();
        form.setAccountNo("2024215993");
        form.setAmount(98000);
        form.setReceiptNo("SB-12345");
        form.setReference("Withdraw from ASB");
        form.setTransactionType(TransactionType.DEBIT);
        form.setTransactionDate(LocalDate.now().minusDays(5));
        transactionService.createNewTransaction(form, logger);

        // +980 - 8
        form = new FormCreateTransaction();
        form.setAccountNo("2024215993");
        form.setAmount(98000);
        form.setReceiptNo("SB-12345");
        form.setReference("Withdraw from ASB");
        form.setTransactionType(TransactionType.DEBIT);
        form.setTransactionDate(LocalDate.now().minusDays(5));
        transactionService.createNewTransaction(form, logger);

        // -736
        form = new FormCreateTransaction();
        form.setAccountNo("2024215993");
        form.setAmount(73600);
        form.setReceiptNo("MBB-12345");
        form.setReference("Care HP Payment Nov 2024");
        form.setTransactionType(TransactionType.CREDIT);
        form.setTransactionDate(LocalDate.now().minusDays(4));
        transactionService.createNewTransaction(form, logger);

        // -169.70
        form = new FormCreateTransaction();
        form.setAccountNo("2024215993");
        form.setAmount(16970);
        form.setReceiptNo("RCT-00981");
        form.setReference("Eat out - TGIF");
        form.setTransactionType(TransactionType.CREDIT);
        form.setTransactionDate(LocalDate.now().minusDays(4));
        transactionService.createNewTransaction(form, logger);

        // -10
        form = new FormCreateTransaction();
        form.setAccountNo("2024215993");
        form.setAmount(98000);
        form.setReceiptNo(null);
        form.setReference("KLCC Parking");
        form.setTransactionType(TransactionType.CREDIT);
        form.setTransactionDate(LocalDate.now().minusDays(4));
        transactionService.createNewTransaction(form, logger);

        // -100
        form = new FormCreateTransaction();
        form.setAccountNo("2024215993");
        form.setAmount(10000);
        form.setReceiptNo("TNG12345");
        form.setReference("TnG Reload RM100");
        form.setTransactionType(TransactionType.CREDIT);
        form.setTransactionDate(LocalDate.now().minusDays(4));
        transactionService.createNewTransaction(form, logger);

    }

}
