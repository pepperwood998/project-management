package com.tuan.exercise.projman.pojo;

import org.springframework.http.HttpStatus;

public class JsonDataWrapper extends CommonResponse {

    private Object data;
    
    public JsonDataWrapper() {
        this.setStatusCode(HttpStatus.OK.value());
        this.setStatusPhrase(HttpStatus.OK.getReasonPhrase());
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}