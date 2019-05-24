package ht.statictrafficmanagement.util;

import java.io.Serializable;

/**
 * 用于向客户端响应操作结果的类型
 * @author Alex
 *
 * @param <T>操作结果中包含的数据的类型
 */
public class ResponseResult<T> implements Serializable{

	private static final long serialVersionUID = -1626793180717240861L;
	
	private Integer state;
	private String message;
	private T data;//泛型谁用谁决定
	
	public ResponseResult() {
		super();
	}
	
	public ResponseResult(Integer state, T data) {
		super();
		this.state = state;
		this.data = data;
	}
	public ResponseResult(Integer state) {
		super();
		this.state = state;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "ResponseResult [state=" + state + ", message=" + message + ", data=" + data + "]";
	}
	
}
