package cn.knowei.springbootinit.entity.Vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: knowei
 * @Description:
 * @Date: Create in 15:07 2023-04-15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    @JsonFormat(shape = JsonFormat.Shape.STRING )
    private Long userId;
    private String nickName;
    private String avatarUrl;
}
