package com.service.activitiService;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description: 测试activiti监听器
 * @author: endure
 * @create: 2020-01-16 14:19
 **/
@Service(value = "activitiListener")
@Transactional(readOnly = true)
public class ActivitiListener implements TaskListener {
    private static Logger logger = LoggerFactory.getLogger(ActivitiListener.class);
    @Override
    public void notify(DelegateTask delegateTask) {
        //从数据库中查询出指定的班里人
        String assignee = "张三";
        //指定个人任务
        delegateTask.setAssignee(assignee);
        logger.info("指定个人任务监听器");
    }
}
