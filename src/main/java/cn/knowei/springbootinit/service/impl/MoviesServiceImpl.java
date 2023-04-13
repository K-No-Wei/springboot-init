package cn.knowei.springbootinit.service.impl;

import cn.knowei.springbootinit.common.BaseResponse;
import cn.knowei.springbootinit.common.ErrorCode;
import cn.knowei.springbootinit.common.PageRequest;
import cn.knowei.springbootinit.common.ResultUtils;
import cn.knowei.springbootinit.utils.CheckRedis;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.knowei.springbootinit.entity.Movies;
import cn.knowei.springbootinit.service.MoviesService;
import cn.knowei.springbootinit.mapper.MoviesMapper;
import io.lettuce.core.RedisClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
* @author zheng
* @description 针对表【movies】的数据库操作Service实现
* @createDate 2023-04-13 14:44:52
*/
@Service
public class MoviesServiceImpl extends ServiceImpl<MoviesMapper, Movies>
    implements MoviesService{
    @Resource
    private RedisTemplate redisTemplate;

    private static final String CACHE = "movies:";

    @Resource
    private CheckRedis checkRedis;

    @Override
    public BaseResponse<Movies> get(long id) {

        String  key = CACHE + id;

        // 布隆过滤器
        if (!checkRedis.checkWhite("whiteList", key)) {
            return ResultUtils.error(400, "无");
        }

        Movies movies = (Movies) redisTemplate.opsForValue().get(key);
        if (movies == null) {
            synchronized (MoviesService.class) {
                if (movies == null) {
                    movies = this.getById(id);

                    if (movies == null) {
                        return ResultUtils.error(ErrorCode.NOT_FOUND_ERROR);
                    } else {
                        redisTemplate.opsForValue().set(CACHE + id, movies, 7L, TimeUnit.MINUTES);
                    }
                }
            }
        }
        return ResultUtils.success(movies);
    }

    @Override
    public BaseResponse list(PageRequest page) {
        QueryWrapper<Movies> moviesQueryWrapper = new QueryWrapper<>();
        moviesQueryWrapper.isNotNull("cover");
        Page<Movies> moviesPage = this.page(new Page<>(page.getCurrent(), page.getPageSize()), moviesQueryWrapper);

        return ResultUtils.success(moviesPage);
    }
}




