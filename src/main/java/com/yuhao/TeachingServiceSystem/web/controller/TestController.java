package com.yuhao.TeachingServiceSystem.web.controller;



import com.alibaba.fastjson.JSONObject;
import com.yuhao.TeachingServiceSystem.dao.UserDAO;
import com.yuhao.TeachingServiceSystem.dto.UserDTO;
import com.yuhao.TeachingServiceSystem.security.PasswordEncoder;
import com.yuhao.TeachingServiceSystem.service.MyUserDetailsService;
import com.yuhao.TeachingServiceSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping(value = "/test")
public class TestController extends BaseController{

    @RequestMapping(value = "main")
    public String returnSuccess() {

       userService.find(new UserDTO(),null);

        System.out.println("主页");

        return "index";

    }

    @ResponseBody
    @RequestMapping(value = "json" ,produces = {"text/plain;charset=UTF-8"},method = RequestMethod.POST)
    public String returnJson(HttpServletRequest request, @RequestBody String data){

        System.out.println("data="+data);

        String msg=request.getParameter("message");


        System.out.println("msg="+msg);

        return ok();
    }






}
