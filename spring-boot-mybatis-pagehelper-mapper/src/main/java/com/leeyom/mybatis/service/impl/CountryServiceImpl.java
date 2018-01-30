package com.leeyom.mybatis.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leeyom.mybatis.mapper.CountryMapper;
import com.leeyom.mybatis.model.Country;
import com.leeyom.mybatis.param.PageParam;
import com.leeyom.mybatis.service.ICountryService;
import com.leeyom.mybatis.vo.DataGridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("countryService")
public class CountryServiceImpl extends BaseServiceImpl<Country> implements ICountryService {

    @Autowired
    CountryMapper countryMapper;


    /**
     * 分页查找
     * @param pageParam 分页参数封装
     * @return
     */
    @Override
    public DataGridResult getCountryListByPage(PageParam pageParam) {
        Integer pageNumber = pageParam.getPageNumber();
        Integer pageSize = pageParam.getPageSize();
        PageHelper.startPage(pageNumber, pageSize);
        List<Country> list = countryMapper.selectAll();
        PageInfo<Country> pageInfo = new PageInfo<>(list);
        DataGridResult dataGridResult = new DataGridResult();
        dataGridResult.setRows(list);
        dataGridResult.setTotal(pageInfo.getTotal());
        return dataGridResult;
    }
}
