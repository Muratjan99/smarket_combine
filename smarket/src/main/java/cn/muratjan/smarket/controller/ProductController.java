package cn.muratjan.smarket.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.muratjan.smarket.common.AjaxResult;
import cn.muratjan.smarket.common.excaption.ProductException;
import cn.muratjan.smarket.pojo.Product;
import cn.muratjan.smarket.service.ProductService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Min;

/**
 * @author MRT
 * @date 2022/6/29 15:55
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Resource
    private ProductService productServiceImpl;

    /**
     * 获取我的所有商品
     * @return AjaxResult
     */
    @GetMapping("/getMyAllProduct")
    @SaCheckLogin
    public AjaxResult getProduct(@RequestParam int status,@RequestParam int page,@RequestParam int size) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("vendor_id", StpUtil.getLoginIdAsLong());
        if(status == 1 || status == 0){
            queryWrapper.eq("status", status);
        }
        return AjaxResult.success(productServiceImpl.page(new Page<>(page, size), queryWrapper));
    }
    /**
     * 获取所有商品
     * @return AjaxResult
     */
    @GetMapping("/getAllProduct")
    public AjaxResult getAllProduct(int page, int size) {
        Page<Product> productPage = new Page<>(page, size);
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 0);
        return AjaxResult.success(productServiceImpl.pageDeep(productPage, queryWrapper));
    }

    /**
     * 添加商品
     * @param product 商品信息
     * @return AjaxResult
     */
    @PostMapping("/add")
    @SaCheckLogin
    public AjaxResult addProduct(@RequestBody @Validated Product product) {
        long loginIdAsLong = StpUtil.getLoginIdAsLong();
        product.setVendorId(loginIdAsLong);
        boolean save = productServiceImpl.saveOrUpdate(product);
        if(!save) {
            throw new ProductException("添加商品失败");
        }
        return AjaxResult.success("添加商品成功");
    }

    /**
     * 删除商品
     * @param productId 商品id
     * @return AjaxResult
     */
    @GetMapping("/delete")
    @SaCheckLogin
    public AjaxResult deleteProduct(@RequestParam @Min(message = "最小值为1",value = 0) long productId) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("vendor_id", StpUtil.getLoginIdAsLong());
        queryWrapper.eq("product_id", productId);
        long count = productServiceImpl.count(queryWrapper);
        if(count == 0) {
            throw new ProductException("没有权限删除该商品");
        }
        boolean remove = productServiceImpl.removeById(productId);
        if(!remove) {
            throw new ProductException("删除商品失败");
        }
        return AjaxResult.success("删除商品成功");
    }

}
