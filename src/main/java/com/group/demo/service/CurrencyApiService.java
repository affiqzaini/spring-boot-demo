package com.group.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.group.demo.dto.currency.CurrencyApiResponse;
import com.group.demo.property.CurrencyApiProperty;
import com.group.demo.utils.Helper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyApiService {

    @Autowired
    CurrencyApiProperty currencyApiProperty;

    private final Helper helper;

    public CurrencyApiResponse getAllCurrency() throws RestClientException, Exception {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<CurrencyApiResponse> entity = new HttpEntity<CurrencyApiResponse>(headers);

        String baseUrl = currencyApiProperty.getBaseUrl() + "/currencies";
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("apikey", currencyApiProperty.getApiKey());

        String url = helper.prepareUrl(baseUrl, null, queryParams);

        ResponseEntity<CurrencyApiResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity,
                CurrencyApiResponse.class);

        return response.getBody();

    }

}
