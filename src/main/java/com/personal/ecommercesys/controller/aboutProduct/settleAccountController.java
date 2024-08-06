package com.personal.ecommercesys.controller.aboutProduct;


import com.personal.ecommercesys.model.*;
import com.personal.ecommercesys.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class settleAccountController {

    @Autowired
    CropsinfoServiceImpl cropsinfoService;

    @Autowired
    UserinfoServiceImpl userinfoService;

    @Autowired
    UserorderServiceImpl userorderService;

    @Autowired
    UsershopcartServiceImpl usershopcartService;

    @Autowired
    UseridentifyidImpl useridentifyidService;


    //    产品中心跳转至加入购物车界面控制器
    @GetMapping("/addToCart")
    public String addToCart(int productId, ModelMap modelMap, HttpServletRequest request){
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser == null){
            modelMap.addAttribute("unloginFlag", 1002);
            return "Login";
        }else {
            String loginUserStr = loginUser.toString();
            if("游客".equals(loginUserStr)){
                modelMap.addAttribute("unloginFlag", 1002);
                return "Login";
            }
            else {
                Userinfo userinfo = userinfoService.selectByUsername(loginUserStr);
                Useridentifyid useridentifyid = useridentifyidService.selectByPrimaryKey(userinfo.getIdentitycard());
                if(useridentifyid == null){
//                      如果未进行身份认证
                    modelMap.addAttribute("noIdentifyFlag", 1001);
                    return "identify";
                }
                else{
//                      如果进行了身份认证
                    Cropsinfo crop = cropsinfoService.selectByPrimaryKey(productId);
                    modelMap.addAttribute("cropInfo",crop);
                    return "settleAccount/addToCart";
                }
            }
        }
    }

//    产品中心跳转至购买界面控制器
    @GetMapping("/settleAccount")
    public String settleAccount(int productId, ModelMap modelMap, HttpServletRequest request){
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser == null){
            modelMap.addAttribute("unloginFlag", 1002);
            return "Login";
        }else {
            String loginUserStr = loginUser.toString();
            if("游客".equals(loginUserStr)){
                modelMap.addAttribute("unloginFlag", 1002);
                return "Login";
            }
            else {
                Userinfo userinfo = userinfoService.selectByUsername(loginUserStr);
                Useridentifyid useridentifyid = useridentifyidService.selectByPrimaryKey(userinfo.getIdentitycard());
                if(useridentifyid == null){
//                      如果未进行身份认证
                    modelMap.addAttribute("noIdentifyFlag", 1001);
                    return "identify";
                }
                else{
//                      如果进行了身份认证
                    Cropsinfo crop = cropsinfoService.selectByPrimaryKey(productId);
                    modelMap.addAttribute("cropInfo",crop);
                    return "settleAccount/settleAccount";
                }
            }
        }
    }


//    确认加入购物车
    @GetMapping("/certainToAddInCart")
    public String certainToAddInCart(Integer cropId, double amount, ModelMap modelMap,HttpServletRequest request){
        Cropsinfo cropsinfo = cropsinfoService.selectByPrimaryKey(cropId);//获得这个农产品以及它的单价
        double cropUnit = cropsinfo.getUnit();
        double sumPrice = amount*cropUnit;
        Object loginUser = request.getSession().getAttribute("loginUser");
        Usershopcart usershopcart = new Usershopcart();
        usershopcart.setCropid(cropId);
        usershopcart.setCropname(cropsinfo.getName());
        usershopcart.setCropunit(cropsinfo.getUnit());
        usershopcart.setCosumeramount(amount);
        usershopcart.setCosumerprice(sumPrice);
        usershopcart.setBuyer(loginUser.toString());
        usershopcart.setAddtime(new java.sql.Date(new java.util.Date().getTime()));
//        将用户选择加入购物车的信息写入购物车表中
        usershopcartService.insertDoNotAddId(usershopcart);
        modelMap.addAttribute("addToCartFlag",1005);
//        获得跳转到原本的商品详情界面所需的数据
//        从txt文件中读取详细作物信息
        File file = new File("src/main/resources/public/cropsText/" + cropsinfo.getDetailinfo());
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
        Userinfo user = userinfoService.selectByUsername(cropsinfo.getSeller());
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
        modelMap.addAttribute("cropInfo",cropsinfo);
        modelMap.addAttribute("userInfo",user);
        modelMap.addAttribute("cropIdList",cropIdList);
        modelMap.addAttribute("cropNameList",cropNameList);
        modelMap.addAttribute("cropTimeList",cropTimeList);
        modelMap.addAttribute("cropDetailInfo",detailBuffer);
        return "products/productInfo";
    }


    //确认购买
    @GetMapping("/certainToBuy")
    public String certainToBuy(Integer cropId,double amount,ModelMap modelMap,HttpServletRequest request){
//        获得个人信息
        Object loginUser = request.getSession().getAttribute("loginUser");
//        获得这个农作物产品
        Cropsinfo crop = cropsinfoService.selectByPrimaryKey(cropId);
        double allValue = amount * crop.getUnit();
        System.out.println(crop.getId());
//        生成一个订单类型对象存储数据
        Userorder userorder = new Userorder();
        userorder.setCropid(crop.getId());
        userorder.setCropname(crop.getName());
        userorder.setShopamount(amount);
        userorder.setTradeunit(crop.getUnit());
        userorder.setShopallvalue(allValue);
        userorder.setBuyer(loginUser.toString());
        userorder.setOrdercreatedate(new java.sql.Date(new java.util.Date().getTime()));
//      将这个订单插入到订单库中
        userorderService.insertDoNotAddId(userorder);
//        对商品库存进行修改
        crop.setRepertory(crop.getRepertory() - amount);
        crop.setSales(crop.getSales() + amount);
        cropsinfoService.updateByPrimaryKeySelective(crop);
//      获得跳转到原本的商品详情界面所需的数据
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
        modelMap.addAttribute("settleAccountFlag",1008);
        return "products/productInfo";
    }

