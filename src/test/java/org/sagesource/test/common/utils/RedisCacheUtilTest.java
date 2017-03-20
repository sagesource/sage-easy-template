package org.sagesource.test.common.utils;

import org.junit.Test;
import org.sagesource.common.utils.RedisCacheUtil;
import org.sagesource.test.base.BaseTest;

/**
 * <p></p>
 * <pre>
 *     author      XueQi
 *     date        2017/3/20
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class RedisCacheUtilTest extends BaseTest {

	private static final String KEY_PREFIX = "sage-easy-templete-";

	/**
	 * del方法测试
	 */
	@Test
	public void del_test() {
		RedisCacheUtil.del(KEY_PREFIX + System.currentTimeMillis());
	}

	/**
	 * set方法测试
	 */
	@Test
	public void set_test() {
		RedisCacheUtil.set(KEY_PREFIX + System.currentTimeMillis(), "test", 100L);
	}

	@Test
	public void set_dir_test() {
		RedisCacheUtil.set("sage:easy:templete:" + System.currentTimeMillis(), "dir_test", 100L);
	}
}
