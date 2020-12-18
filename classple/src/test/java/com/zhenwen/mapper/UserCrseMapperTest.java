package com.zhenwen.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserCrseMapperTest {

    @Autowired
    UserCrseMapper userCrseMapper;

    @Test
    void selectRoleCodeByUserIdCrseId() {
        System.out.println(userCrseMapper.selectRoleCodeByUserIdCrseId(13, 2));
    }

    @Test
    void deleteByPrimaryKey() {
    }

    @Test
    void insert() {
    }

    @Test
    void insertSelective() {
    }

    @Test
    void selectByPrimaryKey() {
    }

    @Test
    void findCoursesByUserId() {
        System.out.println(userCrseMapper.selectCoursesByUserId(12));
    }

    @Test
    void findUserIdByCrseId() {
    }

    @Test
    void updateByPrimaryKeySelective() {
    }

    @Test
    void updateByPrimaryKey() {
    }
}
