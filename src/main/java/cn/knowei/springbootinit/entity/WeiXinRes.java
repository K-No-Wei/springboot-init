package cn.knowei.springbootinit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;

/**
 * @Author: knowei
 * @Description:
 * @Date: Create in 13:47 2023-04-15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WeiXinRes {
    ///{"session_key":"Zq\/Qna8MrEk8ctTYCD305g==",
    private String session_key;
    private String openid;
}
