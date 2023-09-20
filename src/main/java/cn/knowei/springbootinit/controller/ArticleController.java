package cn.knowei.springbootinit.controller;

import cn.hutool.http.HttpUtil;
import cn.knowei.springbootinit.common.BaseResponse;
import cn.knowei.springbootinit.common.ErrorCode;
import cn.knowei.springbootinit.common.PageRequest;
import cn.knowei.springbootinit.common.ResultUtils;
import cn.knowei.springbootinit.entity.Article;
import cn.knowei.springbootinit.entity.UserArticleBrowse;
import cn.knowei.springbootinit.entity.UserArticleCollect;
import cn.knowei.springbootinit.entity.dto.ArticleDTO;
import cn.knowei.springbootinit.service.ArticleService;
import cn.knowei.springbootinit.service.UserArticleBrowseService;
import cn.knowei.springbootinit.service.UserArticleCollectService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Result;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: knowei
 * @Description:
 * @Date: Create in 23:17 2023-04-13
 */
@RestController
@RequestMapping("article")
@Api(tags = "文章接口")
public class ArticleController {
    @Resource
    private ArticleService articleService;

    @Resource
    private UserArticleBrowseService userArticleBrowseService;

    @Resource
    private UserArticleCollectService collectService;

    @GetMapping("hot")
    @ApiOperation("热门文章")
    public BaseResponse getHotArticle() {
        return articleService.getHotArticle();
    }



    @GetMapping("list")
    @ApiOperation("文章列表")
    public BaseResponse getList(PageRequest page) {
        List<ArticleDTO> articleDTOS = articleService.getList(page);

        return ResultUtils.success(articleDTOS);
    }

    @GetMapping("{id}")
    @ApiOperation("单篇文章")
    public BaseResponse getOne(@PathVariable Long id) {
        return ResultUtils.success(articleService.getById(id));
    }

    @GetMapping("view/{id}")
    @ApiOperation("浏览文章")
    public BaseResponse lookById(@PathVariable Long id,HttpServletRequest request) {

        boolean flag = articleService.articleView(id, request);
        return ResultUtils.success(flag);
    }

    @GetMapping("like/{id}")
    @ApiOperation("点赞")
    public BaseResponse like(@PathVariable Long id) {
        boolean flag = articleService.like(id);
        return ResultUtils.success(flag);
    }

    @GetMapping("browList/{userId}")
    @ApiOperation("获取浏览历史")
    public BaseResponse getBroList(@PathVariable Long userId) {
        List<UserArticleBrowse> list = userArticleBrowseService.list(new LambdaQueryWrapper<UserArticleBrowse>().eq(UserArticleBrowse::getUserId, userId).orderByDesc(UserArticleBrowse::getBrowse));
        List<Article> articles = list.stream().map(e -> articleService.getById(e.getArticleId())).collect(Collectors.toList());
        return ResultUtils.success(articles);
    }

    @GetMapping("collect/{id}")
    @ApiOperation("收藏文章")
    public BaseResponse collectById(@PathVariable Long id, HttpServletRequest request) {
        return articleService.collectById(id, request);
    }

    @GetMapping("collectList/{userId}")
    @ApiOperation("获取收藏文章列表")
    public BaseResponse getCollectList(@PathVariable Long userId) {
        List<UserArticleCollect> collectList = collectService.list(new LambdaQueryWrapper<UserArticleCollect>()
                .eq(UserArticleCollect::getUserId, userId));
        return ResultUtils.success(collectList);
    }

    @GetMapping("collect/get/{articleId}")
    @ApiOperation("获取单个收藏记录")
    public BaseResponse getById(@PathVariable Long articleId, HttpServletRequest request) {
        return articleService.getCollectByArticleId(articleId, request);
    }

    @DeleteMapping("collect/{articleId}")
    @ApiOperation("取消收藏记录")
    public BaseResponse deleteById(@PathVariable Long articleId, HttpServletRequest request) {
        return articleService.deleteByArticleId(articleId, request);
    }
}
