package cn.muratjan.smarket.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.muratjan.smarket.common.AjaxResult;
import cn.muratjan.smarket.common.excaption.FootprintException;
import cn.muratjan.smarket.pojo.Footprint;
import cn.muratjan.smarket.service.FootprintService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

/**
 * @author MRT
 * @date 2022/7/4 23:48
 */
@RequestMapping("/footprint")
@RestController
public class FootprintController {

    @Resource
    private FootprintService footprintServiceImpl;

    /**
     * 添加足迹
     * @param productId 商品ID
     * @return AjaxResult
     */
    @GetMapping("/add")
    @SaCheckLogin
    public AjaxResult addFootprint(@RequestParam @Validated @Min(value = 1,message = "商品ID不合法") Long productId) {
        QueryWrapper<Footprint> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productId);
        queryWrapper.eq("create_by", StpUtil.getLoginIdAsLong());
        long count = footprintServiceImpl.count(queryWrapper);
        if (count > 0) {
            return AjaxResult.error("已经添加过足迹");
        }
        Footprint footprint = new Footprint();
        footprint.setProductId(productId);
        footprint.setCreateBy(StpUtil.getLoginIdAsLong());
        footprint.setBrowseDate(LocalDateTime.now());
        boolean save = footprintServiceImpl.save(footprint);
        if(!save){
            throw new FootprintException("添加足迹失败");
        }
        return AjaxResult.success("添加足迹成功");
    }

    /**
     * 查询足迹
     * @param page 当前页
     * @param size 每页显示条数
     * @return AjaxResult
     */
    @GetMapping("/getMyFootprint")
    @SaCheckLogin
    public AjaxResult getFootprint(@RequestParam  int page,int size) {
        Page<Footprint> footprintPage = new Page<>(page,size);
        return AjaxResult.success(footprintServiceImpl.pageDeep(footprintPage));
    }

    /**
     * 删除足迹
     * @param footprintId   足迹ID
     * @return AjaxResult
     */
    @GetMapping("/delete")
    @SaCheckLogin
    public AjaxResult deleteFootprint(@RequestParam @Min(value = 1,message = "足迹ID不合法") Long footprintId) {
        boolean remove = footprintServiceImpl.removeById(footprintId);
        if(!remove){
            throw new FootprintException("删除足迹失败");
        }
        return AjaxResult.success("删除足迹成功");
    }
}
