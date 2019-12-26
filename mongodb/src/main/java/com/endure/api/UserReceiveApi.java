package com.endure.api;

import com.endure.service.PushCaseService;
import com.model.mogodb.UserMongoData;
import com.model.responseDto.ResponseMsgDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户信息接收
 * @author 
 */
@RestController
@RequestMapping("/userReceive")
public class UserReceiveApi {
    private static Logger logger = LoggerFactory.getLogger(UserReceiveApi.class);
    
    @Resource
    private PushCaseService pushCaseService;
    /**
     * 批量保存
     */
    @RequestMapping("/saveBatch")
    public ResponseMsgDto saveBatch(@RequestBody List<UserMongoData> caseMongoDatas) {
    	ResponseMsgDto resMsg = new ResponseMsgDto();
    	try {
        	pushCaseService.saveBatch(caseMongoDatas);
        	logger.info("逾期催收案件保存MongoDB成功");
		} catch (Exception e) {
			logger.info("逾期催收案件保存MongoDB出错，错误信息："+e);
			resMsg.setResultStatus(ResponseMsgDto.FAIL);
			resMsg.setMsg(e.getMessage());
		}
    	return resMsg;
    }
    
    /**
     * 获取列表
     */
    @RequestMapping("/selectList")
    public List<UserMongoData> queryCaseList(@RequestBody String name) {
    	List<UserMongoData> caseMongoDatas = pushCaseService.queryList(name);
    	return caseMongoDatas;
    }
}
