package com.tuan.exercise.projman.pojo;

import org.springframework.http.HttpStatus;

public class JsonDataResponse extends JsonCommonResponse {

    private Pagination pagination;
    private Object data;

    public JsonDataResponse() {
        this.setStatus(HttpStatus.OK.value());
        this.setPhrase(HttpStatus.OK.getReasonPhrase());
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
