package cn.muratjan.smarket.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.muratjan.smarket.common.AjaxResult;
import cn.muratjan.smarket.pojo.Category;
import cn.muratjan.smarket.service.ICatagoryService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author MRT
 * @date 2022/6/30 16:07
 */
@RestController
@RequestMapping("/catagory")
public class CatagoryController {

    @Resource
    private ICatagoryService catagoryServiceImpl;

    @PostMapping("/add")
    @SaCheckLogin
    public AjaxResult addCatagory(@RequestBody @Validated Category category) {
        boolean save = catagoryServiceImpl.save(category);
        if(save) {
            return AjaxResult.success("添加成功");
        }
        return AjaxResult.error("添加失败");
    }


    @GetMapping("/getAll")
    public AjaxResult getAll(){
        List<Category> catagories = catagoryServiceImpl.list();
        return AjaxResult.success(catagories);
    }
}
