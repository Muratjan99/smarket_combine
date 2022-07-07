package cn.muratjan.smarket.service.impl;

import cn.muratjan.smarket.service.IFastDFSService;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author MRT
 * @date 2022/7/3 0:00
 */
@Service
public class FastDFSServiceImpl implements IFastDFSService {
    @Resource
    private FastFileStorageClient storageClient;
    /**
     * 文件上传
     * @param multipartFile 文件
     * @return 文件路径
     * @throws Exception 异常
     */
    public String upload(MultipartFile multipartFile) throws Exception {
        String originalFilename = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1);
        StorePath storePath = this.storageClient.uploadImageAndCrtThumbImage(multipartFile.getInputStream(),multipartFile.getSize(), originalFilename, null);
        return storePath.getFullPath();
    }
}
