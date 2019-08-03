package com.tuan.exercise.projman.pojo;

import java.util.List;

import org.springframework.http.HttpStatus;

public class JsonPageableData<E> extends JsonCommonResponse {

    private Pagination pagination;
    private List<E> data;
    
    public JsonPageableData() {
        this.setStatusCode(HttpStatus.OK.value());
        this.setStatusPhrase(HttpStatus.OK.getReasonPhrase());
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public List<E> getData() {
        return data;
    }

    public void setData(List<E> data) {
        this.data = data;
    }
}
