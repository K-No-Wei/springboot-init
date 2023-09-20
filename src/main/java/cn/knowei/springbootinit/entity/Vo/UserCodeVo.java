package cn.knowei.springbootinit.entity.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: knowei
 * @Description:
 * @Date: Create in 15:17 2023-04-15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCodeVo {

    private UserInfo userInfo;
    private String code;
}
