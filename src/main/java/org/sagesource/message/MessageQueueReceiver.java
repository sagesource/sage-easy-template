package org.sagesource.message;

import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * <p>消息队列监听器</p>
 * <pre>
 *     author      XueQi
 *     date        2017/3/21
 *     email       job.xueqi@gmail.com
 * </pre>
 */
@Component("messageQueueReceiver")
public class MessageQueueReceiver implements MessageListener {
	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("MessageQueueReceiver接收到消息:" + ((TextMessage) message).getText());
		} catch (Exception e) {
			// 消息消费异常的时候 要记录消息的一些信息 避免消息的重复发送和消费
			e.printStackTrace();
		}
	}
}
