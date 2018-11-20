package org.api.doc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.api.doc.bean.ApiDoc;
import org.api.doc.bean.ApiMenu;

public class MappingCache {

	// 用于列表显示菜单
	public static List<ApiMenu> menus = new ArrayList<ApiMenu>(100);

	// 方法映射
	public static HashMap<String, ApiDoc> modMapping = new HashMap<String, ApiDoc>(500);

	// // 接口文档信息
	// static {
	// for (int i = 0; i < 5; i++) {
	// for (int j = 0; j < 3; j++) { // 一个接口
	// ApiDoc ab = new ApiDoc();
	// ab.setDescrible("获取科室下拉框列表");
	// ab.setUrl("dept/query");
	// ab.setWay("POST");
	//
	// for (int n = 0; n < 2; n++) {
	// ApiParamList apl = new ApiParamList();
	// List<ApiParam> paramList = new ArrayList<>();
	// apl.setParams(paramList);
	// apl.setType(n == 0 ? "对象" : "集合");
	// apl.setField(n == 1 ? "age" : null);
	// for (int k = 0; k < 4; k++) { // 入参一
	// ApiParam ap = new ApiParam();
	// ap.setField("age");
	// ap.setIsAnchors(k == 1 && n == 0 ? 1 : 0);
	// ap.setRange("4");
	// ap.setName("年龄");
	// ap.setRemark("客户的年龄");
	// ap.setRequire("Y");
	// ap.setType("String");
	// paramList.add(ap);
	// }
	// ab.getParams().add(apl);
	// }
	//
	// for (int k = 0; k < 5; k++) { // 出参
	// ApiResult af = new ApiResult();
	// af.setField("age");
	// af.setName("年龄");
	// af.setRemark("客户的年龄");
	// af.setType("String");
	// ab.getResults().add(af);
	// }
	// modMapping.put(i + "-" + j, ab);
	// }
	// }
	// }
	//
	// // 接口菜单列表
	// static {
	// for (int i = 0; i < 5; i++) {
	// ApiMenu mc = new ApiMenu(i + "", "测试文档" + i);
	// List<ApiMenu> childs = new ArrayList<ApiMenu>();
	// for (int j = 0; j < 3; j++) {
	// ApiMenu child = new ApiMenu(j + "", "接口" + j);
	// child.setPid(i + "");
	// childs.add(child);
	// mc.setChilds(childs);
	// }
	// menus.add(mc);
	// }
	// }

}
