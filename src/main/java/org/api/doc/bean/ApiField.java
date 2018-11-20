package org.api.doc.bean;

public class ApiField {

	public String field = "";// 实体字段,例："name", "address"
	public String name = ""; // 名称, 例： "姓名", "地址"
	public String type = ""; // 类型，
	public String remark = ""; // 说明
	public int isAnchors = 0; // 1是，0否，是否锚点
	public String sample = ""; // 示例

	public ApiField() {
	}

	public ApiField(String field, String type) {
		this.field = field;
		this.type = type;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

}
