package cn.knowei.springbootinit.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 
 * @TableName kno_user
 */
@TableName(value ="kno_user")
@Data
@Entity
public class User implements Serializable {
    /**
     * 用户id
     */
    @Id
    @TableId(value = "user_id")
    private Long userId;

    /**
     * 用户名称
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 用户头像
     */
    @TableField(value = "user_avatar")
    private String userAvatar;

    /**
     * 用户session
     */
    @TableField(value = "session_key")
    private String sessionKey;

    /**
     * 用户openid
     */
    @TableField(value = "openid")
    private String openid;

    /**
     * 用户盐
     */
    @TableField(value = "serclet")
    private String serclet;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}