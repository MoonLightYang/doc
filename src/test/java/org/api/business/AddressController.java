package org.api.business;

import org.api.param.Child;
import org.api.param.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "address", name = "地址信息查询")
public class AddressController extends BaseController {

	@GetMapping(value = "getAddress", name = "查询各级省市地址")
	public String getAddress(Child child) {
		return "hello";
	}

	@PostMapping(value = "getEmail", name = "获取邮件地址")
	public String getEmail(Person person) {
		return "hello";
	}

}