package com.gxjtkyy.standardcloud.common.dao;

import com.gxjtkyy.standardcloud.common.domain.dto.CondictionDTO;
import com.gxjtkyy.standardcloud.common.domain.dto.DictDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 字典DAO
 * @Package com.gxjtkyy.standardcloud.common.dao
 * @Author lizhenhua
 * @Date 2018/6/27 11:19
 */
@Mapper
public interface DictComMapper {


    /**
     * 获取所有字典信息
     * @return
     */
    List<DictDTO> getList(DictDTO dto);

    /**
     * 根据字典名称查询字典码
     * @param dictName
     * @return
     */
    List<DictDTO> getByName(@Param("dictName") String dictName);

    /**
     * 根据code查询字典描述
     * @param dto 字典码
     * @return
     */
    List<DictDTO> getListByPage(CondictionDTO dto);


    /**
     * 统计数据
     * @param dto
     * @return
     */
    int getTotalCount(CondictionDTO dto);


    /**
     * 插入字典数据
     * @param dto
     */
    boolean insert(DictDTO dto);


    /**
     * 更新字典值
     * @param dto
     * @return
     */
    boolean update(DictDTO dto);
}
