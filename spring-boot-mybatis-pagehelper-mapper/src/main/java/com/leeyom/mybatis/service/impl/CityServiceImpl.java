package com.leeyom.mybatis.service.impl;

import com.leeyom.mybatis.mapper.CityMapper;
import com.leeyom.mybatis.model.City;
import com.leeyom.mybatis.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cityService")
public class CityServiceImpl extends BaseServiceImpl<City> implements ICityService {

    @Autowired
    CityMapper cityMapper;


}
