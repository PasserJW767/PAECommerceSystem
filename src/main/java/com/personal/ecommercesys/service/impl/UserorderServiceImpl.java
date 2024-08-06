package com.personal.ecommercesys.service.impl;

import com.personal.ecommercesys.dao.UserorderDao;
import com.personal.ecommercesys.model.Userorder;
import com.personal.ecommercesys.service.IUserorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service("userorderService")
public class UserorderServiceImpl implements IUserorderService {

    @Autowired
    UserorderDao userorderDao;

    @Override
    public int insertDoNotAddId(Userorder userorder) {
        System.out.println(userorder.getCropid());
        userorderDao.insert(userorder);
        return 0;
    }

    @Override
    public List<Userorder> selectByBuyer(String buyer){
        List<Userorder> list=userorderDao.selectByBuyer(buyer);
        list.sort(new Comparator<Userorder>() {
            @Override
            public int compare(Userorder userorder, Userorder t1) {
                return (int)(t1.getOrdercreatedate().getTime()-userorder.getOrdercreatedate().getTime());
            }
        });
        return list;
    }

    @Override
    public Userorder selectByPrimaryKey(Integer id) {
        return userorderDao.selectByPrimaryKey(id);
    }

}
