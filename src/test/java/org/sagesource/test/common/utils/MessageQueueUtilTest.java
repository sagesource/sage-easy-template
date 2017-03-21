package org.sagesource.test.common.utils;

import org.junit.Test;
import org.sagesource.common.utils.MessageQueueUtil;
import org.sagesource.test.base.BaseTest;

/**
 * <p></p>
 * <pre>
 *     author      XueQi
 *     date        2017/3/21
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class MessageQueueUtilTest extends BaseTest {

	@Test
	public void queueSender_test() {
		MessageQueueUtil.queueSender("test_queue", "test message1 expire..........");
	}

}
