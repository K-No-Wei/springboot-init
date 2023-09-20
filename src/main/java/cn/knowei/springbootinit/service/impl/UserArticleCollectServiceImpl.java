package cn.knowei.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.knowei.springbootinit.entity.UserArticleCollect;
import cn.knowei.springbootinit.service.UserArticleCollectService;
import cn.knowei.springbootinit.mapper.UserArticleCollectMapper;
import org.springframework.stereotype.Service;

/**
* @author zheng
* @description 针对表【kno_user_article_collect】的数据库操作Service实现
* @createDate 2023-04-15 16:52:42
*/
@Service
public class UserArticleCollectServiceImpl extends ServiceImpl<UserArticleCollectMapper, UserArticleCollect>
    implements UserArticleCollectService{

}




