package com.personal.ecommercesys.service;

import com.personal.ecommercesys.model.Cropsinfo;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public interface ICropsinfoService {
    public ArrayList<Cropsinfo> sortBySales();//根据商品价值进行排序（高->低）
    public Cropsinfo selectByName(String name);//根据商品名进行商品选择
    public ArrayList<Cropsinfo> sortByDate();//根据时间新旧进行商品排序
    public List<Cropsinfo> searchByKeyWord(@Param("pageNum")int pageNum, @Param("pageSize")int pageSize, @Param("keyWord") String keyWord);
    public Cropsinfo selectByPrimaryKey(Integer id);//根据Id搜索产品
    public int updateByPrimaryKeySelective(Cropsinfo record);//根据主键更新产品信息
    public ArrayList<Cropsinfo> selectBySellerAndSortByDate(String seller);//搜索某个商家在售的物品并按上架时间新旧排序
    public int insert(Cropsinfo record);//插入商品信息
}
