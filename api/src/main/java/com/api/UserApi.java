package com.api;

import com.model.generate.User;
import com.model.responseDto.ResponseMsgDto;
import com.service.UserService;
import com.utils.redis.RedisLock;
import com.utils.string.RandomofNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/** 
* @Description: 用户接口api
* @Author: endure
* @Date: 2019/12/31 
*/
@RestController
@RequestMapping(value = "user")
public class UserApi {
    private static Logger logger = LoggerFactory.getLogger(UserApi.class);

    @Resource
    private UserService userService; //注入用户service
    @Resource
    private RedisLock redisLock; //注入redis加锁服务

    /**
     * @Description: 新增用户
     * @Param:
     * @return:
     * @Author: endure
     * @Date: 2019/12/31
     */
    @RequestMapping(value = "addUser")
    public ResponseMsgDto addUser(@RequestBody User user1) {
        String redisKey = "addUser";
        String token = null;
        ResponseMsgDto responseMsgDto = new ResponseMsgDto(ResponseMsgDto.SUCCESS);
        try {
            User user = new User();
            user.setName(RandomofNames.generateName());
            user.setSex(1);
            token = redisLock.lock(redisKey, 10000, 11000);
            if(token != null) {
                userService.insertUser(user);
            } else {
                responseMsgDto.setResultStatus(ResponseMsgDto.FAIL);
                responseMsgDto.setMsg("返回队列继续排队");
            }
        } catch (Exception e) {
            logger.error("系统错误{}", e);
            responseMsgDto.setResultStatus(ResponseMsgDto.FAIL);
            responseMsgDto.setMsg("系统异常");
        }finally {
            redisLock.unlock(redisKey, token);
            return responseMsgDto;
        }
    }
}
