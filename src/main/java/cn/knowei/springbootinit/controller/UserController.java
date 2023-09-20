package cn.knowei.springbootinit.controller;

import cn.knowei.springbootinit.common.BaseResponse;
import cn.knowei.springbootinit.common.ResultUtils;
import cn.knowei.springbootinit.entity.Vo.UserCodeVo;
import cn.knowei.springbootinit.entity.Vo.UserInfo;
import cn.knowei.springbootinit.service.WeixinService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: knowei
 * @Description:
 * @Date: Create in 13:28 2023-04-15
 */
@RestController
@RequestMapping("user")
@Api(tags = "用户管理")
public class UserController {

    @Resource
    private WeixinService weixinService;

    /**
     * 获取登入的session
     * @param
     * @return
     */
    @PostMapping("WeChatLogin")
    @ApiOperation("用户登入")
    public BaseResponse weChatLogin(@RequestBody UserCodeVo userCodeVo) {
        Map map = weixinService.getSsession(userCodeVo);
        return ResultUtils.success(map);
    }
}


