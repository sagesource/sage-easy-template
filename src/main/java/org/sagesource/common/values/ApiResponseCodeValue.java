package org.sagesource.common.values;

/**
 * <p>api响应码常量</p>
 * <pre>
 *     author      XueQi
 *     date        2017/3/15
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public interface ApiResponseCodeValue {

	/**
	 * 响应成功Code
	 */
	int SUCCESS_CODE = 0;
	/**
	 * 业务异常响应Code
	 */
	int BIZ_ERR_CODE = 1;
	/**
	 * 系统异常响应
	 */
	int SYS_ERR_CODE = -1;
}
