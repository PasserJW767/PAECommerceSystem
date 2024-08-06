package com.personal.ecommercesys.controller;

import com.personal.ecommercesys.model.Userinfo;
import com.personal.ecommercesys.model.Userwaiting;
import com.personal.ecommercesys.service.IUserinfoService;
import com.personal.ecommercesys.service.IUserwaitingService;
import net.sf.jsqlparser.statement.create.table.Index;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;
import com.personal.ecommercesys.model.Userinfo;
import com.personal.ecommercesys.service.IUserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.util.List;

@Controller
public class AdminHomePageController {

    @Autowired
    IUserwaitingService userwaitingService;

    @Autowired
    IUserinfoService userinfoService;

    @GetMapping("/admin/homePage")
    public String homePage(){
        return "Admin/AdminHomePage";
    }

    @GetMapping("/admin/userManage/addUser")
    public String addUser(ModelMap modelMap){
        modelMap.addAttribute("insertSignal",0);
        return "Admin/UserManage/addUser";
    }

    //添加用户的操作的控制器
    @RequestMapping(value = "/admin/UserManage/addUserIng", method = RequestMethod.POST)
    public String addUserToWaitTable(Userinfo user, ModelMap modelMap){
        if(user.getGrade().equals("商户"))
            userwaitingService.addUserinfo(user);
        else if(user.getGrade().equals("顾客"))
            userinfoService.addUserinfo(user);
        modelMap.addAttribute("insertSignal",1004);
        return "Admin/UserManage/addUser";
    }

    //分页显示待审核信息
    @RequestMapping("/admin/userManage/userWatigList")
    public String selectAll(ModelMap modelMap, Integer pageNum, Integer pageSize){
        if(pageNum==null){
            pageNum=1;
        }
        if (pageSize==null){
            pageSize=7;  //默认每页 多少条
        }
        List<Userwaiting> articles = this.userwaitingService.selectAll(pageNum, pageSize);

        // 这里的 Page 类是 pagehelper 库中的类， 使用pagehelper分页查询会自动帮我们把结果封装成 Page 对象，它其实是一个 List
        modelMap.addAttribute("articles",(Page<Userwaiting>)articles);

        return "Admin/UserManage/userWatingList";
    }

    //分页显示用户信息
    @RequestMapping("/admin/userManage/userInfoList")
    public String selectResources(ModelMap modelMap, Integer pageNum, Integer pageSize){
        if(pageNum==null){
            pageNum=1;
        }
        if (pageSize==null){
            pageSize=7;  //默认每页 多少条
        }
        List<Userinfo> articles = this.userinfoService.selectAll(pageNum, pageSize);

        // 这里的 Page 类是 pagehelper 库中的类， 使用pagehelper分页查询会自动帮我们把结果封装成 Page 对象，它其实是一个 List
        modelMap.addAttribute("articles",(Page<Userinfo>)articles);

        return "Admin/UserManage/userInfoList";
    }


    //按名字显示用户信息列表
    //@RequestMapping(value = "/userInfoSearchList",method = RequestMethod.POST)
    @RequestMapping("/searchNameList")
    public String searchNameList(ModelMap modelMap, Integer pageNum, Integer pageSize,String searchName){
        if(pageNum==null){
            pageNum=1;
        }
        if (pageSize==null){
            pageSize=7;  //默认每页 多少条
        }
        List<Userinfo> articles = this.userinfoService.selectByName(pageNum,pageSize,searchName);

        // 这里的 Page 类是 pagehelper 库中的类， 使用pagehelper分页查询会自动帮我们把结果封装成 Page 对象，它其实是一个 List
        modelMap.addAttribute("articles",(Page<Userinfo>)articles);
        return "Admin/UserManage/userInfoList";
    }

    //按名字删除用户信息
    @GetMapping("/deleteUserinfo")
    public String deleteUsernifo(String deleteName,ModelMap modelMap)
    {
        userinfoService.deleteByPrimaryKey(deleteName);
        Integer pageNum = 1;
        Integer pageSize = 7;
        List<Userinfo> articles = this.userinfoService.selectAll(pageNum, pageSize);
        // 这里的 Page 类是 pagehelper 库中的类， 使用pagehelper分页查询会自动帮我们把结果封装成 Page 对象，它其实是一个 List
        modelMap.addAttribute("articles",(Page<Userinfo>)articles);
        return "Admin/UserManage/userInfoList";
    }

