package cn.knowei.springbootinit.controller;

import cn.knowei.springbootinit.common.BaseResponse;
import cn.knowei.springbootinit.common.PageRequest;
import cn.knowei.springbootinit.entity.Movies;
import cn.knowei.springbootinit.service.MoviesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: knowei
 * @Description:
 * @Date: Create in 14:45 2023-04-13
 */
@RestController
@Slf4j
@RequestMapping("movies")
@Api(value = "电影接口管理")
public class MoviesController {

    @Resource
    private MoviesService moviesService;

    @ApiOperation(value = "查找单个电影信息")
    @GetMapping("{id}")
    public BaseResponse<Movies> get(@PathVariable long id) {
        return moviesService.get(id);
    }

    @PostMapping("list/page")
    @ResponseBody
    public BaseResponse list(PageRequest page) {
        return moviesService.list(page);
    }
}
