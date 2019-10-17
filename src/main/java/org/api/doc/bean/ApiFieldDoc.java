package org.api.doc.bean;

public class ApiFieldDoc {

	private String require = ""; // 是否必填
	private String field = "";// 字段名称
	private String type = "";// 字段类型
	private String name = "";// 字段含义
	private int isAnchors = 0; // 是否锚点
	private String sample = ""; // 示例
	private String remark = ""; // 备注

	public ApiFieldDoc() {
	}

	public String getRequire() {
		return require;
	}

	public void setRequire(String require) {
		this.require = require;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIsAnchors() {
		return isAnchors;
	}

	public void setIsAnchors(int isAnchors) {
		this.isAnchors = isAnchors;
	}

	public String getSample() {
		return sample;
	}

	public void setSample(String sample) {
		this.sample = sample;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