    //按名字查找用户进行编辑
    @RequestMapping("/admin/userManage/selectForEdit")
    public String selectForEdit(String selectName,ModelMap modelMap)
    {
        Userinfo theInfo=userinfoService.selectByNameForEdit(selectName);
        modelMap.addAttribute("theInfo",theInfo);
        return "Admin/UserManage/editUserInfo";
    }

    //修改用户信息库
    @RequestMapping(value = "/admin/userManage/editUserInfo", method = RequestMethod.POST)
    public String editUserInfo(Userinfo userinfo,ModelMap modelMap)
    {
        userinfoService.updateByPrimaryKey(userinfo);
        Integer pageNum = 1;
        Integer pageSize = 7;
        List<Userinfo> articles = this.userinfoService.selectAll(pageNum, pageSize);
        // 这里的 Page 类是 pagehelper 库中的类， 使用pagehelper分页查询会自动帮我们把结果封装成 Page 对象，它其实是一个 List
        modelMap.addAttribute("articles",articles);

        return "Admin/UserManage/userInfoList";
    }

    //按名字删除待审核用户列表
    @GetMapping("/deleteUserWating")
    public String deleteUserWating(String deleteName,ModelMap modelMap)
    {
        userwaitingService.deleteByPrimaryKey(deleteName);
        Integer pageNum = 1;
        Integer pageSize = 7;
        List<Userwaiting> articles = this.userwaitingService.selectAll(pageNum, pageSize);
        // 这里的 Page 类是 pagehelper 库中的类， 使用pagehelper分页查询会自动帮我们把结果封装成 Page 对象，它其实是一个 List
        modelMap.addAttribute("articles",(Page<Userwaiting>)articles);
        return "Admin/UserManage/userWatingList";
    }

    //按名字处理待审核用户列表：①从待审核删除②加入用户列表
    @GetMapping("/todealUserWating")
    public String dealUserWating(String deleteName,ModelMap modelMap)
    {
        System.out.println(deleteName);
        Userinfo user=new Userinfo();
        Userwaiting userwaiting=userwaitingService.selectByPrimaryKey(deleteName);
        user.setUsername(deleteName);
        user.setGender(userwaiting.getGender());
        user.setUserpwd(userwaiting.getUserpwd());
        user.setTelephone(userwaiting.getTelephone());
        user.setAddress(userwaiting.getAddress());
        user.setGrade(userwaiting.getGrade());
        user.setIdentitycard(userwaiting.getIdentitycard());
        userinfoService.addUserinfo(user);
        userwaitingService.deleteByPrimaryKey(deleteName);
        Integer pageNum = 1;
        Integer pageSize = 7;
        List<Userwaiting> articles = this.userwaitingService.selectAll(pageNum, pageSize);
        // 这里的 Page 类是 pagehelper 库中的类， 使用pagehelper分页查询会自动帮我们把结果封装成 Page 对象，它其实是一个 List
        modelMap.addAttribute("articles",(Page<Userwaiting>)articles);
        return "Admin/UserManage/userWatingList";
    }

    //按名字显示查询后的用户信息列表
    @RequestMapping("/searchUserWating")
    public String searchUserWating(ModelMap modelMap, Integer pageNum, Integer pageSize,String searchName){
        if(pageNum==null){
            pageNum=1;
        }
        if (pageSize==null){
            pageSize=7;  //默认每页 多少条
        }
        List<Userwaiting> articles = this.userwaitingService.selectByName(pageNum,pageSize,searchName);

        // 这里的 Page 类是 pagehelper 库中的类， 使用pagehelper分页查询会自动帮我们把结果封装成 Page 对象，它其实是一个 List
        modelMap.addAttribute("articles",(Page<Userwaiting>)articles);
        return "Admin/UserManage/userWatingList";
    }
}
