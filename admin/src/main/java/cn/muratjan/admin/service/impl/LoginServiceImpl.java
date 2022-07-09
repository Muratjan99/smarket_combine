package cn.muratjan.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.muratjan.admin.pojo.Login;
import cn.muratjan.admin.service.LoginService;
import cn.muratjan.admin.mapper.LoginMapper;
import org.springframework.stereotype.Service;

/**
* @author 17543
* @description 针对表【login】的数据库操作Service实现
* @createDate 2022-07-07 13:38:56
*/
@Service
public class LoginServiceImpl extends ServiceImpl<LoginMapper, Login>
    implements LoginService{

}




