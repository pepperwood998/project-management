package com.tuan.exercise.projman.pojo;

import com.tuan.exercise.projman.config.Constant;

public class Pagination {

    private int limit = Constant.getPagingLimit();
    private long total;
    private int page;
    private int size;
    private int offset;

    public Pagination(long total, int page, int size) {
        this.setTotal(total);
        this.setPage(page);
        this.setSize(size);
        this.setOffset(page * size);
    }

    public int getLimit() {
        return limit;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
