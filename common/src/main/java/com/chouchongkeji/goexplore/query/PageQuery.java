package com.chouchongkeji.goexplore.query;

/**
 * @author yichenshanren
 * @date 2017/12/8
 */

public class PageQuery {

    private Integer pageNum = 1;
    private Integer pageSize = 14;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize == null || pageSize < 1) {
            pageSize = 14;
        }
        if (pageSize > 20) {
            pageSize = 14;
        }
        this.pageSize = pageSize;
    }

}
