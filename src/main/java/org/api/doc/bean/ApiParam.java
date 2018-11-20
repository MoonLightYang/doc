package org.api.doc.bean;

public class ApiParam extends ApiField {

	private String range = ""; // 长度(范围)
	private String require = "N"; // 是否必填

	public ApiParam() {
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public String getRequire() {
		return require;
	}

	public void setRequire(String require) {
		this.require = require;
	}
}
