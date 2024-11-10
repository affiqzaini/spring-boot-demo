package com.group.demo.entity.primary;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.group.demo.compositeKey.AccountBalanceCK;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account_balance")
@Entity
public class AccountBalance {

    @EmbeddedId
    private AccountBalanceCK id;

    @Column(name = "opening_bal")
    private Integer openingBalance = 0;

    @Column(name = "closing_bal")
    private Integer closingBalance = 0;

    @Column(name = "debit_amt")
    private Integer debitAmt = 0;

    @Column(name = "credit_amt")
    private Integer creditAmt = 0;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
