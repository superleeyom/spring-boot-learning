package com.leeyom.mybatis.controller;

import com.leeyom.mybatis.model.UserInfo;
import com.leeyom.mybatis.service.IUserInfoService;
import com.leeyom.mybatis.vo.ResultBean;
import com.leeyom.mybatis.vo.StatusCode;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userInfo")
public class UserInfoHandler {

    private static final Logger LOGGER = Logger.getLogger(UserInfoHandler.class);

    @Autowired
    IUserInfoService userInfoService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResultBean getUserInfoList() {
        ResultBean resultBean = new ResultBean();
        try {
            List<UserInfo> userInfoList = userInfoService.selectAll();
            resultBean.setData(userInfoList);
        } catch (Exception e) {
            resultBean.setCode(StatusCode.HTTP_FAILURE);
            resultBean.setMsg("Request userInfo list Failed！");
            LOGGER.error("查询列表失败！", e);
        }
        return resultBean;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResultBean getUserInfo(@PathVariable("id") Integer id) {
        ResultBean resultBean = new ResultBean();
        try {
            UserInfo userInfo = userInfoService.selectByPrimaryKey(id);
            resultBean.setData(userInfo);
        } catch (Exception e) {
            resultBean.setCode(StatusCode.HTTP_FAILURE);
            resultBean.setMsg("Failed to request userInfo details！");
            LOGGER.error("查询指定的UserInfo失败！参数信息：id = " + id, e);
        }
        return resultBean;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResultBean add(@RequestBody UserInfo userInfo) {
        ResultBean resultBean = new ResultBean();
        try {
            userInfoService.insert(userInfo);
        } catch (Exception e) {
            resultBean.setCode(StatusCode.HTTP_FAILURE);
            resultBean.setMsg("Create userInfo Failed！");
            LOGGER.error("新增UserInfo！参数信息：userInfo = " + userInfo.toString(), e);
        }
        return resultBean;
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResultBean update(@PathVariable("id") Integer id, @RequestBody UserInfo userInfo) {
        ResultBean resultBean = new ResultBean();
        try {
            UserInfo oldUserInfo = userInfoService.selectByPrimaryKey(id);
            oldUserInfo.setEmail(userInfo.getEmail());
            oldUserInfo.setPassword(userInfo.getPassword());
            oldUserInfo.setQq(userInfo.getQq());
            oldUserInfo.setEnabled(userInfo.getEnabled());
            oldUserInfo.setRealName(userInfo.getRealName());
            oldUserInfo.setUserName(userInfo.getUserName());
            oldUserInfo.setUserType(userInfo.getTel());
            oldUserInfo.setTel(userInfo.getTel());
            userInfoService.updateByPrimaryKey(oldUserInfo);
        } catch (Exception e) {
            resultBean.setCode(StatusCode.HTTP_FAILURE);
            resultBean.setMsg("Update userInfo failed！");
            LOGGER.error("更新失败！参数信息：id = " + id + ",userInfo = " + userInfo.toString(), e);
        }
        return resultBean;
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResultBean delete(@PathVariable("id") Integer id) {
        ResultBean resultBean = new ResultBean();
        try {
            userInfoService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            resultBean.setCode(StatusCode.HTTP_FAILURE);
            resultBean.setMsg("Delete userInfo failed！");
            e.printStackTrace();
            LOGGER.error("删除失败！参数信息：id = " + id, e);
        }
        return resultBean;
    }

}
