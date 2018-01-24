package com.leeyom.param;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class PageParam {
    /**
     * 起始行
     */
    private int beginLine;
    /**
     * 每页数量
     */
    private Integer pageSize = 3;
    /**
     * 当前页
     */
    private Integer currentPage = 0;

    public int getBeginLine() {
        return pageSize * currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
