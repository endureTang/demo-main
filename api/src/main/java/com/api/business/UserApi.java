package com.api.business;

import com.model.generate.User;
import com.model.queryDto.UserQueryDto;
import com.model.responseDto.ResponseMsgDto;
import com.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
* @Description: 用户接口api
* @Author: endure
* @Date: 2019/12/31 
*/
@RestController
@RequestMapping(value = "user")
@Api(tags = "用户接口")
public class UserApi {
    private static Logger logger = LoggerFactory.getLogger(UserApi.class);

    @Resource
    public UserService userService;
    /**
     * @Description: 新增用户
     * @Param:
     * @return:
     * @Author: endure
     * @Date: 2019/12/31
     */
    @PostMapping(value = "addUser")
    @ApiOperation(value = "新增用户",notes = "可以选择传入id")
    @ApiModelProperty(value = "user")
    public ResponseMsgDto addUser(@RequestBody User user) {
        ResponseMsgDto responseMsgDto = new ResponseMsgDto(ResponseMsgDto.SUCCESS);
        try {
            userService.insertUser(user);
        } catch (Exception e) {
            logger.error("系统错误{}", e);
            responseMsgDto.setResultStatus(ResponseMsgDto.FAIL);
            responseMsgDto.setMsg("系统异常");
        }
        return responseMsgDto;
    }

    /**
     * @Description: 新增用户
     * @Param:
     * @return:
     * @Author: endure
     * @Date: 2019/12/31
     */
    @PostMapping(value = "queryList")
    @ApiOperation(value = "查询列表")
    @ApiModelProperty(value = "user")
    public ResponseMsgDto<UserQueryDto> queryList(@RequestBody(required = false) User user) {
        ResponseMsgDto responseMsgDto = new ResponseMsgDto(ResponseMsgDto.SUCCESS);
        try {
            List<UserQueryDto> userQueryDtos = userService.getAll(user);
            Map<String,List<UserQueryDto>> retMap = new HashMap<>();
            retMap.put("userList",userQueryDtos);
            responseMsgDto.setData(retMap);
        } catch (Exception e) {
            logger.error("系统错误{}", e);
            responseMsgDto.setResultStatus(ResponseMsgDto.FAIL);
            responseMsgDto.setMsg("系统异常");
        }
        return responseMsgDto;
    }
}
