package org.sagesource.common.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * <p>消息队列工具类</p>
 * <pre>
 *     author      XueQi
 *     date        2017/3/21
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class MessageQueueUtil {
	private static final Integer REPEAT_TIME = 5;   // 重试次数

	private static JmsTemplate jmsQueueTemplate;    // 队列模式 Spring Jms Templete
	private static JmsTemplate jmsTopicTemplate;    // 发布/订阅 模式 Spring Jms Templete

	static {
		jmsQueueTemplate = SpringContextHolder.getBean("jmsQueueTemplate");
		jmsTopicTemplate = SpringContextHolder.getBean("jmsTopicTemplate");
	}

	/**
	 * 向队列发送文本消息
	 *
	 * @param queueName 队列名称
	 * @param message   消息内容
	 */
	public static void queueSender(String queueName, final String message) {
		for (int i = 0; i < REPEAT_TIME; i++) {
			try {
				jmsQueueTemplate.send(queueName, new MessageCreator() {
					@Override
					public Message createMessage(Session session) throws JMSException {
						TextMessage result = session.createTextMessage(message);
						return result;
					}
				});
				break;
			} catch (JmsException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 向队列发送对象消息(转换为JSON格式)
	 *
	 * @param queueName 队列名称
	 * @param message   消息内容对象
	 */
	public static void queueSender(String queueName, final Object message) {
		String objectMessage = JSON.toJSONString(message);
		queueSender(queueName, objectMessage);
	}

	/**
	 * 向主题发送消息
	 *
	 * @param topicName 主题名称
	 * @param message   消息内容
	 */
	public static void topicSender(String topicName, final String message) {
		for (int i = 0; i < REPEAT_TIME; i++) {
			try {
				jmsTopicTemplate.send(topicName, new MessageCreator() {
					@Override
					public Message createMessage(Session session) throws JMSException {
						return session.createTextMessage(message);
					}
				});
				break;
			} catch (JmsException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 向主题发送消息(转换为JSON格式)
	 *
	 * @param topicName
	 * @param message
	 */
	public static void topicSender(String topicName, final Object message) {
		String objectMessage = JSON.toJSONString(message);
		topicSender(topicName, objectMessage);
	}
}
