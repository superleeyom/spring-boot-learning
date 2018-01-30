package com.leeyom.mybatis.controller;

import com.leeyom.mybatis.model.Country;
import com.leeyom.mybatis.param.PageParam;
import com.leeyom.mybatis.service.ICountryService;
import com.leeyom.mybatis.vo.DataGridResult;
import com.leeyom.mybatis.vo.ResultBean;
import com.leeyom.mybatis.vo.StatusCode;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryHandler {

    private static final Logger LOGGER = Logger.getLogger(CountryHandler.class);

    @Autowired
    ICountryService countryService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResultBean getCountryList() {
        ResultBean resultBean = new ResultBean();
        try {
            List<Country> countryList = countryService.selectAll();
            resultBean.setData(countryList);
        } catch (Exception e) {
            resultBean.setCode(StatusCode.HTTP_FAILURE);
            resultBean.setMsg("Request country list Failed！");
            LOGGER.error("查询列表失败！", e);
        }
        return resultBean;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResultBean getCountry(@PathVariable("id") Integer id) {
        ResultBean resultBean = new ResultBean();
        try {
            Country country = countryService.selectByPrimaryKey(id);
            resultBean.setData(country);
        } catch (Exception e) {
            resultBean.setCode(StatusCode.HTTP_FAILURE);
            resultBean.setMsg("Failed to request country details！");
            LOGGER.error("查询指定的Country失败！参数信息：id = " + id, e);
        }
        return resultBean;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResultBean add(@RequestBody Country country) {
        ResultBean resultBean = new ResultBean();
        try {
            countryService.insert(country);
        } catch (Exception e) {
            resultBean.setCode(StatusCode.HTTP_FAILURE);
            resultBean.setMsg("Create country Failed！");
            LOGGER.error("新增Country！参数信息：country = " + country.toString(), e);
        }
        return resultBean;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResultBean update(@PathVariable("id") Integer id, @RequestBody Country country) {
        ResultBean resultBean = new ResultBean();
        try {
            Country oldCountry = countryService.selectByPrimaryKey(id);
            oldCountry.setCountryCode(country.getCountryCode());
            oldCountry.setCountryName(country.getCountryName());
            countryService.updateByPrimaryKey(oldCountry);
        } catch (Exception e) {
            resultBean.setCode(StatusCode.HTTP_FAILURE);
            resultBean.setMsg("Update country failed！");
            LOGGER.error("更新失败！参数信息：id = " + id + ",country = " + country.toString(), e);
        }
        return resultBean;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResultBean delete(@PathVariable("id") Integer id) {
        ResultBean resultBean = new ResultBean();
        try {
            countryService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            resultBean.setCode(StatusCode.HTTP_FAILURE);
            resultBean.setMsg("Delete country failed！");
            e.printStackTrace();
            LOGGER.error("删除失败！参数信息：id = " + id, e);
        }
        return resultBean;
    }

    /**
     * 分页查找
     * @param pageParam 分页参数封装
     * @return
     */
    @RequestMapping(value = "/getCountryListByPage", method = RequestMethod.POST)
    public ResultBean getCountryListByPage(@RequestBody PageParam pageParam) {
        ResultBean resultBean = new ResultBean();
        try {
            DataGridResult dataGridResult = countryService.getCountryListByPage(pageParam);
            resultBean.setData(dataGridResult);
        } catch (Exception e) {
            resultBean.setCode(StatusCode.HTTP_FAILURE);
            resultBean.setMsg("获取分页失败！");
            e.printStackTrace();
            LOGGER.error("获取分页失败失败！参数信息：pageParam = " + pageParam.toString(), e);
        }
        return resultBean;

    }

}
