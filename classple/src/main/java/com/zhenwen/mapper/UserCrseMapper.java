package com.zhenwen.mapper;

import com.zhenwen.domain.UserCrse;

import java.util.List;

/**
 * @author zhenwen
 */
public interface UserCrseMapper {

    /**
     * 删除记录
     * @param key
     * @return
     */
    int deleteByPrimaryKey(UserCrse key);

    /**
     * 插入记录
     * @param record 记录
     * @return 影响行数
     */
    int insert(UserCrse record);

    /**
     * 插入记录
     * @param record 记录
     * @return 影响行数
     */
    int insertSelective(UserCrse record);

    /**
     * 选择记录
     * @param userId
     * @param crseId
     * @return
     */
    UserCrse selectByPrimaryKey(Integer userId, Integer crseId);

    /**
     * 根据用户ID选择记录
     *
     * @param userId
     * @return
     */
    List<UserCrse> selectCoursesByUserId(Integer userId);

    /**
     * 查询用户在课程中的角色
     *
     * @param userId
     * @param crseId
     * @return
     */
    String selectRoleCodeByUserIdCrseId(Integer userId, Integer crseId);

    /**
     * 根据课程ID 用户身份查找用户
     * @param crseId
     * @param roleCode
     * @return
     */
    List<Integer> selectUserIdByCrseIdRoleCode(Integer crseId, String roleCode);

    /**
     * 根据课程ID选择用户ID
     * @param crseId
     * @return
     */
    List<Integer> selectUserIdByCrseId(Integer crseId);

    /**
     * 查找课程人数
     * @param crseId
     * @return
     */
    Integer selectUserCountByCrseId(Integer crseId);

    /**
     * 根据课程ID查找
     * @param crseId
     * @return
     */
    List<UserCrse> selectUserCrseByCrseId(Integer crseId);

    /**
     * 更新记录
     * @param record 记录
     * @return 影响行数
     */
    int updateByPrimaryKeySelective(UserCrse record);

    /**
     * 更新记录
     * @param record 记录
     * @return 影响行数
     */
    int updateByPrimaryKey(UserCrse record);
}
