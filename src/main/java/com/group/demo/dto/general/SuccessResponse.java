package com.group.demo.dto.general;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This is apredefined format for API with single data response.
 * The format is meant to be changed according to the practice used by any
 * development team.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuccessResponse<T> {

    private int statusCode = 200;
    private boolean status = true;
    private String statusMessage = "Data retrieved successfully.";
    private T data;

    public SuccessResponse(T data) {
        this.data = data;

        if (data == null) {
            this.statusMessage = "No data found!";
        }
    }

}
