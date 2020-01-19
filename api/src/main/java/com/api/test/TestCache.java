package com.api.test;

import com.alibaba.fastjson.JSONArray;
import com.model.generate.User;
import com.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/** 
* @Description: 用户接口api
* @Author: endure
* @Date: 2019/12/31 
*/
@RestController
@RequestMapping(value = "test")
public class TestCache {
    private static Logger logger = LoggerFactory.getLogger(TestCache.class);
    /**
     * 注入用户service
     */
    @Resource
    private UserService userService;

    /**
     * @Description: 测试缓存查询
     * @Param:
     * @return:
     * @Author: endure
     * @Date: 2019/12/31
     */
    @PostMapping(value = "testCache")
    public String testCache(){
        List<User> user = userService.getByname("张三");
        logger.info("查询消息"+JSONArray.toJSONString(user)+"222");
        return JSONArray.toJSONString(user);
    }
}
