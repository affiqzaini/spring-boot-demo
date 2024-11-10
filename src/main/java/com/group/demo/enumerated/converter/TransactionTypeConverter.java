package com.group.demo.enumerated.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.group.demo.enumerated.TransactionType;

@Component
public class TransactionTypeConverter implements Converter<String, TransactionType> {

    @Override
    public TransactionType convert(String value) {
        return TransactionType.valueOf(value);
    }

}
