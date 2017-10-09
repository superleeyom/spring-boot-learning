package com.leeyom.controller;

import com.github.pagehelper.PageInfo;
import com.leeyom.model.Role;
import com.leeyom.model.RoleResources;
import com.leeyom.service.RoleResourcesService;
import com.leeyom.service.RoleService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Resource
    private RoleService roleService;
    @Resource
    private RoleResourcesService roleResourcesService;

    @RequestMapping
    public Map<String, Object> getAll(Role role, String draw,
                                      @RequestParam(required = false, defaultValue = "1") int start,
                                      @RequestParam(required = false, defaultValue = "10") int length) {

        Map<String, Object> map = new HashMap<>();
        PageInfo<Role> pageInfo = roleService.selectByPage(role, start, length);
        map.put("draw", draw);
        map.put("recordsTotal", pageInfo.getTotal());
        map.put("recordsFiltered", pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }

    @RequestMapping("/rolesWithSelected")
    public List<Role> rolesWithSelected(Integer uid) {
        return roleService.queryRoleListWithSelected(uid);
    }

    //分配角色
    @RequestMapping("/saveRoleResources")
    public String saveRoleResources(RoleResources roleResources) {
        if (StringUtils.isEmpty(roleResources.getRoleid()))
            return "error";
        try {
            roleResourcesService.addRoleResources(roleResources);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping(value = "/add")
    public String add(Role role) {
        try {
            roleService.save(role);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping(value = "/delete")
    public String delete(Integer id) {
        try {
            roleService.delRole(id);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }


}
