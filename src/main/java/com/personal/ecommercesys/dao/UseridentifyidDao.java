package com.personal.ecommercesys.dao;

import com.personal.ecommercesys.model.Useridentifyid;

/**
 * @Entity com.personal.ecommercesys.model.Useridentifyid
 */
public interface UseridentifyidDao {
    /**
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     * @mbg.generated
     */
    int insert(Useridentifyid record);

    /**
     * @mbg.generated
     */
    int insertSelective(Useridentifyid record);

    /**
     * @mbg.generated
     */
    Useridentifyid selectByPrimaryKey(String id);

    /**
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Useridentifyid record);

    /**
     * @mbg.generated
     */
    int updateByPrimaryKey(Useridentifyid record);

    int insertRecord(Useridentifyid record);
}