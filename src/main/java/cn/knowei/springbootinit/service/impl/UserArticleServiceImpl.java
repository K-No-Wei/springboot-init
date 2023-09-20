package cn.knowei.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.knowei.springbootinit.entity.UserArticle;
import cn.knowei.springbootinit.service.UserArticleService;
import cn.knowei.springbootinit.mapper.UserArticleMapper;
import org.springframework.stereotype.Service;

/**
* @author zheng
* @description 针对表【kno_user_article】的数据库操作Service实现
* @createDate 2023-04-15 18:01:40
*/
@Service
public class UserArticleServiceImpl extends ServiceImpl<UserArticleMapper, UserArticle>
    implements UserArticleService{

}




