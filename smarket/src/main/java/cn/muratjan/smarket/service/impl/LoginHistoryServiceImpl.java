package cn.muratjan.smarket.service.impl;

import cn.muratjan.smarket.mapper.LoginHistoryMapper;
import cn.muratjan.smarket.pojo.LoginHistory;
import cn.muratjan.smarket.service.LoginHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author 17543
* @description 针对表【login_history】的数据库操作Service实现
* @createDate 2022-07-01 14:29:25
*/
@Service
public class LoginHistoryServiceImpl extends ServiceImpl<LoginHistoryMapper, LoginHistory>
    implements LoginHistoryService {

}




