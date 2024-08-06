package com.personal.ecommercesys.service;


import com.personal.ecommercesys.model.Userinfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*
* 业务（服务）类，如果涉及到数据库的事务就要在Service类上加事务注解
* 只要是非查询的方法（insert、update、delete）操作就要在方法上加事务注解@Transactional
* */
public interface IUserinfoService {
    /*
    * 登录
    * */
    public Userinfo selectForLogin(Userinfo user);

    /*
    *添加用户信息
    * */
    public void addUserinfo(Userinfo userinfo);

    /*
    * 按名字查找
    * */
    public String judgeName(String name);

    /*
    * 分页*/
    List<Userinfo> selectAll(int pageNum, int pageSize);

    /*
    * 按名字查询后分页*/
    List<Userinfo> selectByName(int pageNum, int pageSize,String searchName);

    /*
     * 按名字删除*/
    int deleteByPrimaryKey(String username);

    /*
     * 按名字更新*/
    int updateByPrimaryKey(Userinfo record);

    /*
     * //为了编辑的查询*/
    Userinfo selectByNameForEdit(String editName);
    Userinfo selectByPrimaryKey(String searchName);

    //按照名字查找用户的所有信息
    Userinfo selectByUsername(String userName);
}
