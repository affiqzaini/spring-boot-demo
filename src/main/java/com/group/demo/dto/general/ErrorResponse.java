package com.group.demo.dto.general;

import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This is a predefined format for API with single data response.
 * The format is meant to be changed according to the practice used by any
 * development team.
 *
 * Sample API Error Response
 * {
 * "code": 400,
 * "status": false,
 * "message": "Invalid parameters.",
 * "errors" : [
 * key: "This field is required."
 * ]
 * }
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private int code = HttpStatus.BAD_REQUEST.value();
    private boolean status = false;
    private String message;
    private Map<String, String> errors;

}
