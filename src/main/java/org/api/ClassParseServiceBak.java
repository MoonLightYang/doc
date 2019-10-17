//package org.api;
//
//import java.lang.reflect.Method;
//import java.lang.reflect.Type;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.api.doc.CacheMapping;
//import org.api.doc.bean.ApiDetail;
//import org.api.doc.bean.ApiMenu;
//import org.api.doc.bean.ApiParamList;
//import org.api.doc.bean.ApiResultList;
//import org.api.parse.DocConstant;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.saas.framework.annotation.DocMethod;
//
//public class ClassParseServiceBak {
//
//	/**
//	 * 解析Controller
//	 * 
//	 * @param controllerClazz
//	 * @return
//	 */
//	public ApiMenu parseController(Class<?> controllerClazz) {
//		RequestMapping cRm = controllerClazz.getAnnotation(RequestMapping.class);
//		String pid = DocConstant.ROOT_MENU; // 根id
//		String id = cRm.value()[0]; // 映射地址
//		String describle = cRm.name(); // 菜单名称
//		return new ApiMenu(pid, id, describle);
//	}
//
//	/**
//	 * 解析Controller Method
//	 * 
//	 * @param controllerClazz
//	 * @param menu
//	 * @return
//	 */
//	public List<ApiMenu> parseMethod(ParseObjectService objectParse, Class<?> controllerClazz, ApiMenu menu) {
//		List<ApiMenu> childs = new ArrayList<>();// 子菜单
//		String pid = menu.getId();
//
//		Method[] controllerMethods = controllerClazz.getDeclaredMethods();
//		for (Method controllerMethod : controllerMethods) {
//			// 1：设置方法信息
//			ApiDetail doc = this.setMetnodInfo(pid, controllerMethod);
//
//			// 2：解析参数
//			DocMethod docMethod = controllerMethod.getAnnotation(DocMethod.class);
//			if (docMethod != null) {
//				// 入参
//				List<ApiParamList> apls = this.parseMethodParams(objectParse, docMethod.param());
//				doc.setParams(apls);
//
//				// 出参
//				List<ApiResultList> arls = this.parseMethodResults(objectParse, docMethod.result());
//				doc.setResults(arls);
//			}
//
//			// 3：添加到二级菜单集合
//			String[] cacheMapping = doc.getUrl().split("/");
//			childs.add(new ApiMenu(cacheMapping[0], cacheMapping[1], doc.getDescrible()));
//
//			// 4：缓存菜单详情
//			CacheMapping.detailCache.put(cacheMapping[0] + "-" + cacheMapping[1], doc);
//		}
//		return childs;
//	}
//
//	/**
//	 * 设置方法信息
//	 * 
//	 * @param clazzMapping
//	 * @param method
//	 * @param doc
//	 * @return
//	 */
//	private ApiDetail setMetnodInfo(String controllerMapping, Method method) {
//		// 1: request method
//		String requestMethod = "", describle = "", methodMapping = "";
//		GetMapping cm = method.getAnnotation(GetMapping.class);
//		PostMapping pm = method.getAnnotation(PostMapping.class);
//		if (cm != null) {
//			requestMethod = "GET";
//			methodMapping = cm.value()[0];
//			describle = cm.name();
//		} else if (pm != null) {
//			requestMethod = "POST";
//			methodMapping = pm.value()[0];
//			describle = pm.name();
//		}
//		String url = controllerMapping + "/" + methodMapping;
//		return new ApiDetail(url, requestMethod, describle);
//	}
////
////	private List<ApiParamList> parseMethodParams(ParseObjectService objectParse, Class<?> paramClass) {
////		List<ApiParamList> apls = new ArrayList<>();// 申明方法参数列表
////		Type[] types = paramClass.getTypeParameters();
////		objectParse.parseParams(types[0], apls, true, false, null, null);
////		return apls;
////	}
////
////	private List<ApiResultList> parseMethodResults(ParseObjectService objectParse, Class<?> resultClass) {
////		List<ApiResultList> arls = new ArrayList<>();// 申明方法参数列表
////		Type type = resultClass.getTypeParameters()[0];
////		objectParse.parseResults(type, arls, true, false, null, null);
////		return arls;
////	}
//
//	private List<ApiParamList> parseMethodParams(ParseObjectService objectParse, Class<?> params) {
//		List<ApiParamList> apls = new ArrayList<>();// 申明方法参数列表
//		Type[] types = params.getTypeParameters();
//		objectParse.parseParams(types[0], apls, true, false, null, null);
//		return apls;
//	}
//
//	private List<ApiResultList> parseMethodResults(ParseObjectService objectParse, Class<?> params) {
//		List<ApiResultList> arls = new ArrayList<>();// 申明方法参数列表
//		Type type = params.getTypeParameters()[0];
//		objectParse.parseResults(type, arls, true, false, null, null);
//		return arls;
//	}
//}
