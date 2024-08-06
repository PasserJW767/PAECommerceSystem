package com.personal.ecommercesys.dao;

import com.personal.ecommercesys.model.Userinfo;
import com.personal.ecommercesys.model.Userwaiting;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.personal.ecommercesys.model.Userwaiting
 */
public interface UserwaitingDao {
    /**
     * @mbg.generated
     */
    int deleteByPrimaryKey(String username);

    /**
     * @mbg.generated
     */
    int insert(Userwaiting record);

    /**
     * @mbg.generated
     */
    int insertSelective(Userwaiting record);

    /**
     * @mbg.generated
     */
    Userwaiting selectByPrimaryKey(String username);

    /**
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Userwaiting record);

    /**
     * @mbg.generated
     */
    int updateByPrimaryKey(Userwaiting record);
    void addUserinfo(Userinfo userinfo);
    List<Userwaiting> selectAll(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    //按用户名like 查询后分页
    List<Userwaiting> selectByName(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize,@Param("searchName") String searchName);
}