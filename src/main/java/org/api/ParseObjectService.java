package org.api;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.api.doc.bean.ApiDoc;
import org.api.doc.bean.ApiFieldDoc;

import com.saas.framework.annotation.DocField;

public class ParseObjectService {

	HashSet<String> basicSet = new HashSet<>();

	public ParseObjectService() {
		basicSet.add("Integer");
		basicSet.add("Double");
		basicSet.add("Float");
		basicSet.add("Long");
		basicSet.add("Short");
		basicSet.add("Byte");
		basicSet.add("Boolean");
		basicSet.add("Character");
		basicSet.add("String");
		basicSet.add("int");
		basicSet.add("double");
		basicSet.add("long");
		basicSet.add("short");
		basicSet.add("byte");
		basicSet.add("boolean");
		basicSet.add("char");
		basicSet.add("float");
	}

	public void parseField(String keyPrex, String fieldName, Class<?> paramClazz, List<ApiDoc> results) {
		List<ApiFieldDoc> params = new ArrayList<>();
		
		ApiDoc apiDoc = new ApiDoc(keyPrex, fieldName,params);
		results.add(apiDoc);

		Field[] fields = paramClazz.getDeclaredFields();
		if (fields == null)
			return;

		ApiFieldDoc param = null;
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			DocField df = field.getAnnotation(DocField.class);
			if (df == null)
				continue;

			ApiFieldDoc apiParam = new ApiFieldDoc();
			apiParam.setField(field.getName());
			apiParam.setName(df.name());
			apiParam.setSample(df.sample());
			apiParam.setRemark(df.remark());
			apiParam.setRequire(this.isRequire(field));

			Class<?> typeClazz = field.getType();
			String typeName = this.getFieldType(typeClazz);
			if (basicSet.contains(typeName))
				apiParam.setType(typeName);
			else if (this.isEnum(typeClazz)) {
				apiParam.setType("Enum");
				String remark = this.parseEnum(typeClazz);
				apiParam.setRemark(apiParam.getRemark() + remark);
			} else if (this.isList(typeName)) {
				apiParam.setType("List");
				apiParam.setIsAnchors(1);
				Class<?> genClazz = this.getGenClass(field);
				if(paramClazz == genClazz || paramClazz.equals(genClazz)){
					
				}else {
					this.parseField(field.getName() + (i + 1), field.getName(), genClazz, results);
				}
			} else if (!Collection.class.isAssignableFrom(typeClazz)) {
				apiParam.setType(typeName);
				apiParam.setIsAnchors(1);
				this.parseField(field.getName() + (i + 1), field.getName(), typeClazz, results);
			}
			params.add(apiParam);
		}
	}

	private boolean isList(String type) {
		if (type.equals("List"))
			return true;

		return false;
	}

	private boolean isEnum(Class<?> typeClazz) {
		if (Enum.class.isAssignableFrom(typeClazz))
			return true;

		return false;
	}

	private String parseEnum(Class<?> typeClazz) {
		Enum<?>[] enums = (Enum[]) typeClazz.getEnumConstants();
		StringBuilder sb = new StringBuilder(50);
		try {
			Method valMod = typeClazz.getMethod("getValue");
			Method txtMod = typeClazz.getMethod("getText");
			int len = enums.length;
			for (int i = 0; i < len; i++) {
				Enum<?> enumConst = enums[i];
				sb.append("[").append(valMod.invoke(enumConst, null)).append("-");
				sb.append(txtMod.invoke(enumConst, null)).append("]");
				if(i != len - 1)
					sb.append(",");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	private String getFieldType(Class<?> typeClazz) {
		String fts = typeClazz.toString();
		String type = fts.substring(fts.lastIndexOf(".") + 1, fts.length());
		return type;
	}

	private String isRequire(Field field) {
		Annotation[] anos = field.getAnnotations();
		if (anos == null)
			return "N";

		for (Annotation ano : anos) {
			if (ano instanceof NotNull)
				return "Y";
			if (ano instanceof NotEmpty)
				return "Y";
			if (ano instanceof NotBlank)
				return "Y";
		}
		return "N";
	}

	private Class<?> getGenClass(Field field) {
		Type genericType = field.getGenericType();
		if (genericType == null)
			return null;

		if (genericType instanceof ParameterizedType) {
			ParameterizedType pt = (ParameterizedType) genericType;
			Class<?> genClazz = (Class<?>) pt.getActualTypeArguments()[0];
			return genClazz;
		}
		return null;
	}

}