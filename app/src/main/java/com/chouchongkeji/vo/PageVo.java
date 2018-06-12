package com.chouchongkeji.vo;

/**
 * @author linqin
 * @date 2018/6/12
 */
public class PageVo {
    private Integer pageNo=1;
    private Integer pageSize=14;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        if (pageNo ==null){
            pageNo=1;
        }
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize == null){
            pageSize=14;
        }
        this.pageSize = pageSize;
    }
}
