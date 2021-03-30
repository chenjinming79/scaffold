package com.company.project.common;


public class PageParam extends BaseParam{
    private Integer page=1;

    // 分页参数
    private Integer limit = 10 ;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
