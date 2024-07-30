package com.volvo.mall.admin.service.impl;

import com.volvo.mall.admin.mapper.UmsMemberLevelMapper;
import com.volvo.mall.admin.model.UmsMemberLevel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class UmsMemberLevelServiceImplTest {

    @InjectMocks
    @Spy
    UmsMemberLevelServiceImpl umsMemberLevelService;

    @Mock
    UmsMemberLevelMapper memberLevelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * 测试获取member成功
     **/
    @Test
    void testList() {

        UmsMemberLevel umsMemberLevel = new UmsMemberLevel();

        umsMemberLevel.setId(1L);
        umsMemberLevel.setName("test");

        when(memberLevelMapper.selectByExample(any())).thenReturn(Collections.singletonList(umsMemberLevel));

        Assertions.assertNotNull(umsMemberLevelService.list(1));
    }
}