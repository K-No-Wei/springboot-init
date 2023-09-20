package cn.knowei.springbootinit.service;

import cn.knowei.springbootinit.common.BaseResponse;
import cn.knowei.springbootinit.common.PageRequest;
import cn.knowei.springbootinit.entity.Article;
import cn.knowei.springbootinit.entity.dto.ArticleDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author zheng
* @description 针对表【kno_article】的数据库操作Service
* @createDate 2023-04-13 23:17:26
*/
public interface ArticleService extends IService<Article> {

    BaseResponse getHotArticle();

    List<ArticleDTO> getList(PageRequest page);

    boolean like(Long id);

    boolean articleView(Long id, HttpServletRequest request);

    BaseResponse collectById(Long id, HttpServletRequest request);

    BaseResponse getCollectByArticleId(Long articleId, HttpServletRequest request);

    BaseResponse deleteByArticleId(Long articleId, HttpServletRequest request);
}
