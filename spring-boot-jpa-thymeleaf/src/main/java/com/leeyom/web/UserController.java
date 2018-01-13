package com.leeyom.web;

import com.leeyom.dao.UserRepository;
import com.leeyom.pojo.db.User;
import com.leeyom.pojo.param.UserParam;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    /**
     * 获取用户列表
     * @param model 返回结果封装
     * @param page  当前页
     * @param size  每一页的数量
     * @return
     */
    @RequestMapping("/list")
    public String list(ModelMap model, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "6") Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        Page<User> userPage = userDao.findList(pageable);
        model.addAttribute("users", userPage);
        return "user/list";
    }

    /**
     * 编辑用户
     * @param userParam 前台参数封装
     * @param result    错误信息封装
     * @param model     返回信息封装
     * @return
     */
    @RequestMapping("/edit")
    public String edit(@Valid UserParam userParam, BindingResult result, ModelMap model) {
        StringBuilder errorMsg = new StringBuilder("");
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                errorMsg = errorMsg.append(error.getCode()).append("-").append(error.getDefaultMessage()).append(";");
            }
            model.addAttribute("errorMsg", errorMsg);
            model.addAttribute("user", userParam);
            return "user/userEdit";
        }

        User user = new User();
        BeanUtils.copyProperties(userParam, user);
        user.setRegTime(new Date());
        userDao.save(user);
        return "redirect:/list";
    }

    /**
     * 删除用户
     * @param id 用户id
     * @return
     */
    @RequestMapping("/delete")
    public String delete(Long id) {
        userDao.delete(id);
        return "redirect:/list";
    }


    /**
     * 跳转到新增页面
     * @return
     */
    @RequestMapping("/toAdd")
    public String toAdd() {
        return "user/userAdd";
    }

    /**
     * 跳转到编辑页面，数据回显
     * @param id 用户id
     * @return
     */
    @RequestMapping("/toEdit")
    public String toEdit(Model model, Long id) {
        User user = userDao.findById(id);
        model.addAttribute("user", user);
        return "user/userEdit";
    }


}
