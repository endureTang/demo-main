package com.api;

import com.alibaba.fastjson.JSONArray;
import com.feign.MongodbService;
import com.model.exception.CommonException;
import com.model.mogodb.UserMongoData;
import com.model.responseDto.ResponseMsgDto;
import com.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "user")
public class UserApi {
    private static Logger logger = LoggerFactory.getLogger(UserApi.class);
    /**
     * 注入用户service
     */
    @Resource
    private UserService userService;
    /**
     * 注入MongoDB 网关feign
     */
    @Resource
    private MongodbService mongodbService;

    /**
     * 获取数据
     * @return
     */
    @RequestMapping(value = "getAll")
    public String getAll(@RequestParam(required = false) Map<String,Object> condition){
        try {
            List projectList = userService.getAll(condition);
            String s = JSONArray.toJSONString(projectList);
            return s;
        } catch (CommonException e) {
            return e.getMessage();
        }catch (Exception e){
            logger.error("系统错误"+e);
            return null;
        }
    }

    /**
     * 获取数据
     * @return
     */
    @RequestMapping(value = "pushUser")
    public ResponseMsgDto pushUser(String jsonArrayStr){
        try {
            logger.info("接收到推送数据"+jsonArrayStr);
            List<UserMongoData> mongoDatas = JSONArray.parseArray(jsonArrayStr, UserMongoData.class);
            mongodbService.saveBatch(mongoDatas);
            logger.info("接收成功");
            return new ResponseMsgDto(ResponseMsgDto.SUCCESS);
        } catch (Exception e) {
           logger.error("系统错误{}",e);
            return new ResponseMsgDto(ResponseMsgDto.FAIL);
        }
    }
}
