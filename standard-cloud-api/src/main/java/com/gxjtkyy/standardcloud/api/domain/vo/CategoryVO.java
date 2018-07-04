package com.gxjtkyy.standardcloud.api.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 分类
 * @Package com.gxjtkyy.domain.vo
 * @Author lizhenhua
 * @Date 2018/6/19 18:44
 */
@Data
public class CategoryVO implements Serializable{

    /**一级分类*/
    private Set<String> topCategories = new HashSet<>();

    /**二级分类*/
    private Set<String> subCategories = new HashSet<>();

    /**三级分类*/
    private Set<String> thirdCategories = new HashSet<>();

}
