package org.api;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.api.doc.MappingCache;
import org.api.doc.bean.ApiDoc;
import org.api.doc.bean.ApiMenu;
import org.api.doc.bean.ApiParamList;
import org.api.doc.bean.ApiResultList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

public class ParseMethodService {

	/**
	 * 解析Controller
	 * 
	 * @param clazz
	 * @return
	 */
	public ApiMenu parseController(Class<?> clazz) {
		ApiMenu menu = new ApiMenu();

		RequestMapping cRm = clazz.getAnnotation(RequestMapping.class);
		String clazzMapping = cRm.value()[0]; // 映射地址
		String clazzRemark = cRm.name(); // 菜单名称
		menu.setId(clazzMapping);// 菜单id
		menu.setDescrible(clazzRemark);// 菜单名(菜单描述)

		List<ApiMenu> childs = new ArrayList<>();// 子菜单
		this.parseToChildMenus(clazz, clazzMapping, childs);
		menu.setChilds(childs);

		return menu;
	}

	/**
	 * 解析参数获取现有的菜单
	 * 
	 * @param clazz
	 * @param clazzMapping
	 * @param childs
	 * @return
	 */
	private List<ApiDoc> parseToChildMenus(Class<?> clazz, String clazzMapping, List<ApiMenu> childs) {
		List<ApiDoc> docs = new ArrayList<ApiDoc>(); // 对应的子菜单
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			ApiDoc doc = new ApiDoc();
			// 1：设置方法信息
			String methodMapping = this.setMetnodInfo(clazzMapping, method, doc);
			
			// 2: 解析请求参数
			List<ApiParamList> apls = this.parseMethodParams(method);
			doc.setParams(apls);

			// 3: 解析返回参数
			List<ApiResultList> arls = this.parseMethodResults(method);
			doc.setResults(arls);

			// 4：放入总集合中
			docs.add(doc);

			// 5：设置菜单信息
			this.cacheMenus(clazzMapping, methodMapping, childs, doc);
		}
		return docs;
	}

	/**
	 * 缓存菜单信息
	 * 
	 * @param clazzMapping
	 * @param methodMapping
	 * @param childs
	 * @param doc
	 */
	private void cacheMenus(String clazzMapping, String methodMapping, List<ApiMenu> childs, ApiDoc doc) {
		ApiMenu child = new ApiMenu();
		child.setPid(clazzMapping);
		child.setDescrible(doc.getDescrible());
		child.setId(methodMapping);
		childs.add(child);
		MappingCache.modMapping.put(clazzMapping + "-" + methodMapping, doc);
	}

	/**
	 * 设置方法信息
	 * 
	 * @param clazzMapping
	 * @param method
	 * @param doc
	 * @return
	 */
	private String setMetnodInfo(String clazzMapping, Method method, ApiDoc doc) {
		String value = null;
		GetMapping cm = method.getAnnotation(GetMapping.class);
		if (cm != null) {
			doc.setWay("GET");
			value = cm.value()[0];
			doc.setDescrible(cm.name());
		}
		PostMapping pm = method.getAnnotation(PostMapping.class);
		if (pm != null) {
			value = pm.value()[0];
			doc.setWay("POST");
			doc.setDescrible(pm.name());
		}
		doc.setUrl(clazzMapping + "/" + value);
		return value;
	}

	private List<ApiParamList> parseMethodParams(Method method) {
		List<ApiParamList> apls = new ArrayList<>();// 申明方法参数列表
		Type[] types = method.getGenericParameterTypes();

		ParseObjectService test = new ParseObjectService();
		test.parseParams(types[0], apls, true, false, null, null);
		return apls;
	}

	private List<ApiResultList> parseMethodResults(Method method) {
		List<ApiResultList> arls = new ArrayList<>();// 申明方法参数列表
		Type type = method.getGenericReturnType();

		ParseObjectService test = new ParseObjectService();
		test.parseResults(type, arls, true, false, null, null);
		return arls;
	}
}
