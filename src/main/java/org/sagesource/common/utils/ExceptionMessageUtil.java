package org.sagesource.common.utils;

import org.springframework.util.StringUtils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * <p>异常消息处理</p>
 * <pre>
 *     author      XueQi
 *     date        2017/3/14
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class ExceptionMessageUtil {

	/**
	 * 异常堆栈信息转换为字符串
	 *
	 * @param e
	 * @param isHtml
	 * @return
	 */
	public static String builtStackTraceStr(Exception e, boolean isHtml) {
		// StringWriter将包含堆栈信息
		StringWriter stringWriter = new StringWriter();
		//必须将StringWriter封装成PrintWriter对象，
		//以满足printStackTrace的要求
		PrintWriter printWriter = new PrintWriter(stringWriter);
		//获取堆栈信息
		e.printStackTrace(printWriter);
		//转换成String，并返回该String
		StringBuffer error        = stringWriter.getBuffer();
		String       errorMessage = error.toString();

		if (isHtml)
			errorMessage = StringUtils.replace(errorMessage, "\n", "<br/>");

		return errorMessage;
	}

}
