package com.tuan.exercise.projman.pojo;

import com.tuan.exercise.projman.config.Constant;

public class Pagination {
    private int limit = Constant.pagingLimit;
    private long total;
    private int offset;
    private int size;

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
}
