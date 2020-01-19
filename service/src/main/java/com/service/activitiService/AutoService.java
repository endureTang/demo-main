package com.service.activitiService;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: endure
 * @create: 2020-01-17 11:26
 **/
@Service
public class AutoService implements JavaDelegate{
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void execute(DelegateExecution delegateExecution) {
        logger.info("money小于100，那我就自己决定了，autoService执行");
    }
}
