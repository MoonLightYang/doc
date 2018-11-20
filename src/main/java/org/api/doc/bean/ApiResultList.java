package org.api.doc.bean;

import java.util.List;

public class ApiResultList extends ApiList {

	private List<ApiResult> results;// 参数列表

	public ApiResultList() {
	}

	public List<ApiResult> getResults() {
		return results;
	}

	public void setResults(List<ApiResult> results) {
		this.results = results;
	}

}
