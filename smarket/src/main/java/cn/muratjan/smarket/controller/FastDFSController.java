package cn.muratjan.smarket.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.muratjan.smarket.common.AjaxResult;
import cn.muratjan.smarket.common.excaption.FastDFSException;
import cn.muratjan.smarket.pojo.Photo;
import cn.muratjan.smarket.service.IFastDFSService;
import cn.muratjan.smarket.service.ITuserService;
import cn.muratjan.smarket.service.PhotoService;
import cn.muratjan.smarket.vo.UpdateUserVO;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author MRT
 * @date 2022/7/2 23:56
 */
@RestController
@RequestMapping("/fastdfs")
public class FastDFSController {
    @Resource
    private IFastDFSService fastDFSServiceImpl;
    @Resource
    private PhotoService photoServiceImpl;
    @Resource
    private  ITuserService tuserServiceImpl;

    private final String ip = "http://115.154.88.174:8888/";
    /**
     * 文件上传 headers必须是form-data否则无法上传文件
     * @param file 文件
     * @return 文件路径
     * @throws Exception 异常
     */
    @RequestMapping(value = "/uploadAvatars", headers = "content-type=multipart/form-data", method = RequestMethod.POST)
    @SaCheckLogin
    public AjaxResult uploadAvatars(@RequestParam("file") MultipartFile file) {
        // 判断文件是否为空 并且获取后缀
        String fileNameExtension = Objects.requireNonNull(FilenameUtils.getExtension(file.getOriginalFilename())).toLowerCase();
        // 后缀检测
        if (!(fileNameExtension.equals("png") || fileNameExtension.equals("jpg") || fileNameExtension.equals("jpeg"))){
            throw new FastDFSException("文件格式有误");
        }
        // 上传文件大小检测
        if(file.getSize() > 1024 * 1024 * 5){
            throw new FastDFSException("文件大小超过5Mb，上传失败！");
        }
        String result;
        try {
            String path = fastDFSServiceImpl.upload(file);
            if (!StringUtils.isEmpty(path)) {
                result = path;
            } else {
                throw new FastDFSException("上传失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new FastDFSException("服务异常");
        }
        // 将文件路径保存到数据库
        Photo photo = new Photo();
        photo.setAvatarPath(result);
        boolean save = photoServiceImpl.save(photo);
        if(!save) {
            throw new FastDFSException("保存数据库失败");
        }
        photo.setIp(ip);
        UpdateUserVO updateUserVO = new UpdateUserVO();
        updateUserVO.setAvatar(ip + photo.getAvatarPath());
        tuserServiceImpl.updateCurrentUserInfo(updateUserVO);
        return AjaxResult.success(photo);
    }
    /**
     * 文件上传 headers必须是form-data否则无法上传文件
     * @param file 文件
     * @return 文件路径
     * @throws Exception 异常
     */
    @RequestMapping(value = "/uploadPhoto", headers = "content-type=multipart/form-data", method = RequestMethod.POST)
    @SaCheckLogin
    public AjaxResult uploadPhoto(@RequestParam("file") MultipartFile file) {
        // 判断文件是否为空 并且获取后缀
        String fileNameExtension = Objects.requireNonNull(FilenameUtils.getExtension(file.getOriginalFilename())).toLowerCase();
        // 后缀检测
        if (!(fileNameExtension.equals("png") || fileNameExtension.equals("jpg") || fileNameExtension.equals("jpeg"))){
            throw new FastDFSException("文件格式有误");
        }
        // 上传文件大小检测
        if(file.getSize() > 1024 * 1024 * 5){
            throw new FastDFSException("文件大小超过5Mb，上传失败！");
        }
        String result;
        try {
            String path = fastDFSServiceImpl.upload(file);
            if (!StringUtils.isEmpty(path)) {
                result = path;
            } else {
                throw new FastDFSException("上传失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new FastDFSException("服务异常");
        }
        // 将文件路径保存到数据库
        Photo photo = new Photo();
        photo.setAvatarPath(result);
        boolean save = photoServiceImpl.save(photo);
        if(!save) {
            throw new FastDFSException("保存数据库失败");
        }
        photo.setIp(ip);
        UpdateUserVO updateUserVO = new UpdateUserVO();
        updateUserVO.setAvatar(ip + photo.getAvatarPath());
        return AjaxResult.success(photo);
    }
}
