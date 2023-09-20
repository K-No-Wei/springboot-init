package cn.knowei.springbootinit.service;

import cn.knowei.springbootinit.entity.ArticleCategory;
import cn.knowei.springbootinit.entity.dto.ArticleDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author zheng
* @description 针对表【kno_article_category】的数据库操作Service
* @createDate 2023-04-13 23:17:26
*/
public interface ArticleCategoryService extends IService<ArticleCategory> {

    List<ArticleDTO> getListByCateId(Long id);
}
