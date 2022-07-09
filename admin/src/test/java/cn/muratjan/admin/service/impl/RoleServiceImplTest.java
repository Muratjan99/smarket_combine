package cn.muratjan.admin.service.impl;

import cn.muratjan.admin.service.RoleService;
import cn.muratjan.admin.service.RoleUserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author MRT
 * @date 2022/7/7 19:44
 */
@SpringBootTest
class RoleServiceImplTest {
//    @Resource
//    private RoleServiceImpl roleServiceImpl;
    @Resource
    private RoleUserService roleUserService;
    @Test
    void getAll() {
//        System.out.println(roleUserService.getUserRolesList(1L));
//        System.out.println(roleServiceImpl.getRoleActionList(1L));
        System.out.println(roleUserService.getUserRolesNameList(1L));
    }

}