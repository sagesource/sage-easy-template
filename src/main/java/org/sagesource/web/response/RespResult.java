package org.sagesource.web.response;

/**
 * <p>Rest接口返回值对象</p>
 * <pre>
 *     author      XueQi
 *     date        2017/3/15
 *     email       qi.xue@ucarinc.com
 * </pre>
 */
public class RespResult<T> {
	/**
	 * 响应码
	 */
	private int    code;
	/**
	 * 响应信息
	 */
	private String message;
	/**
	 * 返回结果对象
	 */
	private T      result;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
}
