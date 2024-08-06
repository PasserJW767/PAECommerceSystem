package com.personal.ecommercesys.service.impl;

import com.personal.ecommercesys.dao.UsershopcartDao;
import com.personal.ecommercesys.model.Userorder;
import com.personal.ecommercesys.model.Usershopcart;
import com.personal.ecommercesys.service.IUsershopcartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service("usershopcartService")
public class UsershopcartServiceImpl implements IUsershopcartService {

    @Autowired
    UsershopcartDao usershopcartDao;

    @Override
    public int insertDoNotAddId(Usershopcart usershopcart) {
        usershopcartDao.insertDoNotAddId(usershopcart);
        return 0;
    }

    @Override
    public List<Usershopcart> selectByBuyer(String buyer){
        List<Usershopcart> list=usershopcartDao.selectByBuyer(buyer);
        list.sort(new Comparator<Usershopcart>() {
            public int compare(Usershopcart usershopcart, Usershopcart t1) {
                return (int)(t1.getAddtime().getTime()-usershopcart.getAddtime().getTime());
            }
        });
        return list;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        usershopcartDao.deleteByPrimaryKey(id);
        return 0;
    }

    @Override
    public Usershopcart selectByPrimaryKey(Integer id) {
        return usershopcartDao.selectByPrimaryKey(id);
    }
}
