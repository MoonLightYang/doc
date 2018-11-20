package org.api.doc.bean;

import java.util.List;

public class ApiMenu {

	private String pid = "root"; // 父级id
	private String id; // 当前菜单id
	private String describle; // 菜单名称
	private List<ApiMenu> childs; // 子菜单

	public ApiMenu() {
	}

	public ApiMenu(String id, String describle) {
		this.id = id;
		this.describle = describle;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescrible() {
		return describle;
	}

	public void setDescrible(String describle) {
		this.describle = describle;
	}

	public List<ApiMenu> getChilds() {
		return childs;
	}

	public void setChilds(List<ApiMenu> childs) {
		this.childs = childs;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

}
