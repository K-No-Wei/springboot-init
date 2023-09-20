package cn.knowei.springbootinit.service.impl;

import cn.knowei.springbootinit.entity.Article;
import cn.knowei.springbootinit.entity.ArticleTag;
import cn.knowei.springbootinit.entity.dto.ArticleDTO;
import cn.knowei.springbootinit.mapper.ArticleMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.knowei.springbootinit.entity.ArticleCategory;
import cn.knowei.springbootinit.service.ArticleCategoryService;
import cn.knowei.springbootinit.mapper.ArticleCategoryMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author zheng
* @description 针对表【kno_article_category】的数据库操作Service实现
* @createDate 2023-04-13 23:17:26
*/
@Service
public class ArticleCategoryServiceImpl extends ServiceImpl<ArticleCategoryMapper, ArticleCategory>
    implements ArticleCategoryService{

    @Resource
    private ArticleMapper articleMapper;

    @Override
    public List<ArticleDTO> getListByCateId(Long id) {
        LambdaQueryWrapper<ArticleCategory> qw = new LambdaQueryWrapper<>();
        qw.eq(ArticleCategory::getCategoryId, id);

        List<ArticleCategory> articleCategories = list(qw);
        List<Long> articleIdList = articleCategories.stream()
                .map(e -> e.getArticleId())
                .collect(Collectors.toList());
        //
        List<ArticleDTO> articleDTOS = articleIdList.stream()
                .map(e -> articleMapper.selectById(e)).collect(Collectors.toList())
                .stream()
                .map(e -> {
                    ArticleDTO articleDTO = new ArticleDTO();
                    BeanUtils.copyProperties(e, articleDTO);
                    return articleDTO;
                })
                .collect(Collectors.toList());
        return articleDTOS;
    }

}




