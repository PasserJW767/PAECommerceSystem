package com.personal.ecommercesys.service;

import com.personal.ecommercesys.model.Userinfo;
import com.personal.ecommercesys.model.Userwaiting;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserwaitingService {
    //    插入
    public void addUserinfo(Userinfo userinfo);

    //分页显示
    List<Userwaiting> selectAll(int pageNum, int pageSize);

    //按名字删除待审核用户列表
    int deleteByPrimaryKey(String username);

    //审核后加入
    int insertSelective(Userwaiting record);

    //按用户名查找
    Userwaiting selectByPrimaryKey(String username);

    //按用户名like 查询后分页
    List<Userwaiting> selectByName(int pageNum,int pageSize,String searchName);
}
