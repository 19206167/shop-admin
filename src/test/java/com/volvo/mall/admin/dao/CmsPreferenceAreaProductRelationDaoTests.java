package com.volvo.mall.admin.dao;

import com.volvo.mall.admin.model.CmsPrefrenceAreaProductRelation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * FileName: CmsPreferenceAreaProductRelationDaoTests.java
 */

@SpringBootTest
public class CmsPreferenceAreaProductRelationDaoTests {

    @Autowired
    CmsPreferenceAreaProductRelationDao cmsPreferenceAreaProductRelationDao;

    @Test
    @Transactional
    @Rollback
    public void testInsertList(){
        int length = 5;
        List<CmsPrefrenceAreaProductRelation> list = new ArrayList<>();

        for (long i = 0; i < length; i++) {
            CmsPrefrenceAreaProductRelation cmsPrefrenceAreaProductRelation =
                    new CmsPrefrenceAreaProductRelation();

            cmsPrefrenceAreaProductRelation.setPrefrenceAreaId(1L);
            cmsPrefrenceAreaProductRelation.setPrefrenceAreaId(i);

            list.add(cmsPrefrenceAreaProductRelation);
        }

        int count = cmsPreferenceAreaProductRelationDao.insertList(list);

        assertEquals(length, count);
    }

}
