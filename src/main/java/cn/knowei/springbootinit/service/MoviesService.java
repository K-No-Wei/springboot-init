package cn.knowei.springbootinit.service;

import cn.knowei.springbootinit.common.BaseResponse;
import cn.knowei.springbootinit.common.PageRequest;
import cn.knowei.springbootinit.entity.Movies;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author zheng
* @description 针对表【movies】的数据库操作Service
* @createDate 2023-04-13 14:44:52
*/
public interface MoviesService extends IService<Movies> {

    BaseResponse<Movies> get(long id);

    BaseResponse list(PageRequest page);
}
