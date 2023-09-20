package cn.knowei.springbootinit.service;

import cn.knowei.springbootinit.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author zheng
* @description 针对表【kno_category】的数据库操作Service
* @createDate 2023-04-13 23:17:26
*/
public interface CategoryService extends IService<Category> {

    List<Category> getList();
}
