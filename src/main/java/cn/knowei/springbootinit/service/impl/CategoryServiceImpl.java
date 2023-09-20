package cn.knowei.springbootinit.service.impl;

import cn.knowei.springbootinit.constant.RedisConstant;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.knowei.springbootinit.entity.Category;
import cn.knowei.springbootinit.service.CategoryService;
import cn.knowei.springbootinit.mapper.CategoryMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author zheng
* @description 针对表【kno_category】的数据库操作Service实现
* @createDate 2023-04-13 23:17:26
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public List<Category> getList() {
        List<Category> categories = (List<Category>) redisTemplate.opsForValue().get(RedisConstant.CATE_LIST);

        if (categories == null) {
            categories = this.list();


            redisTemplate.opsForValue().set(RedisConstant.CATE_LIST, categories);
        }
        return categories;
    }
}




