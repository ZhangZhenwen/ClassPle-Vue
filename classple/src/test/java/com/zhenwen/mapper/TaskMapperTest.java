package com.zhenwen.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskMapperTest {

    @Autowired
    TaskMapper taskMapper;

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
        System.out.println(taskMapper.selectByPrimaryKey(1));
    }

    @Test
    void selectByAsgnId() {
    }

    @Test
    void selectByAsgnIdUserId() {
    }

    @Test
    void selectTaskCountByAsgnId() {
    }

    @Test
    void selectTaskHaveScoreCountByAsgnId() {
    }

    @Test
    void updateByPrimaryKeySelective() {
    }

    @Test
    void updateByPrimaryKey() {
    }
}
