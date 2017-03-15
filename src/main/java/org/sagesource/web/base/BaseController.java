package org.sagesource.web.base;

import org.sagesource.common.utils.ExceptionMessageUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Page Controller基类</p>
 * <pre>
 *     author      XueQi
 *     date        2017/3/14
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class BaseController {

	@Value("${show_error_stack}")
	private Boolean showErrorStack;

	/**
	 * 异常处理
	 *
	 * @return <p>
	 * ${request_url}:请求地址
	 * ${error_message}:错误信息
	 * </p>
	 */
	@ExceptionHandler
	public String exceptionHandler(HttpServletRequest request, Exception e, Model model) {
		String requestUrl   = request.getRequestURL().toString();
		if (showErrorStack) {
			String errorMessage = ExceptionMessageUtil.builtStackTraceStr(e, true);
			model.addAttribute("error_message", errorMessage);
		} else {
			model.addAttribute("error_message", e);
		}

		model.addAttribute("request_url", requestUrl);
		return "errorpage/500";
	}

}
