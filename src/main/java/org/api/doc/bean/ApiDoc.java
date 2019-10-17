package org.api.doc.bean;

import java.util.List;

public class ApiDoc {

	private String key;
	private String name;
	private List<ApiFieldDoc> docs;

	public ApiDoc(String key, List<ApiFieldDoc> docs) {
		this.key = key;
		this.docs = docs;
	}

	public ApiDoc(String key, String name, List<ApiFieldDoc> docs) {
		this.name = name;
		this.key = key;
		this.docs = docs;
	}

	public ApiDoc() {
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<ApiFieldDoc> getDocs() {
		return docs;
	}

	public void setDocs(List<ApiFieldDoc> docs) {
		this.docs = docs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
