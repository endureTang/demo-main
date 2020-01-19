package com.cfg;

import com.utils.string.UuidUtil;
import org.activiti.engine.impl.cfg.IdGenerator;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
* @Description: activiti配置信息
* @Param:
* @return:
* @Author: endure
* @Date: 2020/1/15
*/
@Configuration
public class ActivitiConfig implements ProcessEngineConfigurationConfigurer {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void configure(SpringProcessEngineConfiguration springProcessEngineConfiguration) {
        springProcessEngineConfiguration.setActivityFontName("宋体");
        springProcessEngineConfiguration.setLabelFontName("宋体");
        springProcessEngineConfiguration.setAnnotationFontName("宋体");
        logger.info("配置字体:" + springProcessEngineConfiguration.getActivityFontName());
        springProcessEngineConfiguration.setIdGenerator(new idGenerator());
    }
}
class idGenerator implements IdGenerator{
    @Override
    public String getNextId() {
        return UuidUtil.get32UUID();
    }
}