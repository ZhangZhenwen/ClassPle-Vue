package com.zhenwen.service;

import com.zhenwen.domain.User;
import com.zhenwen.utils.Pager;

import java.util.List;

/**
 * @author zhenwen
 * @date 2020/10/13
 */

public interface UserService {

    /**
     * 查找所有
     * @return list
     */
    List<User> findAll();

    /**
     * 分页查找
     * @param page 页数
     * @param size 容量
     * @return pager
     */
    Pager<User> findByPager(int page, int size);

    /**
     * 根据ID查找
     * @param id ID
     * @return User
     */
    User findById(int id);

    /**
     * 添加
     * @param user user
     * @return bool
     */
    Boolean insert(User user);

    /**
     * 添加老师
     * @param user user
     * @return bool
     */
    Boolean insertTchUser(User user);

    /**
     * 添加学生
     * @param user user
     * @return bool
     */
    Boolean insertStuUser(User user);

    /**
     * 更新
     * @param user user
     * @return bool
     */
    Boolean updateById(User user);

    /**
     * 删除
     * @param id User ID
     * @return bool
     */
    Boolean deleteById(int id);

    /**
     *
     * @param name 账户
     * @return bool
     */
    User selectByAccountTelEmail(String name);

    /**
     * 查找用户在课程中的角色
     * @param crseId
     * @return
     */
    String selectRoleCodeByCrseId(Integer crseId);
}










