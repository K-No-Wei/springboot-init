package cn.knowei.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.knowei.springbootinit.entity.User;
import cn.knowei.springbootinit.service.UserService;
import cn.knowei.springbootinit.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author zheng
* @description 针对表【kno_user】的数据库操作Service实现
* @createDate 2023-04-15 14:14:56
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




