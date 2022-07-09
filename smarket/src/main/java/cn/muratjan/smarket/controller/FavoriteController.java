package cn.muratjan.smarket.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.muratjan.smarket.common.AjaxResult;
import cn.muratjan.smarket.common.excaption.FavoriteException;
import cn.muratjan.smarket.pojo.Favorite;
import cn.muratjan.smarket.pojo.Product;
import cn.muratjan.smarket.service.FavoriteService;
import cn.muratjan.smarket.service.ProductService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.Min;

/**
 * @author MRT
 * @date 2022/7/4 23:09
 */
@RestController
@RequestMapping("/favorite")
public class FavoriteController {

    @Resource
    private FavoriteService favoriteServiceImpl;

    @Resource
    private ProductService productServiceImpl;

    /**
     * 添加收藏
     * @return AjaxResult
     */
    @GetMapping("/add")
    @SaCheckLogin
    public AjaxResult addFavorite(@RequestParam @Min(message = "商品ID不合法",value = 0)  Long productId) {
        Favorite favorite = new Favorite();
        favorite.setProductId(productId);
        Product byId = productServiceImpl.getById(productId);
        if (byId == null) {
            throw new FavoriteException("商品不存在");
        }
        QueryWrapper<Favorite> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productId);
        queryWrapper.eq("user_id", StpUtil.getLoginIdAsLong());
        Favorite byId1 = favoriteServiceImpl.getOne(queryWrapper);
        if (byId1 != null) {
            throw new FavoriteException("已收藏");
        }
        boolean save = favoriteServiceImpl.save(favorite);
        if(!save){
            throw new FavoriteException("添加收藏失败");
        }
        return AjaxResult.success("添加收藏成功");
    }

    /**
     * 检查是否收藏
     * @param productId 商品ID
     * @return AjaxResult
     */
    @GetMapping("/check")
    @SaCheckLogin
    public AjaxResult checkAdd(@RequestParam @Min(message = "商品ID不合法",value = 0)  Long productId){
        QueryWrapper<Favorite> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productId);
        queryWrapper.eq("create_by", StpUtil.getLoginIdAsLong());
        long count = favoriteServiceImpl.count(queryWrapper);
        if (count > 0) {
            throw new FavoriteException("已收藏");
        }
        return AjaxResult.success("ok");
    }
    /**
     * 查询
     * @param page 当前页
     * @param size 每页显示条数
     * @return AjaxResult
     */
    @GetMapping("/getMyFavorite")
    @SaCheckLogin
    public AjaxResult getFavorite(@RequestParam @Min(message = "商品ID不合法",value = 0)int page,int size) {
        Page<Favorite> favoritePage = new Page<>(page,size);
        return AjaxResult.success(favoriteServiceImpl.pageDeep(favoritePage));
    }

    /**
     * 删除收藏
     * @param favId 收藏ID
     * @return AjaxResult
     */
    @GetMapping("/delete")
    @SaCheckLogin
    public AjaxResult deleteFavorite(@RequestParam @Min(message = "商品ID不合法",value = 0)  Long favId) {
        QueryWrapper<Favorite> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("fav_id",favId);
        queryWrapper.eq("create_by", StpUtil.getLoginIdAsLong());
        boolean remove = favoriteServiceImpl.remove(queryWrapper);
        if(!remove){
            throw new FavoriteException("删除收藏失败");
        }
        return AjaxResult.success("删除收藏成功");
    }
}
