package com.personal.ecommercesys.service.impl;

import com.personal.ecommercesys.dao.UseridentifyidDao;
import com.personal.ecommercesys.model.Useridentifyid;
import com.personal.ecommercesys.service.IUseridentifyidService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("useridentifyidService")
public class UseridentifyidImpl implements IUseridentifyidService {

    @Autowired
    UseridentifyidDao useridentifyidDao;

    @Override
    public Useridentifyid selectByPrimaryKey(String id) {
        return useridentifyidDao.selectByPrimaryKey(id);
    }

    @Override
    public int insert(Useridentifyid record) {
        return useridentifyidDao.insertRecord(record);
    }
}
