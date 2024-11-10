package com.group.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group.demo.dto.currency.CurrencyApiResponse;
import com.group.demo.dto.currency.CurrencyInfo;
import com.group.demo.dto.general.ErrorResponse;
import com.group.demo.dto.general.SuccessResponse;
import com.group.demo.property.CurrencyApiProperty;
import com.group.demo.service.CurrencyApiService;
import com.group.demo.utils.Logger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("api/v1/currency")
@RequiredArgsConstructor
@Tag(name = "Currency Controller")
public class CurrencyController {

    @Autowired
    CurrencyApiProperty apiProperty;

    private final String LOGGER_NAME = "CurrencyController";
    private final CurrencyApiService currencyApiService;

    @GetMapping("")
    @Operation(summary = "Get all currencies")
    public ResponseEntity<?> getAll(
            @RequestHeader HttpHeaders headers,
            HttpServletRequest request,
            HttpServletResponse response) {

        Logger logger = new Logger(LOGGER_NAME);

        try {
            logger.logRequest(request, null);
            CurrencyApiResponse data = currencyApiService.getAllCurrency();
            SuccessResponse<Map<String, CurrencyInfo>> res = new SuccessResponse<>();
            res.setData(data.getData());

            logger.writeLog("Response: " + res.toString());
            return ResponseEntity.ok().body(res);

        } catch (Exception e) {
            e.printStackTrace();
            logger.printStackTrace(e);

            ErrorResponse error = new ErrorResponse();
            error.setMessage("Failed to fetch data from currencyapi.com");
            logger.writeLog("Response: " + error);

            return ResponseEntity.badRequest().body(error);
        } finally {
            logger.flushLog();
        }
    }

}
