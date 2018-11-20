package org.api.doc.bean;

import java.util.ArrayList;
import java.util.List;

public class ApiDoc {

	private String url; // 请求uri
	private String describle; // 接口描述
	private String way; // 请求方式
	private List<ApiParamList> params = new ArrayList<>(); // 请求参数
	private List<ApiResultList> results = new ArrayList<>(); // 响应结果

	public ApiDoc() {
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescrible() {
		return describle;
	}

	public void setDescrible(String describle) {
		this.describle = describle;
	}

	public String getWay() {
		return way;
	}

	public void setWay(String way) {
		this.way = way;
	}

	public List<ApiParamList> getParams() {
		return params;
	}

	public void setParams(List<ApiParamList> params) {
		this.params = params;
	}

	public List<ApiResultList> getResults() {
		return results;
	}

	public void setResults(List<ApiResultList> results) {
		this.results = results;
	}

}
