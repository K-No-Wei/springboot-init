package cn.knowei.springbootinit.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: knowei
 * @Description:
 * @Date: Create in 15:06 2023-04-13
 */
@Component
@Slf4j
public class CheckRedis {

    @Resource
    private RedisTemplate redisTemplate;

    public boolean checkWhite(String item, String key) {
        int hashValue = Math.abs((key.hashCode()));
        long index = (long) (hashValue % Math.pow(2, 32));
        log.info(key + "--对应的位置：{}", index);

        Boolean flag = redisTemplate.opsForValue().getBit(item, index);

        log.info("--->key:" + key + "对应的下标:" + index + "是否存在:" + flag);
        return flag;
    }
}
