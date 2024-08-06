package com.personal.ecommercesys.controller;

import ch.qos.logback.core.util.FileUtil;
import com.github.pagehelper.Page;
import com.mysql.cj.Session;
import com.personal.ecommercesys.model.*;
import com.personal.ecommercesys.service.*;
import com.personal.ecommercesys.service.IUserorderService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class PageController {

    @Autowired
    IUserinfoService userinfoService;
    @Autowired
    ICropsinfoService cropsinfoService;
    @Autowired
    IUserorderService userorderService;
    @Autowired
    IUsershopcartService usershopcartService;
    @Autowired
    IUserwaitingService userwaitingService;
    @Autowired
    IUseridentifyidService useridentifyidService;

    //    首页
    @GetMapping("/")
    public String index(ModelMap modelMap,HttpServletRequest request,String info) {
        if(info!=null){
            if("1001".equals(info)){
                request.getSession().setAttribute("loginUser","游客");
            }
        }
//        获取session中的用户的用户名
        Object loginUser = request.getSession().getAttribute("loginUser");
        String loginUserStr = (String) loginUser;
//        如果没有用户名，是空或者是游客的话，返回未登录的界面
        if (loginUser == null || "游客".equals(loginUserStr)) {
            return "topPublic";
        } else {
            return "topPublicSuccess";
        }
    }

    //    登录
    @GetMapping("/toLogin")
    public String toLogin(HttpServletRequest httpRequest, ModelMap modelMap) {
        return "Login";
    }
    //          注册
    @GetMapping("/toRegister")
    public String register(ModelMap modelMap,HttpServletRequest request) {
        System.out.println(request.getSession().getAttribute("loginUser"));
        return "register";
    }

    //注册后入库
    @RequestMapping("/register/toInsertUserinfo")
    public String insertUserinfo(HttpServletRequest httpServletRequest,Userinfo userinfo,ModelMap modelMap)
    {
        if (userinfo.getGrade().equals("普通用户")) {
            userinfoService.addUserinfo(userinfo);
            httpServletRequest.getSession().setAttribute("loginUser",userinfo.getUsername());
            modelMap.addAttribute("registerSuccessFlag",1002);
            return "topPublicSuccess";
        }
        else{
            userwaitingService.addUserinfo(userinfo);
            modelMap.addAttribute("registerFlag",1001);
            return "topPublic";
        }
    }

    //    产品中心页面
    @GetMapping("/productCenter")
    public String productCenter(ModelMap modelMap) {
        List<Cropsinfo> receiveList = cropsinfoService.sortBySales();
        List<Cropsinfo> cropNewList = cropsinfoService.sortByDate();
        List<Cropsinfo> endList = new ArrayList<Cropsinfo>();
//        以下分别为最新供应信息的作物编号、作物名、作物发布时间
        List<String> cropIdList = new ArrayList<String>();
        List<String> cropNameList = new ArrayList<String>();
        List<String> cropTimeList = new ArrayList<String>();
        for(int i = 0;i < 10;i++){
            endList.add(receiveList.get(i));
        }
//        最新供应信息的列表
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date tempdate;
        String tempstr;
        for(int i = 0;i < 10;i++){
//            向cropIdList中存入作物编号
            cropIdList.add(String.valueOf(cropNewList.get(i).getId()));
//            向cropTimeList中存入作物发布时间
            tempdate = cropNewList.get(i).getAddingtime();
            tempstr = sdf.format(tempdate);
            cropTimeList.add(tempstr);
//            向cropNameList中存入作物名字
            cropNameList.add(cropNewList.get(i).getName());//加入名字
        }
        modelMap.addAttribute("sortBySalesList",endList);
        modelMap.addAttribute("cropIdList",cropIdList);
        modelMap.addAttribute("cropNameList",cropNameList);
        modelMap.addAttribute("cropTimeList",cropTimeList);
        return "productCenter";
    }


    // 特色产业带
    @GetMapping("/IndustrialBelt")
    public String IndustrialBelt( ModelMap modelMap) {
        return "IndustrialBelt";
    }

    // 采供分类
    @GetMapping("/classification")
    public String classification(ModelMap modelMap) {
        List<Cropsinfo> receiveList = cropsinfoService.sortBySales();
        List<Cropsinfo> endList = new ArrayList<Cropsinfo>();
        for(int i = 0;i < 10;i++){
            endList.add(receiveList.get(i));
        }
        modelMap.addAttribute("sortBySalesList",endList);
        return "classification";
    }

    //  名企优录
    @GetMapping("/enterprise")
    public String goodEnterprise(ModelMap modelMap){
        return "enterprise";
    }


    //    登录界面点击登录的处理
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public String login(Userinfo user,ModelMap modelMap,HttpServletRequest request) {
        Userinfo logUser = userinfoService.selectForLogin(user);
        //modelMap.addAttribute("logUser",logUser);
        if (logUser != null) {
//            获得当前登录的用户用户名
            String logUserInfo = user.getUsername();
//            将当前用户的用户名存入Session中
            request.getSession().setAttribute("loginUser",logUserInfo);
//            返回登录成功的界面
            return "topPublicSuccess";
        } else {
//            登录失败，设置登录标识，便于弹出提示信息
            modelMap.addAttribute("loginFlag", 1001);
            request.getSession().setAttribute("loginUser","游客");
            return "Login";
        }
    }

    // 按名字搜索作物信息
    @GetMapping("/searchCropList")
    public String searchCropList(ModelMap modelMap, Integer pageNum, Integer pageSize,String keyWord,String logUser){
        if(pageNum==null){
            pageNum=1;
        }
        if (pageSize==null){
            pageSize=7;  //默认每页 多少条
        }
        List<Cropsinfo> list = cropsinfoService.searchByKeyWord(pageNum,pageSize,keyWord);
        if(list.isEmpty())
            modelMap.addAttribute("isEmpty",1088);
        else
            modelMap.addAttribute("isEmpty",1111);
        // 这里的 Page 类是 pagehelper 库中的类， 使用pagehelper分页查询会自动帮我们把结果封装成 Page 对象，它其实是一个 List
        modelMap.addAttribute("cropList",(Page<Cropsinfo>)list);
        modelMap.addAttribute("logUser",logUser);
        return "search";
    }

    @RequestMapping("/user/addUserinfo")
    public String addUserinfo()
    {
        return "register";
    }

    // 个人中心
    @GetMapping("/personal")
    public String personal(ModelMap modelMap,HttpServletRequest request) {
//        从session中获取当前登录用户的用户名，并通过这个用户名获得用户的信息，封装至Userinfo中
        Userinfo user=userinfoService.selectByUsername((String) request.getSession().getAttribute("loginUser"));
//        封装当前用户个人信息
        modelMap.addAttribute("theUser",user);
        String loginUser = user.getUsername();
//        获得当前用户订单并封装
        List<Userorder> orderList=userorderService.selectByBuyer(loginUser);
        modelMap.addAttribute("userOrders",orderList);
//        获得当前用户购物车并封装
        List<Usershopcart> shopList=usershopcartService.selectByBuyer(loginUser);
        modelMap.addAttribute("shopCartList",shopList);
        if(user.getGrade().equals("普通用户") || user.getGrade().equals("企业级用户") || user.getGrade().equals("政府机关")){
            return "personal";
        }
        else if(user.getGrade().equals("农户")){
//            若是商家,获得商家上架的作物信息并封装
            List<Cropsinfo> mySellList = cropsinfoService.selectBySellerAndSortByDate(loginUser);
            modelMap.addAttribute("mySellList",mySellList);
            return "merchantPersonal";
        }
        else
            return null;
    }

    //按顾客名字查找用户+修改库
    @RequestMapping("/personal/editPersonal")
    public String editPersonal(String gender,String telephone,String address,String identitycard,ModelMap modelMap,HttpServletRequest request)
    {
        Object loginUser = request.getSession().getAttribute("loginUser");
        Userinfo theInfo=userinfoService.selectByNameForEdit(loginUser.toString());
        theInfo.setGender(gender);
        theInfo.setTelephone(telephone);
        theInfo.setAddress(address);
        theInfo.setIdentitycard(identitycard);
        userinfoService.updateByPrimaryKey(theInfo);
        modelMap.addAttribute("theUser",theInfo);

        List<Userorder> orderList=userorderService.selectByBuyer(loginUser.toString());
        modelMap.addAttribute("userOrders",orderList);
        List<Usershopcart> shopList=usershopcartService.selectByBuyer(loginUser.toString());
        modelMap.addAttribute("shopCartList",shopList);
        modelMap.addAttribute("updateUserInfoFlag", 1077);

        String theGrade=theInfo.getGrade();
        if(theGrade.equals("农户")) {
            List<Cropsinfo> mySellList = cropsinfoService.selectBySellerAndSortByDate(loginUser.toString());
            modelMap.addAttribute("mySellList", mySellList);
            return "merchantPersonal";
        }
        else
            return "personal";
    }

    //查找cropinfo+修改库
    @RequestMapping("/merchantPersonal/editStores")
    public String editStores(String editId,String editunit,String editkucun,ModelMap modelMap,HttpServletRequest request){
        System.out.println(editId + editunit + editkucun);
        Object loginUser = request.getSession().getAttribute("loginUser");
        int theId=Integer.parseInt(editId);
        Cropsinfo crop=cropsinfoService.selectByPrimaryKey(theId);//按产品编号选择产品
        double theKucun=Double.parseDouble(editkucun);
        crop.setRepertory(theKucun);
        double theUnit=Double.parseDouble(editunit);
        crop.setUnit(theUnit);
        cropsinfoService.updateByPrimaryKeySelective(crop);
        Userinfo user=userinfoService.selectByUsername(loginUser.toString());
        modelMap.addAttribute("theUser",user);
        modelMap.addAttribute("logUser",loginUser.toString());
        List<Userorder> orderList=userorderService.selectByBuyer(loginUser.toString());
        modelMap.addAttribute("userOrders",orderList);
        List<Usershopcart> shopList=usershopcartService.selectByBuyer(loginUser.toString());
        modelMap.addAttribute("shopCartList",shopList);
        List<Cropsinfo> mySellList = cropsinfoService.selectBySellerAndSortByDate(loginUser.toString());
        modelMap.addAttribute("mySellList",mySellList);
        modelMap.addAttribute("updateFlag",1055);
        return "merchantPersonal";
    }

    //上传商品并返回这个界面
    @RequestMapping(value="/uploadCrop/toPersonCenter",method=RequestMethod.POST)
    public String uploadCrop(@RequestParam("thisCropName")String thisCropName,
                             @RequestParam("thisCropUnit")String thisCropUnit,
                             @RequestParam("thisCropRepertory") String thisCropRepertory,
                             @RequestParam("thisCropDetailInfo") String thisCropDetailInfo,
                             @RequestParam("thisCropImgLocation") MultipartFile thisCropImgLocation,
                             HttpServletRequest request,ModelMap modelMap){
//        获得当前登陆者
        String loginUser = (String) request.getSession().getAttribute("loginUser");
        if(useridentifyidService.selectByPrimaryKey(userinfoService.selectByUsername(loginUser).getIdentitycard()) == null){
            modelMap.addAttribute("noIdentifyFlag", 1001);
            return "identify";
        }
//        新建一个农作物信息类
        Cropsinfo record = new Cropsinfo();
//        设置农作物的名字、单价、库存
        record.setName(thisCropName);
        record.setUnit(Double.parseDouble(thisCropUnit));
        record.setRepertory(Double.parseDouble(thisCropRepertory));
        record.setSales((double) 0);
        record.setSeller(loginUser);
        record.setAddingtime(new java.sql.Date(new java.util.Date().getTime()));
//        获得当前时间并转换为相应字符串
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String formatDate = sdf.format(date);
        String fileName = loginUser + formatDate;
//        将作物介绍存储至对应文件夹中
        File file = new File("src/main/resources/public/cropsText/" + fileName + ".txt");
        String txtFileName = fileName + ".txt";
        record.setDetailinfo(txtFileName);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        BufferedWriter bfw = null;
        try {
            bfw = new BufferedWriter(new FileWriter(file));
            bfw.write(thisCropDetailInfo);
            bfw.flush();
        } catch (IOException e) {
            e.printStackTrace();
            file.delete();
        } finally {
            if(bfw != null){
                try {
                    bfw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
//        将图片存储至对应文件夹中
        MultipartFile tmpFile = thisCropImgLocation;
        String type = tmpFile.getOriginalFilename().substring(tmpFile.getOriginalFilename().lastIndexOf("."));//获得文件类型
        file = new File("src/main/resources/public/cropsImg",fileName + ".gif");//建立一个新的文件
        String pictureName = "/cropsImg/" + fileName + ".gif";
        record.setPicturelocation(pictureName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        byte[] bytes = null;
        try {
            bytes = tmpFile.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        将bytes数组写入到file文件中
        FileOutputStream fileOutStream = null;
        try{
            fileOutStream = new FileOutputStream(file);
            fileOutStream.write(bytes);
            fileOutStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fileOutStream != null){
                try {
                    fileOutStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        cropsinfoService.insert(record);
        System.out.println("插入成功！");
        //插入成功，返回个人中心
        Userinfo user=userinfoService.selectByUsername(loginUser);
        modelMap.addAttribute("theUser",user);
        List<Userorder> orderList=userorderService.selectByBuyer(loginUser);
        modelMap.addAttribute("userOrders",orderList);
        List<Usershopcart> shopList=usershopcartService.selectByBuyer(loginUser);
        modelMap.addAttribute("shopCartList",shopList);
        List<Cropsinfo> mySellList = cropsinfoService.selectBySellerAndSortByDate(loginUser);
        modelMap.addAttribute("mySellList",mySellList);
        return "merchantPersonal";
    }

//    到达认证页面
    @GetMapping("/identify")
    public String identify() {
        return "identify";
    }

    @RequestMapping(value="/identify/submit",method = RequestMethod.POST)
    public String identifySubmit(@RequestParam("ID")String ID,
                                 @RequestParam("realName")String realName,
                                 @RequestParam("idCardFront") MultipartFile idCardFront,
                                 @RequestParam("idCardBehind") MultipartFile idCardBehind,
                                 ModelMap modelMap,HttpServletRequest request){
        String loginUser = (String) request.getSession().getAttribute("loginUser");
        Userinfo userinfo = userinfoService.selectByUsername(loginUser);
        if(!userinfo.getIdentitycard().equals(ID)){
            modelMap.addAttribute("identifyFail", 1005);
            return "identify";
        }
        Useridentifyid useridentifyid = new Useridentifyid();
        useridentifyid.setId(ID);
        useridentifyid.setRealname(realName);
//        获得当前时间并转换为相应字符串
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String formatDate = sdf.format(date);
        String fileName = loginUser + formatDate;
//        插入身份证正面照
        MultipartFile tmpFile = idCardFront;
        String type = tmpFile.getOriginalFilename().substring(tmpFile.getOriginalFilename().lastIndexOf("."));//获得文件类型
        File file = new File("src/main/resources/public/userIdentify",fileName + "1.gif");//建立一个新的文件
        String pictureName = "/userIdentify/" + fileName + "1.gif";
        useridentifyid.setIdcardfront(pictureName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        byte[] bytes = null;
        try {
            bytes = tmpFile.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        将bytes数组写入到file文件中
        FileOutputStream fileOutStream = null;
        try{
            fileOutStream = new FileOutputStream(file);
            fileOutStream.write(bytes);
            fileOutStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        插入身份证反面照
        tmpFile = idCardBehind;
        type = tmpFile.getOriginalFilename().substring(tmpFile.getOriginalFilename().lastIndexOf("."));//获得文件类型
        file = new File("src/main/resources/public/userIdentify",fileName + "2.gif");//建立一个新的文件
        pictureName = "/userIdentify/" + fileName + "2.gif";
        useridentifyid.setIdcardbehind(pictureName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        bytes = null;
        try {
            bytes = tmpFile.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        将bytes数组写入到file文件中
        fileOutStream = null;
        try{
            fileOutStream = new FileOutputStream(file);
            fileOutStream.write(bytes);
            fileOutStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fileOutStream != null){
                try {
                    fileOutStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        useridentifyidService.insert(useridentifyid);
        modelMap.addAttribute("identifySuccess",1001);
        return "topPublicSuccess";
    }
}
