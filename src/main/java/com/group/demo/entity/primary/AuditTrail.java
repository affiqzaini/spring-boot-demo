package com.group.demo.entity.primary;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "audit_trail")
public class AuditTrail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "account_no", nullable = false)
    private String accountNo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "transaction_id", referencedColumnName = "id")
    private Transaction transaction;

    @Column(name = "curr_opening_bal")
    private Integer currentOpeningBal;

    @Column(name = "new_opening_bal")
    private Integer newOpeningBal;

    @Column(name = "curr_debit_amt")
    private Integer currentDebitAmt;

    @Column(name = "new_debit_amt")
    private Integer newDebitAmt;

    @Column(name = "curr_credit_amt")
    private Integer currentCreditAmt;

    @Column(name = "new_credit_amt")
    private Integer newCreditAmt;

    @Column(name = "curr_closing_bal")
    private Integer currentClosingBal;

    @Column(name = "new_closing_bal")
    private Integer newClosingBal;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public void prepareAuditTrail(AccountBalance payload, Transaction trx) {
        this.accountNo = payload.getId().getAccountNo();
        this.transaction = trx;
        this.currentOpeningBal = payload.getOpeningBalance();
        this.newOpeningBal = payload.getOpeningBalance();
        this.currentClosingBal = payload.getClosingBalance();
        this.newClosingBal = payload.getClosingBalance();
        this.currentDebitAmt = payload.getDebitAmt();
        this.newDebitAmt = payload.getDebitAmt();
        this.currentCreditAmt = payload.getCreditAmt();
        this.currentCreditAmt = payload.getCreditAmt();
    }

}
