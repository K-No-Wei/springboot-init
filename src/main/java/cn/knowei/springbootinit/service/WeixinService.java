package cn.knowei.springbootinit.service;

import cn.knowei.springbootinit.entity.Vo.UserCodeVo;

import java.util.Map;

/**
 * @Author: knowei
 * @Description:
 * @Date: Create in 13:37 2023-04-15
 */
public interface WeixinService {
    Map<String, Object> getSsession(UserCodeVo userCodeVo);
}
