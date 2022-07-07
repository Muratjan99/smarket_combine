package cn.muratjan.admin.common.handler;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author MRT
 * @date 2022/6/28 16:37
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        if(StpUtil.isLogin()){
            this.setFieldValByName("createBy", StpUtil.getLoginIdAsLong(),metaObject);
            this.setFieldValByName("updateBy",StpUtil.getLoginIdAsLong(),metaObject);
        }
        // TODO：创建者
        this.setFieldValByName("createTime", LocalDateTime.now(),metaObject);
        // TODO：更新者 时区问题
        this.setFieldValByName("updateTime", LocalDateTime.now(),metaObject);
        // 删除标志
        this.setFieldValByName("isDeleted",0,metaObject);
        // 版本号 -- > 乐观锁
        this.setFieldValByName("version",0,metaObject);
        // 状态
        this.setFieldValByName("status",0,metaObject);
        // 登录时间
        this.setFieldValByName("loginTime",LocalDateTime.now(),metaObject);

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 更新者信息
        // TODO: 这里需要更改成在线用户的ID
        if(StpUtil.isLogin()){
            this.setFieldValByName("updateBy",StpUtil.getLoginIdAsLong(),metaObject);
        }
        this.setFieldValByName("updateTime", LocalDateTime.now(),metaObject);
    }
}