//    从个人中心控制器到结算
    @GetMapping("/settleAccountFromPersonal")
    public String settleAccountFromPersonal(int orderId,ModelMap modelMap){
//        获得用户的购物车订单信息
        Usershopcart thisShopCart = usershopcartService.selectByPrimaryKey(orderId);
        Cropsinfo crop = cropsinfoService.selectByPrimaryKey(thisShopCart.getCropid());
        modelMap.addAttribute("cropInfo",crop);
        modelMap.addAttribute("order",thisShopCart);
        return "settleAccount/settleAccountFromPerson";
    }

//  从个人中心过去的确认结算后返回的个人中心
    @GetMapping("/certainToBuyFromPerson")
    public String certainToBuyFromPerson(Integer cropId,double amount,Integer orderId,ModelMap modelMap,HttpServletRequest request){
//        获得个人信息
        Object loginUser = request.getSession().getAttribute("loginUser");
//        获取需要添加的数据
        Cropsinfo crop = cropsinfoService.selectByPrimaryKey(cropId);
        double allValue = amount * crop.getUnit();
//        生成一个订单类型的对象存储数据
        Userorder userorder = new Userorder();
        userorder.setCropid(cropId);
        userorder.setCropname(crop.getName());
        userorder.setShopamount(amount);
        userorder.setTradeunit(crop.getUnit());
        userorder.setShopallvalue(allValue);
        userorder.setBuyer(loginUser.toString());
        userorder.setOrdercreatedate(new java.sql.Date(new java.util.Date().getTime()));
//      将这个订单插入到订单库中
        userorderService.insertDoNotAddId(userorder);
//      对该商品的库存进行修改
        crop.setRepertory(crop.getRepertory()-amount);
        crop.setSales(crop.getSales() + amount);
        cropsinfoService.updateByPrimaryKeySelective(crop);
//      删除购物车的这个订单
        usershopcartService.deleteByPrimaryKey(orderId);
//      获取到个人中心界面所需要的数据
        modelMap.addAttribute("buyThisCrop",1011);
        Userinfo user=userinfoService.selectByUsername(loginUser.toString());
        modelMap.addAttribute("theUser",user);
        List<Userorder> orderList=userorderService.selectByBuyer(loginUser.toString());
        modelMap.addAttribute("userOrders",orderList);
        List<Usershopcart> shopList=usershopcartService.selectByBuyer(loginUser.toString());
        modelMap.addAttribute("shopCartList",shopList);
        if(user.getGrade().equals("普通用户") || user.getGrade().equals("企业级用户") || user.getGrade().equals("政府机关")){
            return "personal";
        }
        else if(user.getGrade().equals("农户")){
            List<Cropsinfo> mySellList = cropsinfoService.selectBySellerAndSortByDate(loginUser.toString());
            modelMap.addAttribute("mySellList",mySellList);
            return "merchantPersonal";
        }
        else
            return null;
    }

//    个人中心删除订单
    @GetMapping("/deleteShopCart")
    public String deletShopCart(Integer orderId,ModelMap modelMap,HttpServletRequest request){
        //        获得个人信息
        Object loginUser = request.getSession().getAttribute("loginUser");
//        删除购物车订单
        usershopcartService.deleteByPrimaryKey(orderId);
        //      获取到个人中心界面所需要的数据
        modelMap.addAttribute("deleteThisCrop",1013);
        Userinfo user=userinfoService.selectByUsername(loginUser.toString());
        modelMap.addAttribute("theUser",user);
        List<Userorder> orderList=userorderService.selectByBuyer(loginUser.toString());
        modelMap.addAttribute("userOrders",orderList);
        List<Usershopcart> shopList=usershopcartService.selectByBuyer(loginUser.toString());
        modelMap.addAttribute("shopCartList",shopList);
        if(user.getGrade().equals("普通用户") || user.getGrade().equals("企业级用户") || user.getGrade().equals("政府机关")){
            return "personal";
        }
        else if(user.getGrade().equals("农户")){
            List<Cropsinfo> mySellList = cropsinfoService.selectBySellerAndSortByDate(loginUser.toString());
            modelMap.addAttribute("mySellList",mySellList);
            return "merchantPersonal";
        }
        else
            return null;
    }

}
