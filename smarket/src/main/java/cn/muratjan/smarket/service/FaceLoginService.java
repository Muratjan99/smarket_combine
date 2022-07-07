package cn.muratjan.smarket.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author MRT
 * @date 2022/6/29 14:59
 */
public interface FaceLoginService {
    /**
     * 人脸搜索
     * @param imagebast64 图片base64
     * @return 返回
     * @throws Exception
     */
    public String loginByFace(StringBuffer imagebast64) throws Exception;

    /**
     * 人脸注册
     * @param userId 用户id
     * @param imagebast64 图片base64
     * @return 返回
     */
    public String registerFace(String userId, StringBuffer imagebast64);
}
