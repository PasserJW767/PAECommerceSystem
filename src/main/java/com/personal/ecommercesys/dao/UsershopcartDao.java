package com.personal.ecommercesys.dao;

import com.personal.ecommercesys.model.Usershopcart;

import java.util.List;

/**
 * @Entity com.personal.ecommercesys.model.Usershopcart
 */
public interface UsershopcartDao {
    /**
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * @mbg.generated
     */
    int insert(Usershopcart record);

    /**
     * @mbg.generated
     */
    int insertSelective(Usershopcart record);

    /**
     * @mbg.generated
     */
    Usershopcart selectByPrimaryKey(Integer id);

    /**
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Usershopcart record);

    /**
     * @mbg.generated
     */
    int updateByPrimaryKey(Usershopcart record);

    //Id为自动增长，不插入Id的插入语句
    public int insertDoNotAddId(Usershopcart usershopcart);
    //按买家 查找购物车表
    public List<Usershopcart> selectByBuyer(String buyer);

}