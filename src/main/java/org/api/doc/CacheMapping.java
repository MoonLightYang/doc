package org.api.doc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.api.doc.bean.ApiDetail;
import org.api.doc.bean.ApiMenu;

public class CacheMapping {

	// 用于列表显示菜单（一级为class, 二级为method）
	public static List<ApiMenu> menusCache = new ArrayList<ApiMenu>(100);

	// 用于缓存所有method详情信息，便于快速查找
	public static HashMap<String, ApiDetail> detailCache = new HashMap<String, ApiDetail>(500);

}
