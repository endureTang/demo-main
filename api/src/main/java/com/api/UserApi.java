package com.api;

import com.alibaba.fastjson.JSONArray;
import com.feign.MongodbServiceFeign;
import com.feign.MqOrderServiceFeign;
import com.model.exception.CommonException;
import com.model.generate.User;
import com.model.mogodb.UserMongoData;
import com.model.responseDto.ResponseMsgDto;
import com.service.UserService;
import com.utils.RandomofNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/** 
* @Description: 用户接口api
* @Author: endure
* @Date: 2019/12/31 
*/
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
    private MongodbServiceFeign mongodbService;

    @Resource
    private MqOrderServiceFeign mqOrderService;

    /** 
    * @Description: 获取数据
    * @Param:  
    * @return:  
    * @Author: endure
    * @Date: 2019/12/31 
    */
    @RequestMapping(value = "getAll")
    public String getAll(@RequestParam(required = false) Map<String,Object> condition){
        try {
            List projectList = userService.getAll(condition);
            for (Object user :projectList) {
                String mqServiceMsg = mqOrderService.sendOrderMq( (User)user);
                logger.info("getAll feign调用mq返回消息："+mqServiceMsg);
            }
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
        * @Description: 推送数据到MongoDB
        * @Param:
        * @return:
        * @Author: zxb
        * @Date: 2019/12/31
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

    /**
     * @Description: 新增用户
     * @Param:
     * @return:
     * @Author: endure
     * @Date: 2019/12/31
     */
    @RequestMapping(value = "addUser")
    public ResponseMsgDto addUser(@RequestBody User user1) {
        try {
            User user = new User();
            user.setName(RandomofNames.generateName());
            user.setSex(1);
            try {
                userService.insertUser(user);
            }catch (DuplicateKeyException e){
                logger.info("主键插入重复");
            }catch (Exception e){
                logger.error(""+e);
            }
            return new ResponseMsgDto(ResponseMsgDto.SUCCESS);
        } catch (Exception e) {
            logger.error("系统错误{}", e);
            return new ResponseMsgDto(ResponseMsgDto.FAIL);
        }
    }
}
