package cn.knowei.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.knowei.springbootinit.entity.ArticleTag;
import cn.knowei.springbootinit.service.ArticleTagService;
import cn.knowei.springbootinit.mapper.ArticleTagMapper;
import org.springframework.stereotype.Service;

/**
* @author zheng
* @description 针对表【kno_article_tag】的数据库操作Service实现
* @createDate 2023-04-13 23:17:26
*/
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag>
    implements ArticleTagService{

}




