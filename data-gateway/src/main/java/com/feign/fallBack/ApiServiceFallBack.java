package com.feign.fallBack;

import com.feign.ApiServiceFeign;
import com.model.generate.User;
import com.model.responseDto.ResponseMsgDto;
import org.springframework.stereotype.Component;

/**
 * @description: api接口feign调用失败处理类
 * @author: endure
 * @create: 2020-01-07 16:47
 **/
@Component
public class ApiServiceFallBack implements ApiServiceFeign {
    @Override
    public ResponseMsgDto addUser(User user) {
        ResponseMsgDto responseMsgDto = new ResponseMsgDto(ResponseMsgDto.FAIL);
        responseMsgDto.setMsg("服务暂时无法访问，请稍后重试");
        return responseMsgDto;
    }
}
