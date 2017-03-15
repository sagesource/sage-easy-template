package org.sagesource.web.api;

import org.sagesource.web.base.BaseApiController;
import org.sagesource.web.response.RespResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p></p>
 * <pre>
 *     author      XueQi
 *     date        2017/3/15
 *     email       job.xueqi@gmail.com
 * </pre>
 */
@RestController
@RequestMapping("/api/simple")
public class SimpleApiController extends BaseApiController {

	/**
	 * rest接口实例
	 *
	 * @param name
	 * @return <p>
	 * ${name}:入参name
	 * ${date}:时间
	 * </p>
	 */
	@RequestMapping(value = "{name}", method = RequestMethod.GET, produces = "application/json")
	public RespResult<Map<String, Object>> simpleApi(@PathVariable("name") String name) {
		Map<String, Object> result = new HashMap<>();
		result.put("name", name);
		result.put("date", new Date());

		return success(result);
	}

}
