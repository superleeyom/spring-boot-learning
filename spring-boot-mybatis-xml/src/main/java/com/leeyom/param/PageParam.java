package com.leeyom.param;

/**
 * 分页参数
 * @author leeyom
 * @date 2018年01月25日 下午9:09
 */
public class PageParam {

    /**
     * 每页的数量
     */
    private Integer pageSize = 10;
    /**
     * 当前页
     */
    private Integer pageNumber;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public PageParam(Integer pageSize, Integer pageNumber) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
    }

    @Override
    public String toString() {
        return "PageParam{" +
                "pageSize=" + pageSize +
                ", pageNumber=" + pageNumber +
                '}';
    }
}
