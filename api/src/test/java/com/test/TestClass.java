package com.test;


import com.ApiApplication;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class TestClass {
    private static Logger logger = LoggerFactory.getLogger(TestClass.class);
    @Resource
    RuntimeService runtimeService;
    @Resource
    TaskService taskService;
    @Resource
    RepositoryService repositoryService;

    @Test
    public void test(){
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("SimpleProcess");
        logger.info(instance.getId());

        List<Task> list = taskService.createTaskQuery().taskAssignee("admin").list();
        for (Task task : list) {
            logger.info(task.getName());
        }
    }
}
