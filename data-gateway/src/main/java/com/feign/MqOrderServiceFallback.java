package com.feign;

import com.model.generate.User;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: endure
 * @create: 2019-12-31 14:21
 **/
@Component
public class MqOrderServiceFallback implements MqOrderService{
    @Override
    public String sendOrderMq(User user) {
        System.out.println("调用消息接口失败，对其进行降级处理！");
        return "消息接口繁忙，请稍后重试！";
    }
}
