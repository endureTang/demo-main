package com.api;

import com.alibaba.fastjson.JSONArray;
import com.model.responseDto.ResponseMsgDto;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
* @Description: 用户接口api
* @Author: endure
* @Date: 2019/12/31 
*/
@RestController
@RequestMapping(value = "activiti")
public class ActivitiApi {
    private static Logger logger = LoggerFactory.getLogger(ActivitiApi.class);
    @Resource
    private RuntimeService runtimeService;
    @Resource
    private TaskService taskService;

    /** 
    * @Description: 启动流程引擎 
    * @Param:  
    * @return:  
    * @Author: endure
    * @Date: 2020/1/17 
    */
    @RequestMapping(value = "init")
    public String initActivitiEngine(){
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("testProcess");
        logger.info("testProcess流程已经启动，实例id为:{}",instance.getId());
        return "testProcess流程已经启动，实例id为："+instance.getId();
    }
    /**
     * @Description: 获取用户任务列表
     * @Param:
     * @return:
     * @Author: endure
     * @Date: 2020/1/17
     */
    @RequestMapping(value = "getTaskList")
    public String getTaskList(@RequestParam(value = "name") String name){
        List<Task> list = taskService.createTaskQuery().taskAssignee(name).list();
        Map<String,Object> retMap = new HashMap<>();
        List<Map<String,Object>> retList = new ArrayList<>();
        for (Task task : list) {
            retMap.put("taskName", task.getName());
            retMap.put("taskId", task.getId());
            retList.add(retMap);
        }
        return JSONArray.toJSONString(retList);
    }
    /**
     * @Description: 执行任务
     * @Param:
     * @return:
     * @Author: endure
     * @Date: 2019/12/31
     */
    @RequestMapping(value = "checkMoney")
    public ResponseMsgDto addUser(@RequestParam(value = "money",required = false) String money,@RequestParam(value = "taskId") String taskId) {
        ResponseMsgDto responseMsgDto = new ResponseMsgDto(ResponseMsgDto.SUCCESS);
        try {
            logger.info("taskId:{}开始执行",taskId);
            if(StringUtils.isEmpty(taskId)){
                logger.info("taskId不能为空");
                responseMsgDto.setResultStatus(ResponseMsgDto.FAIL);
                responseMsgDto.setMsg("taskId不能为空");
                return responseMsgDto;
            }
            Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
            if(task == null){
                logger.info("任务已经执行，或者未找到");
                responseMsgDto.setResultStatus(ResponseMsgDto.FAIL);
                responseMsgDto.setMsg("任务已经执行，或者未找到");
                return responseMsgDto;
            }
            if(StringUtils.isEmpty(money)){
                logger.info("没有money参数，那应该是审核任务咯");
                taskService.complete(taskId);
            }else{
                logger.info("money参数值为：{}，那应该是价格判断任务",money);
                Map<String,Object> variables = new HashMap<>();
                variables.put("money", money);
                taskService.complete(taskId,variables);
            }

            logger.info("taskId:{}执行完毕",taskId);
        } catch (Exception e) {
            logger.error("系统错误{}", e);
            responseMsgDto.setResultStatus(ResponseMsgDto.FAIL);
            responseMsgDto.setMsg("系统异常");
        }
        return responseMsgDto;
    }
}
