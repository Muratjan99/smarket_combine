package cn.muratjan.admin.common.utils;

import com.baidu.aip.face.AipFace;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;

/**
 * @author PDX
 * @website https://blog.csdn.net/Gaowumao
 * @Date 2022-05-08 17:05
 * @Description
 */
@Component
public class BaiduAiUtils {

    @Value("${ai.appId}")
    private String APP_ID;
    @Value("${ai.apiKey}")
    private String API_KEY;
    @Value("${ai.secretKey}")
    private String SECRET_KEY;
    @Value("${ai.imageType}")
    private String IMAGE_TYPE;
    @Value("${ai.groupId}")
    private String groupId;



    private AipFace client;

    private HashMap<String,String> map = new HashMap<>();

    private BaiduAiUtils(){
        map.put("quality_control","NORMAL");//图片质量
        map.put("liveness_control","LOW");//活体检测
    }

    @PostConstruct
    public void init(){
        client = new AipFace(APP_ID,API_KEY,SECRET_KEY);
    }

    /**
     * 人脸注册，将用户照片存入人脸库中
     * @param userId
     * @param image
     * @return
     */
    public Boolean faceRegister(String userId,String image){
        //人脸注册
        JSONObject res = client.addUser(image, IMAGE_TYPE, groupId, userId, map);
        Integer errorCode = res.getInt("error_code");
        return errorCode == 0? true : false;
    }

    /**
     * 人脸更新，更新人脸库中的用户照片
     * @param userId
     * @param image
     * @return
     */
    public Boolean faceUpdate(String userId,String image){
        //人脸更新
        JSONObject res = client.updateUser(image, IMAGE_TYPE, groupId, userId, map);
        Integer errorCode = res.getInt("error_code");
        return errorCode == 0 ? true : false;
    }

    /**
     * 人脸检测。判断上传的图片中是否具有面部信息
     * @param image
     * @return
     */
    public Boolean faceCheck(String image){
        JSONObject res = client.detect(image, IMAGE_TYPE, map);
        if (res.has("error_code") && res.getInt("error_code") == 0){
            JSONObject resultObject = res.getJSONObject("result");
            Integer faceNum = resultObject.getInt("face_num");
            return faceNum == 1? true : false;
        }else {
            return false;
        }
    }

    /**
     * 人脸查找：查找人脸库中最相似的人脸并返回数据
     *          处理：用户的匹配得分（score）大于80分，即可认为是同一个人
     * @param image
     * @return
     */
    public String faceSearch(String image){
        JSONObject res = client.search(image, IMAGE_TYPE, groupId, map);
        if (res.has("error_code") && res.getInt("error_code") == 0){
            JSONObject result = res.getJSONObject("result");
            JSONArray userList = result.getJSONArray("user_list");
            if (userList.length() > 0){
                JSONObject user = userList.getJSONObject(0);
                double score = user.getDouble("score");
                if (score > 80){
                    return user.getString("user_id");
                }
            }
        }
        return null;
    }

}