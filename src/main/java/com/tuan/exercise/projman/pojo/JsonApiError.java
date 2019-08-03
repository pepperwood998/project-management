package com.tuan.exercise.projman.pojo;

import org.springframework.http.HttpStatus;

public class JsonApiError extends JsonCommonResponse {

    public JsonApiError(HttpStatus status, String message) {
        this.setStatusCode(status.value());
        this.setStatusPhrase(status.getReasonPhrase());
        this.setMessage(message);
    }
}
