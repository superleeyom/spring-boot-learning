package com.leeyom.web;

import com.leeyom.dao.UserDao;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * 控制层
 * @author Leeyom Wang
 * @date 2018年01月04日 17:17
 */
@Controller
public class UserController {

    @Resource
    private UserDao userDao;

}
