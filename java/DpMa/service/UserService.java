package DpMa.service;

import DpMa.pojo.entity.User;


public interface UserService {
    /**
     * 登录方法，如果登录成功，返回true，登录失败返回false
     *
     * @param user
     * @return
     */
    boolean login(User user);

    /**
     *
     * @param phone
     * @return
     */
    User getUserByPhone(String phone);

    void register(User user);
}
