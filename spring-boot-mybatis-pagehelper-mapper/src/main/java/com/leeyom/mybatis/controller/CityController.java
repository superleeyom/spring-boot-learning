package com.leeyom.mybatis.controller;

import com.leeyom.mybatis.model.City;
import com.leeyom.mybatis.service.ICityService;
import com.leeyom.mybatis.vo.ResultBean;
import com.leeyom.mybatis.vo.StatusCode;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/city")
public class CityController {

    private static final Logger LOGGER = Logger.getLogger(CityController.class);

    @Autowired
    ICityService cityService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResultBean getCityList() {
        ResultBean resultBean = new ResultBean();
        try {
            List<City> cityList = cityService.selectAll();
            resultBean.setData(cityList);
        } catch (Exception e) {
            resultBean.setCode(StatusCode.HTTP_FAILURE);
            resultBean.setMsg("Request city list Failed！");
            LOGGER.error("查询列表失败！", e);
        }
        return resultBean;
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResultBean getCity(@PathVariable("id") Integer id) {
        ResultBean resultBean = new ResultBean();
        try {
            City city = cityService.selectByPrimaryKey(id);
            resultBean.setData(city);
        } catch (Exception e) {
            resultBean.setCode(StatusCode.HTTP_FAILURE);
            resultBean.setMsg("Failed to request city details！");
            LOGGER.error("查询指定的City失败！参数信息：id = " + id, e);
        }
        return resultBean;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResultBean add(@RequestBody City city) {
        ResultBean resultBean = new ResultBean();
        try {
            cityService.insert(city);
        } catch (Exception e) {
            resultBean.setCode(StatusCode.HTTP_FAILURE);
            resultBean.setMsg("Create city Failed！");
            LOGGER.error("新增City！参数信息：city = " + city.toString(), e);
        }
        return resultBean;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResultBean update(@PathVariable("id") Integer id, @RequestBody City city) {
        ResultBean resultBean = new ResultBean();
        try {
            City oldCity = cityService.selectByPrimaryKey(id);
            oldCity.setName(city.getName());
            oldCity.setState(city.getState());
            cityService.updateByPrimaryKey(oldCity);
        } catch (Exception e) {
            resultBean.setCode(StatusCode.HTTP_FAILURE);
            resultBean.setMsg("Update city failed！");
            LOGGER.error("更新失败！参数信息：id = " + id + ",city = " + city.toString(), e);
        }
        return resultBean;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResultBean delete(@PathVariable("id") Integer id) {
        ResultBean resultBean = new ResultBean();
        try {
            cityService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            resultBean.setCode(StatusCode.HTTP_FAILURE);
            resultBean.setMsg("Delete city failed！");
            e.printStackTrace();
            LOGGER.error("删除失败！参数信息：id = " + id, e);
        }
        return resultBean;
    }

}
