package com.group.demo.repository.primary;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.group.demo.entity.primary.Transaction;
import java.util.Optional;
import java.util.List;

public interface TransactionRepository
        extends PagingAndSortingRepository<Transaction, Long>,
        JpaSpecificationExecutor<Transaction> {

    Transaction save(Transaction trx);

    Optional<Transaction> findById(Long id);

    List<Transaction> findByAccountNo(String accountNo);

}
