package com.leeyom.web;

import com.leeyom.dao.UserDao;
import com.leeyom.pojo.param.UserParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 控制层
 * @author Leeyom Wang
 * @date 2018年01月04日 17:17
 */
@Controller
public class UserController {

    @Resource
    private UserDao userDao;

    /**
     * 新增用户
     * @param userParam 前台参数封装
     * @param result    请求异常信息封装
     * @param model     请求结果封装
     * @return
     */
    @RequestMapping("/add")
    public String add(@Valid UserParam userParam, BindingResult result, ModelMap model) {


        return null;
    }

}
