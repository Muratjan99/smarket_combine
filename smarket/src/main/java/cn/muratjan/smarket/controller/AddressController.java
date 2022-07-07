package cn.muratjan.smarket.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.muratjan.smarket.common.AjaxResult;
import cn.muratjan.smarket.common.excaption.AddressException;
import cn.muratjan.smarket.pojo.Address;
import cn.muratjan.smarket.pojo.Campus;
import cn.muratjan.smarket.service.AddressService;
import cn.muratjan.smarket.service.CampusService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author MRT
 * @date 2022/7/1 15:20
 */
@RestController
@RequestMapping("/address")
public class AddressController {

    @Resource
    private AddressService addressServiceImpl;
    @Resource
    private CampusService campusServiceImpl;



    /**
     * 添加地址
     * @return
     */
    @PostMapping("/add")
    @SaCheckLogin
    @Transactional(noRollbackFor = AddressException.class)
    public AjaxResult addAddress(@RequestBody @Validated Address address) {
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", StpUtil.getLoginIdAsLong());
        Long count = addressServiceImpl.count(queryWrapper);
        if (count >= 12) {
            return AjaxResult.error("最多只能添加12个地址");
        }
        if (address.getStatus() == 1  ){
            UpdateWrapper<Address> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("user_id", StpUtil.getLoginIdAsLong());
            updateWrapper.set("status", 0);
            addressServiceImpl.update(null, updateWrapper);
        }
        address.setUserId(StpUtil.getLoginIdAsLong());

        boolean save = addressServiceImpl.save(address);
        if(!save) {
            throw new RuntimeException("添加地址失败");
        }
        return AjaxResult.success();
    }

    /**
     * 获取用户的地址
     * @return AjaxResult
     */
    @GetMapping("/getUserAddress")
    @SaCheckLogin
    public AjaxResult getAddress() {
        Long userId = StpUtil.getLoginIdAsLong();
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<Address> list = addressServiceImpl.list(queryWrapper);
        return AjaxResult.success(list);
    }

    /**
     * 更新地址
     * @param address 地址
     * @return 返回
     */
    @PostMapping("/update")
    @SaCheckLogin
    public AjaxResult updateAddress(@RequestBody @Validated Address address) {
        Long count = addressServiceImpl.count(new QueryWrapper<Address>()
                .eq("addr_id", address.getAddrId())
                .eq("user_id", StpUtil.getLoginIdAsLong()));
        if(count == 0) {
            throw new AddressException("地址与用户不匹配");
        }
        if (address.getStatus() == 1  ){
            UpdateWrapper<Address> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("user_id", StpUtil.getLoginIdAsLong());
            updateWrapper.set("status", 0);
            boolean update = addressServiceImpl.update(null, updateWrapper);
            if(!update) {
                throw new AddressException("设置默认地址失败");
            }
        }
        address.setUserId(StpUtil.getLoginIdAsLong());
        UpdateWrapper<Address> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id", address.getUserId());
        updateWrapper.eq("addr_id", address.getAddrId());
        updateWrapper.set(StringUtils.isNotEmpty(address.getReceiver()), "receiver", address.getReceiver());
        updateWrapper.set(StringUtils.isNotEmpty(address.getReceiverPhone()), "receiver_phone", address.getReceiverPhone());
        updateWrapper.set(StringUtils.isNotEmpty(address.getAddr()), "addr", address.getAddr());
        updateWrapper.set(StringUtils.isNotEmpty(address.getCampus()), "campus", address.getCampus());
        updateWrapper.set(address.getStatus() == 1, "status", address.getStatus());
        boolean update = addressServiceImpl.update(null, updateWrapper);
        if(!update) {
            throw new AddressException("更新地址失败");
        }
        return AjaxResult.success();
    }

    /**
     * 删除地址
     * @param addrId 地址id
     * @return AjaxResult
     */
    @GetMapping("/delete")
    @SaCheckLogin
    public AjaxResult deleteAddress(@RequestParam @NotBlank(message = "id不得为空") String addrId) {
        long count = addressServiceImpl.count(new QueryWrapper<Address>()
                .eq("addr_id", addrId)
                .eq("user_id", StpUtil.getLoginIdAsLong()));
        if(count == 0) {
            throw new AddressException("地址与用户不匹配");
        }
        boolean remove = addressServiceImpl.remove(new QueryWrapper<Address>()
                .eq("addr_id", addrId)
                .eq("user_id", StpUtil.getLoginIdAsLong()));
        if(!remove) {
            throw new AddressException("删除地址失败");
        }
        return AjaxResult.success();
    }

    /**
     * 设置默认地址
     * @param addrId 地址id
     * @return AjaxResult
     */
    @GetMapping("/setDefaultAddress")
    @SaCheckLogin
    public AjaxResult setDefaultAddress(@RequestParam @NotBlank(message = "id不得为空") String addrId) {
        // 先判断是否存在该地址
        long count = addressServiceImpl.count(new QueryWrapper<Address>()
                .eq("addr_id", addrId)
                .eq("user_id", StpUtil.getLoginIdAsLong()));
        if(count == 0) {
            throw new AddressException("地址与用户不匹配");
        }
        // 再把其他地址设置为非默认地址
        UpdateWrapper<Address> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id", StpUtil.getLoginIdAsLong());
        updateWrapper.set("status", 0);
        boolean update = addressServiceImpl.update(null, updateWrapper);
        if(!update) {
            throw new AddressException("设置默认地址失败");
        }
        updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id", StpUtil.getLoginIdAsLong());
        updateWrapper.eq("addr_id", addrId);
        updateWrapper.set("status", 1);
        update = addressServiceImpl.update(null, updateWrapper);
        if(!update) {
            throw new AddressException("设置默认地址失败");
        }
        return AjaxResult.success();
    }

    @GetMapping("/getCampus")
    public AjaxResult getCampus(){
        List<Campus> list = campusServiceImpl.list();
        return AjaxResult.success(list);
    }
}
