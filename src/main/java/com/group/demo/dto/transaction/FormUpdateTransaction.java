package com.group.demo.dto.transaction;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormUpdateTransaction {

    @NotEmpty(message = "Reference is required.")
    @Length(max = 50, min = 3, message = "Reference must be between 3 and 50 characters.")
    private String reference;

    @NotEmpty(message = "Receipt number is required.")
    @Length(max = 20, min = 3, message = "Receipt number must be between 3 and 20 characters.")
    private String receiptNo;

    @Min(value = 10, message = "Minimum amount is RM0.10")
    @Max(value = 100000000, message = "Minimum amount is RM999,999")
    private int amount;

    @PastOrPresent(message = "Future dates are not acceptable.")
    private LocalDate transactionDate = LocalDate.now();

}
