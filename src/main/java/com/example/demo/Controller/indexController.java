package com.example.demo.Controller;

import com.example.demo.Entity.User;
import com.example.demo.Service.IUserService;
import com.example.demo.Service.Mongo.IUserMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ludonglue on 2017/9/11.
 */
@Controller
public class indexController {
    @Autowired
    IUserService userService;
    @Autowired
    IUserMongoService userMongoService;
    @RequestMapping("/")
    public ModelAndView index(){
        return new ModelAndView("index");
    }

    @RequestMapping("/getUser")
    public ModelAndView getUser(){
        ModelAndView result = new ModelAndView("userDiv");
        List<User> userList = userService.selectUserList();
        result.addObject("userList", userList);
        return result;
    }

    @RequestMapping("/getMongoUser")
    public ModelAndView getMongoUser(){
        ModelAndView result = new ModelAndView("userDiv");
        List<User> userList = userMongoService.selectUserList();
        result.addObject("userList", userList);
        return result;
    }
}


