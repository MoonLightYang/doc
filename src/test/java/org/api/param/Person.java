package org.api.param;

import javax.validation.constraints.NotEmpty;

import org.api.doc.annotation.DocField;
import org.api.doc.annotation.DocIgnoreField;

public class Person {

	@NotEmpty
	@DocField(name = "用户地址", range = "10个字符", sample = "123", remark = "当前居住的地址")
	public String address;
	@DocField(name = "年龄", range = "0 - 150", sample = "18", remark = "当前年龄")
	public int age;
	@DocField(name = "用户生日", sample = "1991-04-12", remark = "生日日期")
	public Integer brith;
	@DocIgnoreField
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
