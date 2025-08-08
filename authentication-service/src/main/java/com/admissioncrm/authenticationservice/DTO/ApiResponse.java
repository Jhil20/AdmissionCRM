package com.admissioncrm.authenticationservice.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private boolean success;
    private String message;
    private T data;
    private String error;
    private String timestamp;
    private Integer code;

    public ApiResponse() {}

    public ApiResponse(boolean success, String message, T data, String error, String timestamp, Integer code) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.error = error;
        this.timestamp = timestamp;
        this.code = code;
    }

    // Static methods for creating responses
    public static <T> ApiResponse<T> success(String message, T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage(message);
        response.setData(data);
        response.setTimestamp(LocalDateTime.now().toString());
        response.setCode(200);
        return response;
    }

    public static <T> ApiResponse<T> success(String message) {
        return success(message, null);
    }

    public static <T> ApiResponse<T> error(String message, String error) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setSuccess(false);
        response.setMessage(message);
        response.setError(error);
        response.setTimestamp(LocalDateTime.now().toString());
        response.setCode(500);
        return response;
    }

    public static <T> ApiResponse<T> error(String message, String error, Integer code) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setSuccess(false);
        response.setMessage(message);
        response.setError(error);
        response.setTimestamp(LocalDateTime.now().toString());
        response.setCode(code);
        return response;
    }

    public static <T> ApiResponse<T> warning(String message, T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage(message);
        response.setData(data);
        response.setTimestamp(LocalDateTime.now().toString());
        response.setCode(206);
        return response;
    }

    // Getters and setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
