package com.group.demo.entity.primary;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.group.demo.dto.transaction.FormCreateTransaction;
import com.group.demo.enumerated.TransactionType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaction")
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "account_no", length = 10, nullable = false)
    private String accountNo;

    @Column(name = "reference", length = 50, nullable = false)
    private String reference;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "trx_type", nullable = false)
    private TransactionType transactionType;

    @Column(name = "receipt_no", length = 20)
    private String receiptNo;

    @Column(name = "transaction_date")
    private LocalDate transactionDate;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public void modelToObject(FormCreateTransaction payload) {
        this.accountNo = payload.getAccountNo();
        this.receiptNo = payload.getReceiptNo();
        this.reference = payload.getReference();
        this.amount = payload.getAmount();
        this.transactionDate = payload.getTransactionDate();
        this.transactionType = payload.getTransactionType();
    }

}
