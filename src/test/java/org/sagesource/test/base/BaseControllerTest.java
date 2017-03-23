package org.sagesource.test.base;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * <p>Controller Mock 单元测试基类</p>
 * <pre>
 *     author      XueQi
 *     date        2017/3/23
 *     email       job.xueqi@gmail.com
 * </pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {
		"classpath:applicationContext-test.xml",
		"classpath*:config/applicationContext-*.xml",
		"classpath:applicationContext-webmvc-test.xml"})
public class BaseControllerTest {

	@Autowired
	private WebApplicationContext wac;
	public  MockMvc               mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
}
