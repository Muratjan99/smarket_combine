package cn.muratjan.smarket.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author MRT
 * @date 2022/7/3 0:00
 */
public interface IFastDFSService {
    /**
     * 文件上传
     * @param multipartFile 文件
     * @return 文件路径
     * @throws Exception 异常
     */
    public String upload(MultipartFile multipartFile) throws Exception;
}
