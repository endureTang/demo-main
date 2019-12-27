package com.test;


import com.MqTaskApplication;
import com.alibaba.fastjson.JSONArray;
import com.mq.config.RabbitConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * 测试Mq消息发送
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MqTaskApplication.class)
public class TestMqDirectExchange {
    @Resource
    private RabbitConfig rabbitConfig;
    @Test
    public void sendEndureQueueMsg(){
        List lsit = rabbitConfig.getQuene();
        String s = JSONArray.toJSONString(lsit);
        System.out.println(s);
    }
}
