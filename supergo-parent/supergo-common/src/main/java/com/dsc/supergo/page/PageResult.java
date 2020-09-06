package com.dsc.supergo.page;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果实体类
 * @author 60221
 *
 */
@Getter
@Setter
public class PageResult implements Serializable {
    private Long total;
    private int page=1;
    private List<?> rows;
    private int pageSize=10;

    public <T> PageResult(long total, List<T> list, Integer page) {
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
