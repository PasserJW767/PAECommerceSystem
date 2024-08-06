package com.personal.ecommercesys.service.impl;

import com.personal.ecommercesys.dao.UserwaitingDao;
import com.personal.ecommercesys.model.Userinfo;
import com.personal.ecommercesys.model.Userwaiting;
import com.personal.ecommercesys.service.IUserwaitingService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userwatingService")
public class UserwatingServiceImpl implements IUserwaitingService {
    @Autowired
    UserwaitingDao userwaitingDao;

    @Override
    public void addUserinfo(Userinfo userinfo) {
        this.userwaitingDao.addUserinfo(userinfo);
    }

    @Override
    public List<Userwaiting> selectAll(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize){
        return userwaitingDao.selectAll(pageNum, pageSize);
    }

    @Override
    public int insertSelective(Userwaiting record){
        return userwaitingDao.insertSelective(record);
    }

    @Override
    public int deleteByPrimaryKey(String username){
        return userwaitingDao.deleteByPrimaryKey(username);
    }

    @Override
    public Userwaiting selectByPrimaryKey(String username){
        return userwaitingDao.selectByPrimaryKey(username);
    }

    @Override
    public List<Userwaiting> selectByName(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize,@Param("searchName") String searchName){
        return userwaitingDao.selectByName(pageNum,pageSize,searchName);
    }
}
