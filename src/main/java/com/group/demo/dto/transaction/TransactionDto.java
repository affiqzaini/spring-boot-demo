package com.group.demo.dto.transaction;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

import com.group.demo.dto.general.DtoConverter;
import com.group.demo.entity.primary.Transaction;
import com.group.demo.enumerated.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TransactionDto extends DtoConverter<Transaction> {

    private Long id;
    private String accountNo;
    private String reference;
    private int amount;
    private String formattedAmount;
    private TransactionType transactionType;
    private String receiptNo;
    private String transactionDate;

    @Override
    public void convert(Transaction data) {

        DecimalFormat decf = new DecimalFormat("#,##0.00;-#,##0.00");
        DateTimeFormatter datef = DateTimeFormatter.ofPattern("dd/MM/YYYY");

        this.id = data.getId();
        this.accountNo = data.getAccountNo();
        this.receiptNo = data.getReceiptNo();
        this.reference = data.getReference();
        this.amount = data.getAmount();
        this.formattedAmount = decf.format(data.getAmount() / 100);
        this.transactionType = data.getTransactionType();
        this.transactionDate = datef.format(data.getTransactionDate());

    }

}
