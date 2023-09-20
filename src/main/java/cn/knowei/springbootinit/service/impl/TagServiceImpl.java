package cn.knowei.springbootinit.service.impl;

import cn.knowei.springbootinit.common.BaseResponse;
import cn.knowei.springbootinit.constant.RedisConstant;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.knowei.springbootinit.entity.Tag;
import cn.knowei.springbootinit.service.TagService;
import cn.knowei.springbootinit.mapper.TagMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author zheng
* @description 针对表【kno_tag】的数据库操作Service实现
* @createDate 2023-04-13 23:17:26
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService{

}




