package org.api.param;

import javax.validation.constraints.NotEmpty;

import com.saas.framework.annotation.DocField;
import com.saas.framework.annotation.DocIgnore;

public class Person {

	@NotEmpty
	@DocField(name = "用户地址", sample = "123", remark = "当前居住的地址")
	public String address;
	@DocField(name = "年龄", sample = "18", remark = "当前年龄")
	public int age;
	@DocField(name = "用户生日", sample = "1991-04-12", remark = "生日日期")
	public Integer brith;
	@DocIgnore
	public Integer createDate;

	public Person() {
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Integer getBrith() {
		return brith;
	}

	public void setBrith(Integer brith) {
		this.brith = brith;
	}

	public Integer getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Integer createDate) {
		this.createDate = createDate;
	}

}
