package org.api.results;

public class Result<T> {

	public String name;
	public T data;
	//系统异常， 验证异常(参数校验，非空校验，登陆校验，逻辑校验)， 
	public int code;

	public Result() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
