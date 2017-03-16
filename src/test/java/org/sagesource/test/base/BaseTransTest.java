package org.sagesource.test.base;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <p>带数据库事务的单元测试基类</p>
 * <pre>
 *     author      XueQi
 *     date        2017/3/16
 *     email       job.xueqi@gmail.com
 * </pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:applicationContext-test.xml",
		"classpath*:config/applicationContext-dataSource.xml",
		"classpath*:config/applicationContext-mybatis.xml"})
public class BaseTransTest {
}
