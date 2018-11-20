package org.api.doc.bean;

import java.util.List;

public class ApiParamList extends ApiList {

	private List<ApiParam> params;// 参数列表

	public ApiParamList() {
	}

	public List<ApiParam> getParams() {
		return params;
	}

	public void setParams(List<ApiParam> params) {
		this.params = params;
	}

}
