package com.zhenwen.mapper;

import com.zhenwen.domain.User;

import java.util.List;

/**
 * @author zhenwen
 */
public interface UserMapper {

    /**
     * 删除记录
     * @param userId
     * @return
     */
    int deleteByPrimaryKey(Integer userId);

    /**
     * 插入记录
     * @param record 记录
     * @return 影响行数
     */
    int insert(User record);

    /**
     * 插入记录
     * @param record 记录
     * @return 影响行数
     */
    int insertSelective(User record);

    /**
     * 选择记录
     * @param userId
     * @return
     */
    User selectByPrimaryKey(Integer userId);

    /**
     * 选择所有记录
     * @return
     */
    List<User> findAll();

    /**
     * 根据角色名称选择用户
     *
     * @param roleName
     * @return
     */
    List<User> findAllByRoleName(String roleName);

    /**
     * 根据角色ID选择用户
     *
     * @param roleId
     * @return
     */
    List<User> findByRoleId(int roleId);

    /**
     * 更新记录
     * @param record 记录
     * @return 影响行数
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * 更新记录
     * @param record 记录
     * @return 影响行数
     */
    int updateByPrimaryKey(User record);

    /**
     * 根据账户、手机号、邮箱选择用户
     *
     * @param name
     * @return
     */
    User selectByAccountTelEmail(String name);
}
