package com.personal.ecommercesys.controller.aboutProduct;

import com.personal.ecommercesys.model.Cropsinfo;
import com.personal.ecommercesys.model.Userinfo;
import com.personal.ecommercesys.model.Usershopcart;
import com.personal.ecommercesys.service.ICropsinfoService;
import com.personal.ecommercesys.service.IUserinfoService;
import com.personal.ecommercesys.service.IUsershopcartService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class productCenterController {
    @Autowired
    ICropsinfoService cropsinfoService;
    @Autowired
    IUserinfoService userinfoService;
    @Autowired
    IUsershopcartService usershopcartService;

//  跳转至商品界面
    @GetMapping("/productCenter/product")
    public String productDetails(String productId, ModelMap modelMap){
//        根据传进来的id从库中获得用户所选择的作物
        int id = Integer.parseInt(productId);
        Cropsinfo crop = cropsinfoService.selectByPrimaryKey(id);
//        从txt文件中读取详细作物信息
        File file = new File("src/main/resources/public/cropsText/" + crop.getDetailinfo());
        FileReader reader = null;
        try {
            reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuffer detailBuffer = new StringBuffer();
        char[] cbuf = new char[1024];
        int readLength = -1;
        try{
            while ((readLength = reader.read(cbuf))!=-1){
                detailBuffer.append(new String(cbuf,0,readLength));
            }
        }catch (IOException e){
            return "topPublic";
        } finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
//        获得作物的卖家用户名并查出卖家的信息
        Userinfo user = userinfoService.selectByUsername(crop.getSeller());
//        获得产品界面的最新供应信息
        List<Cropsinfo> cropNewList = cropsinfoService.sortByDate();
        List<String> cropIdList = new ArrayList<String>();
        List<String> cropNameList = new ArrayList<String>();
        List<String> cropTimeList = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date tempdate;
        String tempstr;
        for(int i = 0;i < 10;i++){
            cropIdList.add(String.valueOf(cropNewList.get(i).getId()));
            tempdate = cropNewList.get(i).getAddingtime();
            tempstr = sdf.format(tempdate);
            cropNameList.add(cropNewList.get(i).getName());//加入名字
            cropTimeList.add(tempstr);
        }
        modelMap.addAttribute("cropInfo",crop);
        modelMap.addAttribute("userInfo",user);
        modelMap.addAttribute("cropIdList",cropIdList);
        modelMap.addAttribute("cropNameList",cropNameList);
        modelMap.addAttribute("cropTimeList",cropTimeList);
        modelMap.addAttribute("cropDetailInfo",detailBuffer);
//        modelMap.addAttribute("addToCartFlag",0);
        return "products/productInfo";
    }

//  加入购物车功能
    @GetMapping("/productCenter/addToCart")
    public String addToCart(Integer productId, ModelMap modelMap,String logUser) {
        Cropsinfo crop = cropsinfoService.selectByPrimaryKey(productId);
        Userinfo user = userinfoService.selectByUsername(crop.getSeller());
        List<Cropsinfo> cropNewList = cropsinfoService.sortByDate();
        List<String> cropNameList = new ArrayList<String>();
        List<String> cropTimeList = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date tempdate;
        String tempstr;
        for(int i = 0;i < 10;i++){
            tempdate = cropNewList.get(i).getAddingtime();
            tempstr = sdf.format(tempdate);
            cropNameList.add(cropNewList.get(i).getName());//加入名字
            cropTimeList.add(tempstr);
        }
        //插入购物车
        Usershopcart usershopcart = new Usershopcart();
        System.out.println(productId);
        usershopcart.setCropid(productId);
        usershopcart.setCropname(crop.getName());
        usershopcart.setCropunit(crop.getUnit());
        usershopcart.setCosumeramount((double) 1);
        usershopcart.setCosumerprice(crop.getUnit() * (double)1);
        usershopcart.setAddtime(new java.sql.Date(new java.util.Date().getTime()));
        usershopcart.setBuyer(logUser);
        usershopcartService.insertDoNotAddId(usershopcart);
        modelMap.addAttribute("cropInfo",crop);
        modelMap.addAttribute("userInfo",user);
        modelMap.addAttribute("cropNameList",cropNameList);
        modelMap.addAttribute("cropTimeList",cropTimeList);
        modelMap.addAttribute("logUser",logUser);
        modelMap.addAttribute("addToCartFlag",1010);
        return "products/productInfo";
    }

}
