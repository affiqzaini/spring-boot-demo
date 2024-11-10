package com.group.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group.demo.dto.general.ErrorResponse;
import com.group.demo.dto.general.PagedData;
import com.group.demo.dto.general.SuccessResponse;
import com.group.demo.dto.transaction.FormCreateTransaction;
import com.group.demo.dto.transaction.FormUpdateTransaction;
import com.group.demo.dto.transaction.TransactionDto;
import com.group.demo.entity.primary.Transaction;
import com.group.demo.enumerated.TransactionType;
import com.group.demo.service.TransactionService;
import com.group.demo.utils.ApiExceptionHandler;
import com.group.demo.utils.Logger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("/api/v1/transaction")
@RequiredArgsConstructor
@Tag(name = "Transaction Controller")
public class TransactionController {

    private final String LOGGER_NAME = "TransactionController";
    private final TransactionService transactionService;
    private final ApiExceptionHandler apiExceptionHandler;

    @GetMapping("")
    @Operation(summary = "Get All Transactions")
    public ResponseEntity<?> getAll(
            @RequestHeader HttpHeaders headers,
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(required = true) String accountNo,
            @RequestParam(required = false) String receiptNo,
            @RequestParam(required = false) TransactionType transactionType,
            @RequestParam(required = false) LocalDate dateFrom,
            @RequestParam(required = false) LocalDate dateTo,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int itemsPerPage) {

        Logger logger = new Logger(LOGGER_NAME);
        try {
            logger.logRequest(request, null);
            PagedData<Transaction> pagedData = transactionService.getAll(accountNo, receiptNo, transactionType,
                    dateFrom, dateTo, page, itemsPerPage);

            logger.writeLog("Response: " + pagedData);
            return ResponseEntity.ok().body(pagedData);

        } catch (Exception e) {
            e.printStackTrace();
            logger.printStackTrace(e);

            ResponseEntity<?> err = apiExceptionHandler.handleDataNotFoundError(e);
            logger.writeLog("Response: " + err);

            return err;
        } finally {
            logger.flushLog();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get one transaction by ID")
    public ResponseEntity<?> getOneById(
            @RequestHeader HttpHeaders headers,
            HttpServletRequest request,
            HttpServletResponse response,
            @Valid @PathVariable(required = true) Long id) {

        Logger logger = new Logger(LOGGER_NAME);

        try {
            logger.logRequest(request, null);
            TransactionDto data = transactionService.getOneById(id);
            SuccessResponse<TransactionDto> res = new SuccessResponse<TransactionDto>(data);

            logger.writeLog("Response: " + res);
            return ResponseEntity.ok().body(res);

        } catch (Exception e) {
            e.printStackTrace();
            logger.printStackTrace(e);

            ResponseEntity<?> err = apiExceptionHandler.handleDataNotFoundError(e);
            logger.writeLog("Response: " + err);

            return err;
        } finally {
            logger.flushLog();
        }
    }

    @PostMapping("")
    @Operation(summary = "Create new transaction")
    public ResponseEntity<?> create(
            @RequestHeader HttpHeaders headers,
            HttpServletRequest request,
            HttpServletResponse response,
            @Valid @RequestBody(required = true) FormCreateTransaction payload) {

        Logger logger = new Logger(LOGGER_NAME);
        try {
            logger.logRequest(request, payload.toString());

            TransactionDto data = transactionService.createNewTransaction(payload, logger);
            SuccessResponse<TransactionDto> res = new SuccessResponse<TransactionDto>(data);
            res.setStatusMessage("Created successfully.");
            res.setStatusCode(HttpStatus.CREATED.value());

            logger.writeLog("RESPONSE: " + res.toString());

            return ResponseEntity.created(null).body(res);

        } catch (Exception e) {
            e.printStackTrace();
            logger.printStackTrace(e);

            ErrorResponse error = new ErrorResponse();
            error.setMessage("Failed to create transaction. Please try again later.");
            return ResponseEntity.badRequest().body(error);
        } finally {
            logger.flushLog();
        }
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update transaction by ID")
    public ResponseEntity<?> update(
            @RequestHeader HttpHeaders headers,
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable(required = true) Long id,
            @Valid @RequestBody(required = true) FormUpdateTransaction payload) {

        Logger logger = new Logger(LOGGER_NAME);
        try {
            logger.logRequest(request, payload.toString());

            TransactionDto data = transactionService.update(id, payload, logger);

            SuccessResponse<TransactionDto> res = new SuccessResponse<TransactionDto>(data);
            res.setStatusMessage("Updated successfully.");

            logger.writeLog("RESPONSE: " + res.toString());

            return ResponseEntity.ok().body(res);

        } catch (Exception e) {
            e.printStackTrace();
            logger.printStackTrace(e);

            ErrorResponse error = new ErrorResponse();
            error.setMessage("Failed to create transaction. Please try again later.");
            return ResponseEntity.badRequest().body(error);
        } finally {
            logger.flushLog();
        }
    }

}
