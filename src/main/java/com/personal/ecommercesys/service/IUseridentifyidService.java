package com.personal.ecommercesys.service;

import com.personal.ecommercesys.model.Useridentifyid;

public interface IUseridentifyidService {
    public Useridentifyid selectByPrimaryKey(String id);
    public int insert(Useridentifyid record);
}
