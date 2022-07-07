package cn.muratjan.smarket.controller;

import cn.muratjan.smarket.common.utils.JsonData;
import cn.muratjan.smarket.service.FaceLoginService;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author MRT
 * @date 2022/6/29 15:25
 */
@RestController
@RequestMapping("/Face")
public class FaceLoginController {

    @Resource
    private FaceLoginService faceLoginService;

    /**
     * 人脸登录
     * @return
     * @throws Exception
     */
    @RequestMapping("/face-register")
    @ResponseBody
    public String registerFace(@RequestBody  Map<String, StringBuffer> map) throws Exception {
        String str = faceLoginService.registerFace("1001", map.get("imagebast64"));
        return JSON.toJSONString(JsonData.success(str));
    }
}
