package cn.muratjan.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.muratjan.admin.pojo.Address;
import cn.muratjan.admin.service.AddressService;
import cn.muratjan.admin.mapper.AddressMapper;
import org.springframework.stereotype.Service;

/**
* @author 17543
* @description 针对表【address】的数据库操作Service实现
* @createDate 2022-07-07 13:38:56
*/
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address>
    implements AddressService{

}




