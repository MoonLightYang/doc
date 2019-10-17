package org.api.doc.bean;

import java.util.ArrayList;
import java.util.List;

public class ApiDetail {

	private String url; // 请求uri
	private String describle; // 接口描述
	private String way; // 请求方式
	private List<ApiDoc> params = new ArrayList<>(); // 请求参数
	private List<ApiDoc> results = new ArrayList<>(); // 响应结果

	public ApiDetail() {
	}

	public ApiDetail(String url, String way, String describle) {
		this.url = url;
		this.way = way;
		this.describle = describle;
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

	public List<ApiDoc> getParams() {
		return params;
	}

	public void setParams(List<ApiDoc> params) {
		this.params = params;
	}

	public List<ApiDoc> getResults() {
		return results;
	}

	public void setResults(List<ApiDoc> results) {
		this.results = results;
	}

}
