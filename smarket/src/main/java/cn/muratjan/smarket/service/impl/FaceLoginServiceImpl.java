package cn.muratjan.smarket.service.impl;

import cn.muratjan.smarket.common.utils.BaiduAiUtils;
import cn.muratjan.smarket.service.FaceLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author MRT
 * @date 2022/6/29 14:59
 */
@Service
public class FaceLoginServiceImpl implements FaceLoginService {
    @Autowired
    private BaiduAiUtils baiduAiUtils;

    /**
     * 人脸搜索
     * @param imagebast64 图片base64
     * @return 返回
     * @throws Exception
     */
    public String loginByFace(StringBuffer imagebast64) throws Exception{
        String image = imagebast64.substring(imagebast64.indexOf(",") + 1, imagebast64.length());
        String userId = baiduAiUtils.faceSearch(image);
        return userId;
    }
    /**
     * 人脸注册
     * @param userId 用户id
     * @param imagebast64 图片base64
     * @param request 请求
     * @return 返回
     */
    public String registerFace(String userId, StringBuffer imagebast64){
        String image = imagebast64.substring(imagebast64.indexOf(",") + 1, imagebast64.length());
        Boolean isSuccess = baiduAiUtils.faceRegister(userId, image);
        if (isSuccess){
//            request.getSession().setAttribute("RuserId","1001");
//            request.getSession().setAttribute("Rusername","派大星的朋友");
            return "ok";
        }else {
//            request.getSession().setAttribute("RuserId","未知");
//            request.getSession().setAttribute("Rusername","你不是派大星的朋友");
            return "no";
        }
    }
}
