package org.sagesource.web.base;

import com.google.common.base.Strings;
import org.sagesource.common.values.ApiResponseCodeValue;
import org.sagesource.web.response.RespResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Rest Controller基类</p>
 * <pre>
 *     author      XueQi
 *     date        2017/3/15
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class BaseApiController {

	/**
	 * 响应成功(没有业务异常时调用)
	 *
	 * @param result 返回结果
	 * @param <T>
	 * @return
	 */
	protected <T> RespResult<T> success(T result) {
		RespResult<T> respResult = new RespResult<>();

		respResult.setResult(result);
		respResult.setCode(ApiResponseCodeValue.SUCCESS_CODE);
		respResult.setMessage("SUCCESS");

		return respResult;
	}

	/**
	 * 业务异常处理
	 *
	 * @param result  返回结果
	 * @param message 错误信息
	 * @param <T>
	 * @return
	 */
	protected <T> RespResult<T> faild(T result, String message) {
		RespResult<T> respResult = new RespResult<>();
		respResult.setResult(result);
		respResult.setCode(ApiResponseCodeValue.BIZ_ERR_CODE);
		if (Strings.isNullOrEmpty(message))
			respResult.setMessage("BIZ ERROR");
		else
			respResult.setMessage(message);

		return respResult;
	}

	/**
	 * 统一rest接口异常处理
	 *
	 * @param request
	 * @param e
	 * @return
	 */
	@ExceptionHandler
	@ResponseBody
	public RespResult exceptionHandler(HttpServletRequest request, Exception e) {
		String requestUrl = request.getRequestURL().toString();
		String errStr     = e.toString();

		Map<String, Object> result = new HashMap<>();
		result.put("url", requestUrl);
		result.put("err", errStr);

		RespResult<Map<String, Object>> respResult = new RespResult<>();
		respResult.setResult(result);
		respResult.setCode(ApiResponseCodeValue.SYS_ERR_CODE);
		respResult.setMessage("System Error");

		return respResult;
	}
}
