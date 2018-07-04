package com.gxjtkyy.standardcloud.common.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 分页对象模型
 * @Package com.gxjtkyy.domain
 * @Author lizhenhua
 * @Date 2018/5/29 15:36
 */
@Setter
@Getter
@ToString
public class Page<T> implements Serializable{

    /**总记录数*/
    private int count;

    /**页面大小*/
    private int pageSize =10;

    /**当前页*/
    private int currentPage =1;

    /**总页数*/
    private int totalPage;

    /**结果集*/
    private List<T> dataList;

    public int getTotalPage() {
        if(pageSize > 0){
            int totalPages = count / pageSize;
            return totalPages == 0?1:totalPages;
        }else {
            return 1;
        }
    }
}
