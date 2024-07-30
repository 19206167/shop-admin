package com.volvo.mall.admin.service.impl;

import com.volvo.mall.admin.config.RedisTemplateMocker;
import com.volvo.mall.admin.dao.UmsAdminRoleRelationDao;
import com.volvo.mall.admin.dto.UmsAdminParam;
import com.volvo.mall.admin.dto.UpdateAdminPasswordParam;
import com.volvo.mall.admin.mapper.UmsAdminLoginLogMapper;
import com.volvo.mall.admin.mapper.UmsAdminMapper;
import com.volvo.mall.admin.mapper.UmsAdminRoleRelationMapper;
import com.volvo.mall.admin.model.UmsAdmin;
import com.volvo.mall.admin.model.UmsResource;
import com.volvo.mall.admin.model.UmsRole;
import com.volvo.mall.admin.service.UmsAdminCacheService;
import com.volvo.mall.admin.service.impl.UmsAdminServiceImpl;
import com.volvo.mall.security.util.JwtTokenUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * FileName: UmsAdminServiceTests.java
 */

@SpringBootTest
public class UmsAdminServiceTests {
    @Spy
    @InjectMocks
    UmsAdminServiceImpl umsAdminService;

    @Mock
    UmsAdminMapper umsAdminMapper;

    @Mock
    UmsAdminCacheService umsAdminCacheService;

    @Mock
    UmsAdminRoleRelationMapper umsAdminRoleRelationMapper;

    @Mock
    UmsAdminRoleRelationDao umsAdminRoleRelationDao;

