package cn.knowei.springbootinit.config;

import cn.knowei.springbootinit.entity.Movies;
import cn.knowei.springbootinit.mapper.MoviesMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: knowei
 * @Description:
 * @Date: Create in 15:02 2023-04-13
 */
@Component
@Slf4j
public class RedisBloomFilter {

    @Resource
    private RedisTemplate redisTemplate;

    private static final String CACHE = "movies:";

    @Resource
    private MoviesMapper moviesMapper;


    @PostConstruct
    public void init() {
        // Boolean hasKey = redisTemplate.hasKey("whiteList");
        // if (hasKey) {
        //     return;
        // }
        // List<Movies> movies = moviesMapper.selectList(null);
        // List<Long> longList = movies.stream().map(Movies::getId).collect(Collectors.toList());
        //
        // longList.forEach(
        //         e -> {
        //             String key = CACHE + e;
        //             int hashValue = Math.abs((key.hashCode()));
        //             long index = (long) (hashValue % Math.pow(2, 32));
        //             // log.info(key + "--对应的位置：{}", index);
        //             redisTemplate.opsForValue().setBit("whiteList", index, true);
        //         }
        // );
    }
}
