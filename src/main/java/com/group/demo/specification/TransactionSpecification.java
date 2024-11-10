package com.group.demo.specification;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import com.group.demo.entity.primary.Transaction;
import com.group.demo.enumerated.TransactionType;

public class TransactionSpecification {

    public static Specification<Transaction> findById(Long id) {
        return (root, query, cb) -> {
            if (id != null) {
                return cb.equal(root.get("id"), id);
            } else {
                return cb.conjunction();
            }
        };
    }

    public static Specification<Transaction> findByAccountNo(String accountNo) {
        return (root, query, cb) -> {
            if (accountNo != null && !accountNo.isEmpty()) {
                return cb.equal(root.get("accountNo"), accountNo);
            } else {
                return cb.conjunction();
            }
        };
    }

    public static Specification<Transaction> findByTransactionType(TransactionType val) {
        return (root, query, cb) -> {
            if (val != null) {
                return cb.equal(root.get("transactionType"), val);
            } else {
                return cb.conjunction();
            }
        };
    }

    public static Specification<Transaction> findByReceiptNo(String val) {
        return (root, query, cb) -> {
            if (val != null && !val.isEmpty()) {
                return cb.equal(root.get("receiptNo"), val);
            } else {
                return cb.conjunction();
            }
        };
    }

    public static Specification<Transaction> findAfter(LocalDate dateFrom) {
        return (root, query, cb) -> {
            if (dateFrom != null) {
                return cb.greaterThanOrEqualTo(root.get("transactionDate"), dateFrom);
            } else {
                return cb.conjunction();
            }
        };
    }

    public static Specification<Transaction> findBefore(LocalDate dateTo) {
        return (root, query, cb) -> {
            if (dateTo != null) {
                return cb.lessThanOrEqualTo(root.get("transactionDate"), dateTo);
            } else {
                return cb.conjunction();
            }
        };
    }

}
