package com.personal.ecommercesys.service.impl;

import com.personal.ecommercesys.dao.UserinfoDao;
import com.personal.ecommercesys.model.Userinfo;
import com.personal.ecommercesys.service.IUserinfoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//表示是一个Spring的bean,为了找到这个要加入这个注解
@Service("userinfoService")
public class UserinfoServiceImpl implements IUserinfoService {

    //自动注入mybatis DAO bean
    @Autowired
    UserinfoDao userinfoDao;

    @Override
    public Userinfo selectForLogin(Userinfo user) {
        return this.userinfoDao.selectForLogin(user);
    }

    @Override
    public void addUserinfo(Userinfo userinfo){
        this.userinfoDao.addUserinfo(userinfo);
    }

    @Override
    public String judgeName(String name){
        if(this.userinfoDao.judgeName(name) == null)
            return "true";
        else
            return "false";
    }

    @Override
    public List<Userinfo> selectAll(int pageNum, int pageSize) {
        return userinfoDao.selectAll(pageNum, pageSize);
    }

    @Override
    public List<Userinfo> selectByName(int pageNum, int pageSize,String searchName){
        return userinfoDao.selectByName(pageNum, pageSize,searchName);
    }

    @Override
    public int deleteByPrimaryKey(String username){
        return userinfoDao.deleteByPrimaryKey(username);
    }

    @Override
    public int updateByPrimaryKey(Userinfo record){
        return  userinfoDao.updateByPrimaryKey(record);
    }

    @Override
    public Userinfo selectByNameForEdit(String editName){
        return userinfoDao.selectByNameForEdit(editName);
    }

    @Override
    public Userinfo selectByPrimaryKey(String searchName) {
        return userinfoDao.selectByPrimaryKey(searchName);
    }

    //根据用户名搜索用户信息
    @Override
    public Userinfo selectByUsername(String userName){
        return userinfoDao.selectByUsername(userName);
    }
}

