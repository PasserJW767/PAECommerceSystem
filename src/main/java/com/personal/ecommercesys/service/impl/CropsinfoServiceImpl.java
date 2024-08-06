package com.personal.ecommercesys.service.impl;

import com.personal.ecommercesys.dao.CropsinfoDao;
import com.personal.ecommercesys.model.Cropsinfo;
import com.personal.ecommercesys.service.ICropsinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("cropsinfoService")
public class CropsinfoServiceImpl implements ICropsinfoService {
    @Autowired
    CropsinfoDao cropsinfoDao;

//    根据商品销量进行排序（高->低）
    @Override
    public ArrayList<Cropsinfo> sortBySales(){
        ArrayList<Cropsinfo> endList = cropsinfoDao.getAllProduct();
        endList.sort(new Comparator<Cropsinfo>() {
            @Override
            public int compare(Cropsinfo cropsinfo, Cropsinfo t1) {
                return (int) (t1.getSales() - cropsinfo.getSales());
            }
        });
        return endList;
    }

//    根据商品名进行商品选择
    @Override
    public Cropsinfo selectByName(String name){
        Cropsinfo returnCrop = cropsinfoDao.selectByName(name);
        return returnCrop;
    }

//    根据时间新旧进行商品排序
    @Override
    public ArrayList<Cropsinfo> sortByDate(){
        ArrayList<Cropsinfo> endList = cropsinfoDao.getAllProduct();
        endList.sort(new Comparator<Cropsinfo>() {
            @Override
            public int compare(Cropsinfo cropsinfo, Cropsinfo t1) {
                return (int) (t1.getAddingtime().getTime() - cropsinfo.getAddingtime().getTime());
            }
        });
        return endList;
    }

//    根据给出的关键字搜索类似的农作物
    @Override
    public List<Cropsinfo> searchByKeyWord(int pageNum, int pageSize, String keyWord) {
        return cropsinfoDao.searchByKeyWord(pageNum,pageSize,keyWord);
    }

    @Override
    public Cropsinfo selectByPrimaryKey(Integer id) {
        return cropsinfoDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Cropsinfo record) {
        cropsinfoDao.updateByPrimaryKeySelective(record);
        return 0;
    }

    @Override
    public ArrayList<Cropsinfo> selectBySellerAndSortByDate(String seller) {
        ArrayList<Cropsinfo> endList = cropsinfoDao.selectBySellerAndSortByDate(seller);
        endList.sort((cropsinfo, t1) -> (int) (t1.getAddingtime().getTime() - cropsinfo.getAddingtime().getTime()));
        return endList;
    }

    @Override
    public int insert(Cropsinfo record) {
        cropsinfoDao.insert(record);
        return 0;
    }




}
