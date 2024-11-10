package com.group.demo.compositeKey;

import java.io.Serializable;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountBalanceCK implements Serializable {

    @Column(name = "account_no", length = 10)
    private String accountNo;

    @Column(name = "year", length = 4)
    private Integer year;

}
