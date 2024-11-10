package com.group.demo.repository.primary;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.group.demo.compositeKey.AccountBalanceCK;
import com.group.demo.entity.primary.AccountBalance;
import java.util.Optional;

public interface AccountBalanceRepository extends PagingAndSortingRepository<AccountBalance, AccountBalanceCK> {

    Optional<AccountBalance> findById(AccountBalanceCK id);

    AccountBalance save(AccountBalance accountBalance);

}
