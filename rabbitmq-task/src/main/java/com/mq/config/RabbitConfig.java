package com.mq.config;

import com.model.rabbitMq.QueueConfig;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * rabbitMq配置
 */
@Component("rabbitConfig") 
@Configuration
@PropertySource(value = {"classpath:${spring.profiles.active}/rabbitmq.properties"},encoding="utf-8")
@ConfigurationProperties(prefix="rabbitmq")
public class RabbitConfig {
	private List<QueueConfig> queueConfigList;
	public List<QueueConfig> getQueue() {
		return queueConfigList;
	}
	public void setQueue(List<QueueConfig> queue) {
		this.queueConfigList = queue;
	}

	//声明队列
    @Bean
    public Queue orderQueue() {
        return new Queue(queueConfigList.get(0).getName(), queueConfigList.get(0).isDurable()); // true表示持久化该队列
    }
    @Bean
    public Queue repayPlanQueue() {
        return new Queue(queueConfigList.get(1).getName(), queueConfigList.get(1).isDurable());
    }

    //声明交互器
    @Bean
    public DirectExchange orderExchange() {
        return new DirectExchange(queueConfigList.get(0).getExchange());
    }
    @Bean
    public DirectExchange repayPlanExchange() {
        return new DirectExchange(queueConfigList.get(1).getExchange());
    }

    
    //绑定
    @Bean
    public Binding orderBinding() {
        return BindingBuilder.bind(orderQueue()).to(orderExchange()).with(queueConfigList.get(0).getKey());
    }
    @Bean
    public Binding repayPlanBinding() {
        return BindingBuilder.bind(repayPlanQueue()).to(repayPlanExchange()).with(queueConfigList.get(1).getKey());
    }
}
