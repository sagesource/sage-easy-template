package org.sagesource.test.web.api;

import org.junit.Test;
import org.sagesource.test.base.BaseControllerTest;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * <p></p>
 * <pre>
 *     author      XueQi
 *     date        2017/3/23
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class SimpleApiControllerTest extends BaseControllerTest {

	@Test
	public void testView() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/simple/1"))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
		System.out.println("==== Content ====");
		System.out.println(result.getResponse().getContentAsString());
	}
}
