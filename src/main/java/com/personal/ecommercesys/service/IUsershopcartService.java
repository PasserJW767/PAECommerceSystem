package com.personal.ecommercesys.service;

import com.personal.ecommercesys.model.Usershopcart;

import java.util.List;

public interface IUsershopcartService {
    public int insertDoNotAddId(Usershopcart usershopcart);
    public List<Usershopcart> selectByBuyer(String buyer);
    public int deleteByPrimaryKey(Integer id);//根据购物车号删除购物车
    public Usershopcart selectByPrimaryKey(Integer id);
}
