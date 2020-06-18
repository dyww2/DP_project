package DpMa.service.impl;

import DpMa.dao.UserDao;
import DpMa.pojo.entity.User;
import DpMa.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * creator：Administrator
 * date:2019/12/17
 */

@Service // 交给springIOC(容器管理)后边就可以直接自动装配使用它了
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;


    @Override
    public boolean login(User user) {
        User dbUser = userDao.selectByPhone(user.getPhone());
        if (dbUser == null) {// 数据库里边都没有这个人，直接登录失败
            return false;
        } else {
            if (user.getPassword() == null) {// 如果传递过来的密码为null,直接登录失败
                return false;
            }
            if (user.getPassword().equals(dbUser.getPassword())) {// 如果传过来的密码，等于我查询的密码， 就表示登录成功！


                return true;
            }
        }
        return false;
    }

    @Override
    public User getUserByPhone(String phone) {
        return userDao.selectByPhone(phone);
    }

    @Override
    public void register(User user) {
        User u = userDao.selectByPhone(user.getPhone());
        if (u!=null){
            System.out.println("用户名已经存在");
        }else{
            if(user.getPhone()!=""&&user.getPassword()!=""){
                userDao.insertSelective(user);
            }
        }
    }
}
