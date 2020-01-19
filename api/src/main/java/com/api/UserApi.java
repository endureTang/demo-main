package com.api;

import com.model.responseDto.ResponseMsgDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * @Description: 新增用户
     * @Param:
     * @return:
     * @Author: endure
     * @Date: 2019/12/31
     */
    @RequestMapping(value = "addUser")
    public ResponseMsgDto addUser() {
        ResponseMsgDto responseMsgDto = new ResponseMsgDto(ResponseMsgDto.SUCCESS);
        try {

        } catch (Exception e) {
            logger.error("系统错误{}", e);
            responseMsgDto.setResultStatus(ResponseMsgDto.FAIL);
            responseMsgDto.setMsg("系统异常");
        }
        return responseMsgDto;
    }
}
