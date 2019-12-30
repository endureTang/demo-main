package com.mq.config;

import com.model.rabbitMq.QueueConfig;
import com.mq.receiver.OrderHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class QueueHanlder implements ApplicationContextAware,Runnable{
    private final static Logger logger = LoggerFactory.getLogger(OrderHandler.class);

    // 每个队列的消息监听器
    private final Map<String, SimpleMessageListenerContainer> containerMap = new ConcurrentHashMap<>();
    @Resource
    private RabbitConfig rabbitConfig;
    @Resource
    private ConnectionFactory connectionFactory;
    /**
     * 当前上下文
      */
    private ApplicationContext applicationContext;

    public QueueHanlder(ApplicationContext applicationContext) {
        logger.info("QueueHandler注入上下文");
        this.applicationContext = applicationContext;
    }

    public void refeshSimpleMessageListenerContainer(QueueConfig queueConfig){
        logger.info("RabbitMq消息监听容器启动....");
        SimpleMessageListenerContainer container = containerMap.get(queueConfig.getName());
        //如果已经存在监听器，刷新
        if(container != null){
            container.stop();
        }
        container = new SimpleMessageListenerContainer(connectionFactory);
        //设置监听队列
        container.setQueues(rabbitConfig.orderQueue(),rabbitConfig.repayPlanQueue());
        //设置消息处理监听
        container.setMessageListener((ChannelAwareMessageListener)applicationContext.getBean(queueConfig.getHandler()));
        // 设置获取消息的并发线程数
        container.setConcurrentConsumers(queueConfig.getConcurrentConsumers());
        // 最大的消费端线程数量
        container.setMaxConcurrentConsumers(queueConfig.getMaxConcurrentConsumers());
        //设置是否重回队列
        container.setDefaultRequeueRejected(false);
        //设置手动处理消息
        container.setAcknowledgeMode(queueConfig.isAutoack() ? AcknowledgeMode.AUTO : AcknowledgeMode.MANUAL);
        //设置监听外露
        container.setExposeListenerChannel(true);
        // 每个消费者，每次取消息条数
        container.setPrefetchCount(queueConfig.getPrefetchCount());
        logger.info("刷新MQ消息监听-->"+queueConfig.getName());
        containerMap.put(queueConfig.getName(), container);
        container.start();

    }

    @Override
    public void run() {
        List<QueueConfig> queueConfigs = rabbitConfig.getQuene();
        for (QueueConfig queueConfig : queueConfigs) {
            if (queueConfig.isMonitor()){
                this.refeshSimpleMessageListenerContainer(queueConfig);
            }
        }

    }
    // 在应用程序上下文加载完成后，开始初始化消息队列的监听机制
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        new Thread(this).start();
    }
}
