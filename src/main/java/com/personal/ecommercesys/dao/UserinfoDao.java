package com.personal.ecommercesys.dao;

import com.personal.ecommercesys.model.Userinfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.personal.ecommercesys.model.Userinfo
 */
public interface UserinfoDao {
    /**
     * @mbg.generated
     */
    int deleteByPrimaryKey(String username);

    /**
     * @mbg.generated
     */
    int insert(Userinfo user);

    /**
     * @mbg.generated
     */
    int insertSelective(Userinfo record);

    /**
     * @mbg.generated
     */
    Userinfo selectByPrimaryKey(@Param("searchName") String searchName);

    /**
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Userinfo record);

    /**
     * @mbg.generated
     */
    int updateByPrimaryKey(Userinfo record);


    Userinfo selectForLogin(Userinfo user);

    void addUserinfo(Userinfo userinfo);
    String judgeName(String name);

    //分页显示
    List<Userinfo> selectAll(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    //查询后分页
    List<Userinfo> selectByName(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize,@Param("searchName") String searchName);

    //为了编辑的查询
    Userinfo selectByNameForEdit(String editName);
    //按照用户名来查找该用户
    Userinfo selectByUsername(String userName);
}