package com.personal.ecommercesys.dao;

import com.personal.ecommercesys.model.Userorder;

import java.util.List;

/**
 * @Entity com.personal.ecommercesys.model.Userorder
 */
public interface UserorderDao {
    /**
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * @mbg.generated
     */
    int insert(Userorder record);

    /**
     * @mbg.generated
     */
    int insertSelective(Userorder record);

    /**
     * @mbg.generated
     */
    Userorder selectByPrimaryKey(Integer id);

    /**
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Userorder record);

    /**
     * @mbg.generated
     */
    int updateByPrimaryKey(Userorder record);

    public int insertDoNotAddId(Userorder userorder);//Id为自动增长，不插入Id的插入语句
    public List<Userorder> selectByBuyer(String buyer);//按买家 查找订单表
}