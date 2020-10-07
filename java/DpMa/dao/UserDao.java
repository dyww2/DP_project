package DpMa.dao;

import DpMa.pojo.entity.User;

public interface UserDao {
    // 根据主键删除
    int deleteByPrimaryKey(Integer userId);
    // 插入数据
    int insert(User record);

    /**
     * 选择性的插入：
     *
     * @param record
     * @return
     */
    int insertSelective(User record);

    // 根据主键查询
    User selectByPrimaryKey(Integer userId);

    // 根据主键选择性的更新，传了值的字段（属性），就更新，否则，就不更新这个字段
    int updateByPrimaryKeySelective(User record);

    //更新全部
    int updateByPrimaryKey(User record);

    /**
     * 根据真实名称查询用户信息
     *
     * @param realName 传递的真实姓名的值
     * @return 返回一个User对象
     */
    User selectUserByRealName(String realName);

    /**
     * 根据用户的电话号码查询这个用户的基本信息
     *
     * @param phone
     * @return
     */
    User selectByPhone(String phone);

}
