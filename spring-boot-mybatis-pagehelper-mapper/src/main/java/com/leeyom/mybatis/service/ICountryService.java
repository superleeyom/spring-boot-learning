package com.leeyom.mybatis.service;

import com.leeyom.mybatis.model.Country;
import com.leeyom.mybatis.param.PageParam;
import com.leeyom.mybatis.vo.DataGridResult;

public interface ICountryService extends IBaseService<Country> {

    /**
     * 分页查找
     * @param pageParam 分页参数
     * @return
     */
    DataGridResult getCountryListByPage(PageParam pageParam);
}
