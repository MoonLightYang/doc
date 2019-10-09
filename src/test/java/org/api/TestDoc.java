package org.api;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.api.doc.annotation.DocField;
import org.api.doc.bean.ApiDetail;
import org.api.doc.bean.ApiField;
import org.api.doc.bean.ApiParam;
import org.api.doc.bean.ApiParamList;
import org.api.param.Child;
import org.api.param.Person;
import org.api.results.Result;
import org.springframework.util.StringUtils;

public class TestDoc {
	// ============
	public void hello(Person animals) {
		System.out.println("...你好...");
	}

	// ============
	public void say(Result<Person> result) {
		System.out.println("...你好...");
	}

	// ============
	public void bye(List<Person> persons) {
		System.out.println("......");
	}

	// ============
	public void sing(Child animal) {
		System.out.println("......");
	}

	// ============
	public void listPerson(List<Person> persons) {
		System.out.println("......");
	}

	public static void main(String[] args) {
		TestDoc test = new TestDoc();
		// test.run("hello");
		// test.run("say");
		// test.run("bye");
		// test.run("sing");
		test.run("sing");
	}

	public void run(String mName) {
		ParseObjectService test = new ParseObjectService();

		// 1：一条记录标识一个方法的所有信息
		List<ApiDetail> docs = new ArrayList<ApiDetail>();
		ApiDetail doc = new ApiDetail();

		Class<TestDoc> clazz = TestDoc.class;
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			String name = method.getName();
			if (!mName.equals(name))
				continue;

			// 1： 存在泛型参数
			List<ApiParamList> apls = new ArrayList<>();// 申明方法参数列表
			Type[] types = method.getGenericParameterTypes();
			System.out.println("------------ method：" + name + " start -------------- ");
			test.parseParams(types[0], apls, true, true, null, null);
			System.out.println("------------ method：" + name + " end -------------- ");
			System.out.println();
			doc.setParams(apls);
			docs.add(doc);
		}
		// 输出信息
		this.printDoc(docs);
	}

	// =================================================================================================================
	/**
	 * 解析对象参数
	 * 
	 * @param clazz
	 * @return
	 */
	private List<ApiField> parseParam(Class<?> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		List<ApiField> docFields = new ArrayList<>();
		for (Field field : fields) {
			ApiField api = new ApiField(field.getName(), field.getType().toString());
			DocField df = field.getAnnotation(DocField.class);
			if (df != null) {
				api.setName(df.name());
				api.setRemark(df.remark());
			}
			docFields.add(api);
		}
		return docFields;
	}

	// ========== 输出响应结果 ======================
	public void printDoc(List<ApiDetail> docs) {
		for (ApiDetail doc : docs) {
			System.out.println("请求URI：" + doc.getUrl());
			System.out.println("请求方式：" + doc.getWay());
			System.out.println("接口描述：" + doc.getDescrible());
			List<ApiParamList> paramList = doc.getParams();

			// 输出请求参数
			if (paramList != null) {
				for (ApiParamList apl : paramList) {
					String anchorsField = apl.getField();
					if (!StringUtils.isEmpty(anchorsField))
						System.out.println("泛型锚点字段：" + apl.getField() + ", 泛型类型：" + apl.getType()); // 参数列表
					else
						System.out.println("参数类型：" + apl.getType()); // 参数列表

					List<ApiParam> aps = apl.getParams();
					for (ApiParam ap : aps) {
						System.out.println("字段：" + ap.getField() + ", 名称：" + ap.getName() + ", 长度：" + ap.getRange()
								+ ", 类型：" + ap.getType() + ", 锚点：" + ap.getIsAnchors() + ", 备注：" + ap.getRemark()
								+ ", 必填：" + ap.getRequire());
					}
					System.out.println();
				}
			}

			// // 输出响应结果
			// List<ApiResult> results = doc.getResults();
			// if (results != null) {
			// for (ApiResult ar : results) {
			// System.out.println("字段：" + ar.getField() + ", 名称：" + ar.getName() + ", 类型：" +
			// ar.getType() + ", 备注："
			// + ar.getRemark());
			// }
			// }
		}
	}
}
