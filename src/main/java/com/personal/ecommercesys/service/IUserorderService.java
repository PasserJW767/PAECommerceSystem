package com.personal.ecommercesys.service;

import com.personal.ecommercesys.model.Userorder;

import java.util.List;

public interface IUserorderService {
    public int insertDoNotAddId(Userorder userorder);//Id为自动增长，不插入Id的插入语句
    public List<Userorder> selectByBuyer(String buyer);//按买家 查找订单表
    public Userorder selectByPrimaryKey(Integer id);//按订单号查出这个订单
}
