package org.api.business;

import java.util.List;

import org.api.param.Animal;
import org.api.param.Child;
import org.api.param.Person;
import org.api.results.Consumer;
import org.api.results.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "person", name = "用户信息查询")
public class PersonController extends BaseController {

	 @GetMapping(value = "getPerson", name = "获取个人信息")
	 public Consumer getAddress(Child child) {
	 return null;
	 }

	@PostMapping(value = "getAll", name = "获取所有客户权限")
	public Result<Person> getEmail(Person person) {
		return null;
	}

	 @PostMapping(value = "insert", name = "新增客户")
	 public String queryAuths(Result<Person> result) {
	 return null;
	 }
	
	 @PostMapping(value = "insertBatch", name = "批量新增客户")
	 public List<Person> insert(List<Animal> result) {
	 return null;
	 }
}