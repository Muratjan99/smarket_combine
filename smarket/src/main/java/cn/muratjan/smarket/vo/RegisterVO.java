package cn.muratjan.smarket.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author MRT
 * @date 2022/6/28 14:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterVO {
    @NotBlank(message = "用户名不得为空")
    private String username;
    @NotBlank(message = "密码不得为空")
    private String password;
    private Integer sex;
    private String phone;
    private String email;

}
