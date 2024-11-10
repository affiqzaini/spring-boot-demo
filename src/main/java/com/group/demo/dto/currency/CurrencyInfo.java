package com.group.demo.dto.currency;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyInfo {

    private String symbol;
    private String name;

    @JsonProperty("symbol_native")
    private String symbolNative;

    @JsonProperty("decimal_digits")
    private int decimalDigits;

    private int rounding;
    private String code;

    @JsonProperty("name_plural")
    private String namePlural;

    private String type;
    private List<String> countries;

}
