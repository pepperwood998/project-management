package com.tuan.exercise.projman.pojo;

import org.springframework.http.HttpStatus;

public class ApiError extends CommonResponse {

    public ApiError(HttpStatus status, String message) {
        this.setStatusCode(status.value());
        this.setStatusPhrase(status.getReasonPhrase());
        this.setMessage(message);
    }
}
