package com.group.demo.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.group.demo.dto.general.ErrorResponse;

@ControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleDataNotFoundError(Exception ex) {

        ErrorResponse errBody = new ErrorResponse();
        errBody.setCode(400);
        errBody.setStatus(false);
        errBody.setMessage("Unable to fetch data. Please try again later.");

        return ResponseEntity.badRequest().body(errBody);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleInvalidArguments(MethodArgumentTypeMismatchException ex) {

        ErrorResponse errBody = new ErrorResponse();
        errBody.setCode(HttpStatus.BAD_REQUEST.value());
        errBody.setStatus(false);
        errBody.setMessage("Invalid type for parameter '" + ex.getParameter().getParameterName() + "'");

        return ResponseEntity.badRequest().body(errBody);
    }

    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleInvalidArguments(MethodArgumentNotValidException ex) {

        ErrorResponse errBody = new ErrorResponse();
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        errBody.setCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
        errBody.setStatus(false);
        errBody.setMessage("Invalid parameters.");
        errBody.setErrors(errors);

        return ResponseEntity.unprocessableEntity().body(errBody);
    }

    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleInvalidArguments(HttpMessageNotReadableException ex) {

        ex.getLocalizedMessage();
        ErrorResponse errBody = new ErrorResponse();
        errBody.setCode(HttpStatus.BAD_REQUEST.value());
        errBody.setStatus(false);
        errBody.setMessage(ex.getLocalizedMessage());

        return ResponseEntity.badRequest().body(errBody);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleInvalidArguments(MissingServletRequestParameterException ex) {

        ErrorResponse errBody = new ErrorResponse();
        errBody.setCode(HttpStatus.BAD_REQUEST.value());
        errBody.setStatus(false);
        errBody.setMessage("Parameter " + ex.getMethodParameter().getParameterName() + " is required.");

        return ResponseEntity.badRequest().body(errBody);
    }

}
