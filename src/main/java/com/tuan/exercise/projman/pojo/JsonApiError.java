package com.tuan.exercise.projman.pojo;

import org.springframework.http.HttpStatus;

public class JsonApiError extends JsonCommonResponse {

    public JsonApiError(HttpStatus status, String message) {
        this.setStatus(status.value());
        this.setPhrase(status.getReasonPhrase());
        this.setMessage(message);
    }
}
