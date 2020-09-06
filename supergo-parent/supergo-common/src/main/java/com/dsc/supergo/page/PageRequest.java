package com.dsc.supergo.page;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 分页请求实体类
 * @author 60221
 */
@Setter
@Getter
public class PageRequest implements Serializable {
    private Integer currentPage=1;
    private Integer pageSize=10;
}
