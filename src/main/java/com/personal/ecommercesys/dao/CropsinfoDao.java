package com.personal.ecommercesys.dao;

import com.personal.ecommercesys.model.Cropsinfo;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * @Entity com.personal.ecommercesys.model.Cropsinfo
 */
public interface CropsinfoDao {
    /**
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * @mbg.generated
     */
    int insert(Cropsinfo record);

    /**
     * @mbg.generated
     */
    int insertSelective(Cropsinfo record);

    /**
     * @mbg.generated
     */
    Cropsinfo selectByPrimaryKey(Integer id);

    /**
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Cropsinfo record);

    /**
     * @mbg.generated
     */
    int updateByPrimaryKey(Cropsinfo record);
//    获得所有产品
    public ArrayList getAllProduct();
//    从库中根据农作物名选取农作物信息
    public Cropsinfo selectByName(String name);
//    根据给出的关键字搜索类似的农作物
    public List<Cropsinfo> searchByKeyWord(@Param("pageNum")int pageNum, @Param("pageSize")int pageSize, @Param("keyWord") String keyWord);
//    搜索某个商家在售的物品并按上架时间新旧排序
    public ArrayList<Cropsinfo> selectBySellerAndSortByDate(String seller);
}