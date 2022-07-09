package cn.muratjan.admin.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author MRT
 * @date 2022/7/7 13:43
 */
@Data
public class LoginVO implements Serializable {

    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;


}
