package cn.muratjan.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author MRT
 * @date 2022/7/7 22:17
 */
@Data
@AllArgsConstructor
public class UserInfo {
    private String name;
    private String avatar;
    private String introduction;
    List<String> roles;
}
