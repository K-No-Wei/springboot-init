package cn.knowei.springbootinit.service.impl;

import cn.hutool.http.HttpUtil;
import cn.knowei.springbootinit.constant.RedisConstant;
import cn.knowei.springbootinit.entity.User;
import cn.knowei.springbootinit.entity.Vo.UserCodeVo;
import cn.knowei.springbootinit.entity.Vo.UserInfo;
import cn.knowei.springbootinit.entity.WeiXinRes;
import cn.knowei.springbootinit.mapper.UserMapper;
import cn.knowei.springbootinit.service.WeixinService;
import cn.knowei.springbootinit.utils.JwtUtils;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: knowei
 * @Description:
 * @Date: Create in 13:37 2023-04-15
 */
@Service
public class WeixinServiceImpl implements WeixinService {

    @Value("${weixin.appid}")
    private String appid;

    @Value("${weixin.secret}")
    private String secret;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private UserMapper userMapper;

    /**
     * 返回map 包含token和用户信息
     * @param userCodeVo
     * @return
     */
    @Override
    public Map<String, Object> getSsession(UserCodeVo userCodeVo) {

        String code = userCodeVo.getCode();
        UserInfo userInfo = userCodeVo.getUserInfo();

        String url =  "https://api.weixin.qq.com/sns/jscode2session?appid={0}&secret={1}&js_code={2}&grant_type=authorization_code";
        String replaceUrl = url.replace("{0}", appid).replace("{1}", secret).replace("{2}", code);
        String res = HttpUtil.get(replaceUrl);

        String s = UUID.randomUUID().toString();
        // redisTemplate.opsForValue().set(RedisConstant.WX_SESSION_ID + s, res);

        WeiXinRes weiXinRes = JSON.parseObject(res, WeiXinRes.class);

        LambdaQueryWrapper<User> qw = new LambdaQueryWrapper();
        System.out.println(weiXinRes);
        String preOpen = DigestUtils.md5DigestAsHex(weiXinRes.getOpenid().getBytes());
        qw.eq(User::getOpenid, preOpen);
        User user = userMapper.selectOne(qw);
        if (user == null) {
            user = new User();
            String openM = DigestUtils.md5DigestAsHex(weiXinRes.getOpenid().getBytes());
            user.setOpenid(openM);
            user.setUserName(userInfo.getNickName());
            user.setUserAvatar(user.getUserAvatar());
            userMapper.insert(user);
        }


        String jwt = JwtUtils.acquireJWT(user.getUserId(), user.getUserName());
        userInfo.setUserId(user.getUserId());
        //
        Map<String, Object> map = new HashMap<>();
        map.put("token", jwt);
        map.put("user", userInfo);
        return map;

    }

    public static void main(String[] args) {
        String s = JwtUtils.acquireJWT(123L, "zlw");
        System.out.println(s);

        Claims claims = JwtUtils.parseJWT(s);
        System.out.println(claims.get("userId"));
        System.out.println(claims);
    }
}
