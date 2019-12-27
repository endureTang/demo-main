package com.model.rabbitMq;

/**
 * 队列配置实体
 */
public class QueueConfig {
	/**
	 * 队列名称
	 */
	private String name;
	/**
	 * 是否启动监听
	 */
	private boolean monitor;

	// 处理队列消息的bean的id
	private String handler;
	
	private String exchange;
	
	private String key;

	// 并发消费者数量
	private int concurrentConsumers=1;

	// 最大消费者数量
	private int maxConcurrentConsumers=20;

	// 每次从队列中获取的消息数量
	private int prefetchCount=1;

	// ack模式 false 手动 / true 自动
	private boolean autoack = true;

	// durable false 不持久 / true 持久
	private boolean durable = true;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public boolean isMonitor() {
		return monitor;
	}

	public void setMonitor(boolean monitor) {
		this.monitor = monitor;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public int getConcurrentConsumers() {
		return concurrentConsumers;
	}

	public void setConcurrentConsumers(int concurrentConsumers) {
		this.concurrentConsumers = concurrentConsumers;
	}

	public int getMaxConcurrentConsumers() {
		return maxConcurrentConsumers;
	}

	public void setMaxConcurrentConsumers(int maxConcurrentConsumers) {
		this.maxConcurrentConsumers = maxConcurrentConsumers;
	}

	public int getPrefetchCount() {
		return prefetchCount;
	}

	public void setPrefetchCount(int prefetchCount) {
		this.prefetchCount = prefetchCount;
	}

	public boolean isAutoack() {
		return autoack;
	}

	public void setAutoack(boolean autoack) {
		this.autoack = autoack;
	}

	public boolean isDurable() {
		return durable;
	}

	public void setDurable(boolean durable) {
		this.durable = durable;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}

