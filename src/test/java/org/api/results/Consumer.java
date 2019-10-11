package org.api.results;

import com.saas.framework.annotation.DocField;

public class Consumer {

	@DocField(name="客户手机", sample="15021048619")
	private String phone;
	@DocField(name="客户名称", sample="张三")
	private String name;
	@DocField(name="客户年龄", sample="18")
	private int age;

	public Consumer() {
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
