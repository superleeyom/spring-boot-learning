package com.leeyom.mapper;

import com.leeyom.param.UserParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 动态sql
 * @author Leeyom Wang
 * @date 2018年01月26日 11:03
 */
public class UserSql {

    private static final Logger log = LoggerFactory.getLogger(UserSql.class);

    /**
     * 分页查找
     * @param userParam
     * @return
     */
    public String getUserListByPage(UserParam userParam) {
        StringBuffer sql = new StringBuffer("select id, user_name, password, user_sex, nick_name");
        sql.append(" from users where 1=1 ");
        if (userParam != null) {
            if (StringUtils.isNotBlank(userParam.getUserName())) {
                sql.append(" and user_name = #{userName}");
            }
            if (StringUtils.isNotBlank(userParam.getUserSex())) {
                sql.append(" and user_sex = #{userSex}");
            }
        }
        sql.append(" order by id desc");
        sql.append(" limit " + userParam.getPageNumber() + "," + userParam.getPageSize());
        log.info("getUserListByPage sql is :" + sql.toString());
        return sql.toString();
    }

    /**
     * 获取总记录数
     * @param userParam
     * @return
     */
    public String getCount(UserParam userParam) {
        String sql = new SQL() {{
            SELECT("count(1)");
            FROM("users");
            if (StringUtils.isNotBlank(userParam.getUserName())) {
                WHERE("user_name = #{userName}");
            }
            if (StringUtils.isNotBlank(userParam.getUserSex())) {
                WHERE("user_sex = #{userSex}");
            }
        }}.toString();

        log.info("getCount sql is :" + sql);
        return sql;
    }
}