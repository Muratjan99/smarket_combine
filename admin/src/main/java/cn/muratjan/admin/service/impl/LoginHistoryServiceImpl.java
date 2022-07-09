package cn.muratjan.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.muratjan.admin.pojo.LoginHistory;
import cn.muratjan.admin.service.LoginHistoryService;
import cn.muratjan.admin.mapper.LoginHistoryMapper;
import org.springframework.stereotype.Service;

/**
* @author 17543
* @description 针对表【login_history】的数据库操作Service实现
* @createDate 2022-07-07 13:38:56
*/
@Service
public class LoginHistoryServiceImpl extends ServiceImpl<LoginHistoryMapper, LoginHistory>
    implements LoginHistoryService{

}




