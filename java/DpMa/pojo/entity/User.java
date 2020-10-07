package DpMa.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * user
 * @author
 */
@Data
public class User implements Serializable {
    /**
     * 用户的id,主键
     */
    private Integer userId;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户的真实名称
     */
    private String realName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 用户登录密码
     */
    private String password;

    /**
     * 最后登录时间
     */
    private Date lastLoginTime;

    /**
     * 最后登录ip
     */
    private String lastLoginIp;

    private static final long serialVersionUID = 1L;


}
