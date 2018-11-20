package org.api.doc.bean;

public class ApiList {

	public String field = "";// 锚点字段
	public String type = "";// 参数类型
	public int isBasic = 0;// 是否为基础类型，0否，1是

	public ApiList() {
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

	public int getIsBasic() {
		return isBasic;
	}

	public void setIsBasic(int isBasic) {
		this.isBasic = isBasic;
	}

}
