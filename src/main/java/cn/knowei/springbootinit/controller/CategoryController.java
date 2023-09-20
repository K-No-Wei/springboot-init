package cn.knowei.springbootinit.controller;

import cn.knowei.springbootinit.common.BaseResponse;
import cn.knowei.springbootinit.common.ResultUtils;
import cn.knowei.springbootinit.entity.Category;
import cn.knowei.springbootinit.entity.dto.ArticleDTO;
import cn.knowei.springbootinit.service.ArticleCategoryService;
import cn.knowei.springbootinit.service.ArticleService;
import cn.knowei.springbootinit.service.CategoryService;
import cn.knowei.springbootinit.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: knowei
 * @Description:
 * @Date: Create in 15:20 2023-04-14
 */
@RestController
@RequestMapping("tag")
@Api(tags = "标签管理")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @Resource
    private ArticleCategoryService articleCategoryService;

    @GetMapping("list")
    @ApiOperation("获取所有分类")
    public BaseResponse list() {
        List<Category> categories = categoryService.getList();

        return ResultUtils.success(categories);
    }

    @GetMapping("article/{id}")
    @ApiOperation("获取分类下的文章")
    public BaseResponse getArticle(@PathVariable Long id) {
        List<ArticleDTO> articleDTOS = articleCategoryService.getListByCateId(id);
        return ResultUtils.success(articleDTOS);
    }
}
