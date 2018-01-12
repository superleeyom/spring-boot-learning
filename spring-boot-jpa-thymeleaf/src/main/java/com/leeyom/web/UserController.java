package com.leeyom.web;

import com.leeyom.dao.UserRepository;
import com.leeyom.pojo.db.User;
import com.leeyom.pojo.param.UserParam;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * 控制层
 * @author Leeyom Wang
 * @date 2018年01月04日 17:17
 */
@Controller
public class UserController {

    @Resource
    private UserRepository userDao;

    /**
     * 新增用户
     * @param userParam 前台参数封装
     * @param result    请求异常信息封装
     * @param model     请求结果封装
     * @return
     */
    @RequestMapping("/add")
    public String add(@Valid UserParam userParam, BindingResult result, ModelMap model) {

        StringBuilder errorMsg = new StringBuilder("");
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            for (ObjectError error : allErrors) {
                errorMsg = errorMsg.append(error.getCode()).append("-").append(error.getDefaultMessage()).append(";");
            }
            model.addAttribute("errorMsg", errorMsg);
            return "user/userAdd";
        }

        User oldUser = userDao.findByUserName(userParam.getUserName());
        if (oldUser != null) {
            model.addAttribute("errorMsg", "用户已经存在！");
            return "user/userAdd";
        }

        User user = new User();
        BeanUtils.copyProperties(userParam, user);
        user.setRegTime(new Date());
        userDao.save(user);
        return "redirect:/list";
    }

}
