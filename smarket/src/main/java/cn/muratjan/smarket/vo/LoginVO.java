package cn.muratjan.smarket.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author MRT
 * @date 2022/6/28 14:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginVO {
    @NotBlank(message = "用户名不得为空")
    private String username;
    @NotBlank(message = "密码不得为空")
    private  String password;

}