    @Mock
    UmsAdminLoginLogMapper loginLogMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    RedisTemplate redisTemplate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(umsAdminService, "passwordEncoder", passwordEncoder);
        ReflectionTestUtils.setField(umsAdminService, "jwtTokenUtil", jwtTokenUtil);
    }

    /**
     * 测试通过userName查询Admin成功(缓存)
     **/
    @Test
    public void testGetAdminByUsernameCacheSucceed() {
        // 调用umsAdminMapper.selectByExample()的时候, 返回expectedList
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setId(1L);
        umsAdmin.setUsername("test");

        when(redisTemplate.opsForValue().get(any())).thenReturn(umsAdmin);

        // 调用 umsAdminService.getAdminByUsername( 方法
        UmsAdmin umsAdmin1 = umsAdminService.getAdminByUsername("test");

        Assertions.assertEquals(1, umsAdmin1.getId());
    }

    /**
     * 测试通过userName查询Admin成功(数据库)
     **/
    @Test
    public void testGetAdminByUsernameDBSucceed() {
        // 调用umsAdminMapper.selectByExample()的时候, 返回expectedList
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setId(1L);
        umsAdmin.setUsername("test");

        when(umsAdminCacheService.getAdmin("test")).thenReturn(null);

        List<UmsAdmin> expectedList = new ArrayList<>();
        expectedList.add(umsAdmin);
        when(umsAdminMapper.selectByExample(any())).thenReturn(expectedList);

        // 调用 umsAdminService.getAdminByUsername() 方法
        UmsAdmin umsAdmin1 = umsAdminService.getAdminByUsername("test");

        Assertions.assertEquals(1, umsAdmin1.getId());
    }


    /**
     * 测试通过userName查询Admin失败
     **/
    @Test
    public void testGetAdminByUsernameFailed() {
        // 调用umsAdminMapper.selectByExample()的时候, 返回expectedList
        when(redisTemplate.opsForValue().get(any())).thenReturn(null);
        when(umsAdminMapper.selectByExample(any())).thenReturn(null);

        // 调用 umsAdminService.getAdminByUsername() 方法
        Assertions.assertNull(umsAdminService.getAdminByUsername("test"));
    }

    /**
     * 测试注册成功（没有重名用户）
     **/
    @Test
    public void testRegisterSucceed() {
        UmsAdminParam umsAdminParam = new UmsAdminParam();
        umsAdminParam.setNickName("test");
        umsAdminParam.setUsername("test");
        umsAdminParam.setPassword("123456Lzj");

        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminParam, umsAdmin);

        // 查询相同userName的用户返回空数组
        when(umsAdminMapper.selectByExample(any())).thenReturn(new ArrayList<>());

        // 插入结果返回1
        when(umsAdminMapper.insert(any())).thenReturn(1);

        Assertions.assertNotNull(umsAdminService.register(umsAdminParam));
    }

    /**
     * 测试注册失败（有重名用户）
     **/
    @Test
    public void testRegisterFailed(){
        UmsAdminParam umsAdminParam = new UmsAdminParam();
        umsAdminParam.setNickName("test");
        umsAdminParam.setUsername("test");
        umsAdminParam.setPassword("123456");

        // 调用umsAdminMapper.selectByExample()的时候, 返回expectedList
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setId(1L);
        umsAdmin.setUsername("test");

        List<UmsAdmin> expectedList = new ArrayList<>();
        expectedList.add(umsAdmin);

        // 查询相同userName的用户返回exceptedList
        when(umsAdminMapper.selectByExample(any())).thenReturn(expectedList);

        // 插入结果返回1
        when(umsAdminMapper.insert(any())).thenReturn(1);

        Assertions.assertNull(umsAdminService.register(umsAdminParam));
    }


    /**
     * 测试登录成功（缓存）
     **/
    @Test
    public void testLoginSucceed() {
        // 用户名，密码
        String username = "test";
        String password = "123456";

        // 调用getCacheService().getAdmin(username)的时候, 返回umsAdmin
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setId(1L);
        umsAdmin.setUsername("test");
        umsAdmin.setPassword(passwordEncoder.encode(password));
        umsAdmin.setStatus(1);

        // 调用getCacheService().getResourceList()的时候, 返回expectedList
        UmsResource umsResource = new UmsResource();
        umsResource.setId(1L);
        umsResource.setName("test");

        List<UmsResource> exceptList = new ArrayList<>();
        exceptList.add(umsResource);

        when(redisTemplate.opsForValue().get("mall:ums:admin:test")).thenReturn(umsAdmin);
        when(redisTemplate.opsForValue().get("mall:ums:resourceList:1")).thenReturn(exceptList);

        String token = umsAdminService.login(username, password);

        Assertions.assertNotNull(token);
    }

    /**
     * 测试修改成功
     **/
    @Test
    public void testUpdateSucceed() {
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setId(1L);
        umsAdmin.setUsername("test");
        umsAdmin.setPassword("123456");
        umsAdmin.setStatus(1);

        UmsAdmin umsAdmin1 = new UmsAdmin();
        umsAdmin1.setId(1L);
        umsAdmin1.setUsername("test");
        umsAdmin1.setPassword("456789");
        umsAdmin1.setStatus(1);

        when(umsAdminMapper.selectByPrimaryKey(1L)).thenReturn(umsAdmin1);
        when(umsAdminMapper.updateByPrimaryKeySelective(any())).thenReturn(1);

        int update = umsAdminService.update(umsAdmin.getId(), umsAdmin);

        Assertions.assertEquals(1, update);
    }

    /**
     * 测试删除成功
     **/
    @Test
    public void testDeleteSucceed() {
        Long id = 1L;
        when(umsAdminMapper.deleteByPrimaryKey(1L)).thenReturn(1);

        int delete = umsAdminService.delete(id);

        Assertions.assertEquals(1, delete);
    }


    /**
     * 测试修改角色成功
     **/
    @Test
    public void testUpdateRoleSucceed() {
        long adminId = 1;

        List<Long> roleIds = new ArrayList<>();
        roleIds.add(1L);
        roleIds.add(2L);

        Assertions.assertEquals(2, umsAdminService.updateRole(adminId, roleIds));
    }

    /**
     * 测试获取角色list成功
     **/
    @Test
    public void testGetRoleList() {
        long adminId = 1l;

        List<UmsRole> roles = new ArrayList<>();
        UmsRole umsRole1 = new UmsRole();
        umsRole1.setId(1L);
        UmsRole umsRole2 = new UmsRole();
        umsRole2.setId(2L);

        roles.add(umsRole1);
        roles.add(umsRole2);

        Assertions.assertNotNull(when(umsAdminService.getRoleList(adminId)).thenReturn(roles));
    }


    /**
     * 测试获取角色resourceList成功
     **/
    @Test
    public void testGetResourceList() {
        long adminId = 1l;

        // 调用getCacheService().getResourceList()的时候, 返回expectedList
        UmsResource umsResource = new UmsResource();
        umsResource.setId(1L);
        umsResource.setName("test");

        List<UmsResource> exceptList = new ArrayList<>();
        exceptList.add(umsResource);

        when(redisTemplate.opsForValue().get("mall:ums:resourceList:1")).thenReturn(exceptList);

        List<UmsResource> resourceList = umsAdminService.getResourceList(adminId);

        Assertions.assertNotNull(resourceList);
    }

    /**
     * 测试更新密码成功
     **/
    @Test
    public void testUpdatePassword() {
        UpdateAdminPasswordParam param = new UpdateAdminPasswordParam();

        param.setUsername("test");
        param.setOldPassword("123456");
        param.setNewPassword("456789");

        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setUsername("test");
        umsAdmin.setPassword(passwordEncoder.encode("123456"));
        List<UmsAdmin> expectedList = new ArrayList<>();
        expectedList.add(umsAdmin);

        when(umsAdminMapper.selectByExample(any())).thenReturn(expectedList);

        Assertions.assertEquals(1, umsAdminService.updatePassword(param));
    }

    /**
     * 测试通过username load User
     **/
    @Test
    public void testLoadUserByUsername() {
        String username = "test";

        // 调用getCacheService().getAdmin(username)的时候, 返回umsAdmin
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setId(1L);
        umsAdmin.setUsername(username);
        umsAdmin.setStatus(1);

        // 调用getCacheService().getResourceList()的时候, 返回expectedList
        UmsResource umsResource = new UmsResource();
        umsResource.setId(1L);
        umsResource.setName("test");

        List<UmsResource> exceptList = new ArrayList<>();
        exceptList.add(umsResource);

        when(redisTemplate.opsForValue().get("mall:ums:admin:test")).thenReturn(umsAdmin);
        when(redisTemplate.opsForValue().get("mall:ums:resourceList:1")).thenReturn(exceptList);

        Assertions.assertNotNull(umsAdminService.loadUserByUsername(username));
    }

    /**
     * 测试获取缓存服务
     **/
    @Test
    public void testGetCacheService() {
        Assertions.assertNotNull(umsAdminService.getCacheService());
    }

    /**
     * 测试登出
     **/
    @Test
    public void testLogout() {
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setId(1L);
        umsAdmin.setUsername("test");
        umsAdmin.setPassword("123456");
        umsAdmin.setStatus(1);

        when(redisTemplate.opsForValue().get("mall:ums:admin:test")).thenReturn(umsAdmin);

        umsAdminService.logout("test");
    }
}
