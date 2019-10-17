package org.api.business;

import java.util.List;

import org.api.param.Animal;
import org.api.param.Person;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "person", name = "用户信息查询")
public class PersonController extends BaseController {

	@PostMapping(value = "insertBatch", name = "批量新增客户")
	public List<Person> insert(List<Animal> result) {
		return null;
	}
}