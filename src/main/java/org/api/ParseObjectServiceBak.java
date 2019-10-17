//package org.api;
//
//import java.lang.reflect.Field;
//import java.lang.reflect.ParameterizedType;
//import java.lang.reflect.Type;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;
//
//import org.api.doc.bean.ApiParam;
//import org.api.doc.bean.ApiParamList;
//import org.api.doc.bean.ApiResult;
//import org.api.doc.bean.ApiResultList;
//
//import com.saas.framework.annotation.DocField;
//import com.saas.framework.annotation.DocIgnore;
//
//
//public class ParseObjectServiceBak {
//
//	HashSet<String> basicSet = new HashSet<>();
//
//	public ParseObjectServiceBak() {
//		basicSet.add("java.lang.Integer");
//		basicSet.add("java.lang.Double");
//		basicSet.add("java.lang.Float");
//		basicSet.add("java.lang.Long");
//		basicSet.add("java.lang.Short");
//		basicSet.add("java.lang.Byte");
//		basicSet.add("java.lang.Boolean");
//		basicSet.add("java.lang.Character");
//		basicSet.add("java.lang.String");
//		basicSet.add("int");
//		basicSet.add("double");
//		basicSet.add("long");
//		basicSet.add("short");
//		basicSet.add("byte");
//		basicSet.add("boolean");
//		basicSet.add("char");
//		basicSet.add("floaxt");
//	}
//
//	/**
//	 * 截取类型的名称
//	 * 
//	 * @param typeString
//	 * @return
//	 */
//	private String getTypeName(String typeString) {
//		return typeString.substring(typeString.lastIndexOf(".") + 1, typeString.length());
//	}
//
//	// 参数解析
//	public void parseResults(Type type, List<ApiResultList> arls, boolean root, boolean isAnchors, String anchorsField,
//			HashSet<String> mapping) {
//		String paramTypeName = type.getTypeName();
//		if ("java.lang.Object".equals(paramTypeName))
//			return;
//
//		// 申明返回结果集合
//		ApiResultList apl = new ApiResultList();
//		arls.add(apl);
//		List<ApiResult> ars = new ArrayList<>();
//		apl.setResults(ars);
//
//		// 是否有锚点字段
//		if (isAnchors) {
//			apl.setField(anchorsField);
//			apl.setType(this.getTypeName(paramTypeName));
//		}
//
//		// 返回类型去重
//		if (mapping == null)
//			mapping = new HashSet<String>(10);
//
//		// 1：判断入口参数类型并处理
//		Class<?> paramClazz = null; // 入参类
//		String paramClazzTypeName = null; // 入参类名称
//		String genParamTypeName = null;
//		if (type instanceof ParameterizedType) { // 存在泛型参数
//			ParameterizedType paramType = (ParameterizedType) type;
//
//			// 参数类型信息
//			paramClazz = (Class<?>) paramType.getRawType();// 参数类
//			paramClazzTypeName = paramClazz.getTypeName(); // 参数类型名称
//
//			// 参数泛型信息
//			Class<?> genParamClazz = (Class<?>) paramType.getActualTypeArguments()[0]; // 泛型类型
//			String genParamClazzTypeName = genParamClazz.getTypeName(); // 泛型类型名称
//			System.out.println("带泛型：--> 参数类型：" + paramClazzTypeName + " --> 泛型类型: " + genParamClazz);
//
//			// 集合和对象的带参类型判定
//			if (root) { // 集合对象带参
//				if (paramClazz.isAssignableFrom(List.class) || paramClazz.isAssignableFrom(Map.class)) {
//					apl.setType(this.getTypeName(paramClazzTypeName));
//					paramClazz = genParamClazz;
//				} else { // 直接入参 --> 普通对象带泛型
//					apl.setType("对象");
//					genParamTypeName = this.getTypeName(genParamClazzTypeName);
//					this.parseResults(genParamClazz, arls, root, true, anchorsField, mapping);
//				}
//			}
//		} else if (basicSet.contains(paramTypeName)) {// 基础类型
//			if (root) {
//				apl.setIsBasic(1);
//			}
//			apl.setType(this.getTypeName(paramTypeName));
//			System.out.println("基础类型：" + paramTypeName);
//			return;
//		} else if (type instanceof Class) {// 一般对象类型
//			paramClazz = (Class<?>) type;// 参数类
//			paramClazzTypeName = paramClazz.getTypeName(); // 参数类型名称
//			apl.setType(this.getTypeName(paramClazzTypeName));
//			System.out.println("参数类型" + paramClazzTypeName);
//		}
//
//		// 获取参数所有字段
//		Field[] fields = paramClazz.getDeclaredFields();
//		this.parseField(genParamTypeName, fields, mapping, arls, ars);
//	}
//
//	private void parseField(String genParamTypeName, Field[] fields, HashSet<String> mapping, List<ApiResultList> arls,
//			List<ApiResult> ars) {
//		// 解析每个字段
//		for (Field field : fields) {
//			DocIgnore dif = field.getAnnotation(DocIgnore.class);
//			if (dif != null)
//				continue;
//
//			String fieldName = field.getName();
//			Type fieldType = field.getType();
//			String fieldTypeName = fieldType.getTypeName();
//			Type ftp = field.getGenericType();
//
//			// 一个对象参数
//			ApiResult ar = new ApiResult();
//			ar.setField(fieldName);
//			ar.setType(this.getTypeName(fieldTypeName));
//
//			// 文档注解解析
//			this.parseResultAnnotion(field, ar);
//
//			// 类型匹配和处理
//			if (basicSet.contains(fieldTypeName)) { // 非基础类型的
//				System.out.println("基础类型字段-->" + fieldName);
//			} else if (ftp instanceof ParameterizedType) { // 参数存在泛型参数
//				ParameterizedType fpt = (ParameterizedType) ftp;
//				Class<?> fieldGenParamClazz = (Class<?>) fpt.getActualTypeArguments()[0];// 参数泛型类型
//				System.out.println("包含泛型-->字段：" + fieldName + ", 类型：" + fieldType + ", 泛型类型：" + fieldGenParamClazz);
//				if (mapping.add(fieldGenParamClazz.getTypeName())) {
//					this.parseResults(fieldGenParamClazz, arls, false, true, fieldName, mapping);
//				}
//			} else { // 对象类型(不带泛型)
//				System.out.println("对象类型字段-->" + fieldName + ", 类型：" + fieldTypeName);
//				if ("java.lang.Object".equals(fieldTypeName)) {
//					ar.setType(genParamTypeName);
//					ar.setIsAnchors(1);
//				} else if (mapping.add(fieldTypeName)) {
//					this.parseResults(fieldType, arls, false, true, fieldName, mapping);
//				}
//			}
//			ars.add(ar);
//		}
//	}
//
//	// 参数解析
//	public void parseParams(Type type, List<ApiParamList> apls, boolean root, boolean isAnchors, String anchorsField,
//			HashSet<String> mapping) {
//		ApiParamList apl = new ApiParamList(); // 一个实体参数对象的列表
//		apls.add(apl);
//
//		List<ApiParam> aps = new ArrayList<>();
//		apl.setParams(aps);
//		if (isAnchors) {// 锚点字段
//			apl.setField(anchorsField);
//			apl.setType(this.getTypeName(type.getTypeName()));
//		}
//
//		if (mapping == null)
//			mapping = new HashSet<String>(10);
//
//		// 1：判断入口参数类型并处理
//		Class<?> paramClazz = null;
//		if (type instanceof ParameterizedType) { // 存在泛型参数
//			ParameterizedType paramType = (ParameterizedType) type;
//			Class<?> genParamClazz = (Class<?>) paramType.getActualTypeArguments()[0];// 参数泛型类型
//			paramClazz = (Class<?>) paramType.getRawType();
//			String paramClazzTypeName = paramClazz.getTypeName();
//			System.out.println("带泛型-->参数类型：" + paramClazzTypeName);
//			System.out.println("带泛型-->泛型参数类型：" + genParamClazz);
//
//			if (root) { // 如果root节点是其他类型的，则列表直接定位到泛型
//				apl.setType(this.getTypeName(paramClazzTypeName));
//				paramClazz = genParamClazz;
//			}
//		} else if (type instanceof Class) {// 一般对象类型
//			paramClazz = (Class<?>) type;// 参数类型
//			String paramClazzTypeName = paramClazz.getTypeName();
//			System.out.println("参数类型" + paramClazzTypeName);
//			apl.setType("对象");
//		}
//
//		// 获取参数所有字段, 解析每个字段
//		Field[] fields = paramClazz.getDeclaredFields();
//		for (Field field : fields) {
//			DocIgnore dif = field.getAnnotation(DocIgnore.class);
//			if (dif != null)
//				continue;
//
//			String fieldName = field.getName();
//			Type fieldType = field.getType();
//			String typeName = fieldType.getTypeName();
//			Type ftp = field.getGenericType();
//
//			// 一个对象参数
//			ApiParam ap = new ApiParam();
//			ap.setField(fieldName);
//			ap.setType(this.getTypeName(typeName));
//
//			// 文档注解解析
//			this.parseParamAnnotion(field, ap);
//
//			// 类型匹配和处理
//			if (basicSet.contains(typeName)) { // 非基础类型的
//				System.out.println("基础类型字段-->" + fieldName);
//			} else if (ftp instanceof ParameterizedType) { // 参数存在泛型参数
//				ParameterizedType fpt = (ParameterizedType) ftp;
//				Class<?> fieldGenParamClazz = (Class<?>) fpt.getActualTypeArguments()[0];// 参数泛型类型
//				System.out.println("包含泛型-->字段：" + fieldName + ", 类型：" + fieldType + ", 泛型类型：" + fieldGenParamClazz);
//				ap.setIsAnchors(1);
//				if (mapping.add(fieldGenParamClazz.getTypeName())) {
//					this.parseParams(fieldGenParamClazz, apls, false, true, fieldName, mapping);
//				}
//
//			} else { // 对象类型(不带泛型)
//				ap.setIsAnchors(1);
//				System.out.println("对象类型字段-->" + fieldName + ", 类型：" + typeName);
//				if (mapping.add(typeName)) {
//					this.parseParams(fieldType, apls, false, true, fieldName, mapping);
//				}
//			}
//			aps.add(ap);
//		}
//	}
//
//	/**
//	 * 解析入参字段上面的注解信息
//	 * 
//	 * @param field
//	 * @param ap
//	 */
//	private void parseResultAnnotion(Field field, ApiResult ar) {
//		// 文档注解
//		DocField doc = field.getAnnotation(DocField.class);
//		if (doc != null) {
//			ar.setName(doc.name());
//			ar.setRemark(doc.remark());
//			ar.setSample(doc.sample());
//		}
//	}
//
//	/**
//	 * 解析入参字段上面的注解信息
//	 * 
//	 * @param field
//	 * @param ap
//	 */
//	private void parseParamAnnotion(Field field, ApiParam ap) {
//		// 文档注解
//		DocField anDoc = field.getAnnotation(DocField.class);
//		if (anDoc != null) {
//			ap.setName(anDoc.name());
//			ap.setRemark(anDoc.remark());
//			ap.setRange(anDoc.range());
//		}
//
//		NotNull anNn = field.getAnnotation(NotNull.class);
//		NotEmpty anNe = field.getAnnotation(javax.validation.constraints.NotEmpty.class);
//		NotBlank anNb = field.getAnnotation(NotBlank.class);
//		// 必填判定
//		if (anNn != null || anNe != null || anNb != null)
//			ap.setRequire("Y");
//		else
//			ap.setRequire("N");
//	}
//
//}