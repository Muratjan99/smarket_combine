package cn.muratjan.smarket.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author MRT
 * @date 2022/6/29 9:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserVO {

    private String nickname;
    private String avatar;
    private Integer sex;
    private LocalDate birthdate;
    private String phone;
    private String email;
}
