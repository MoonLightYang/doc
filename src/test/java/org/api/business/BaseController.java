package org.api.business;

import org.api.param.Child;
import org.springframework.web.bind.annotation.GetMapping;

public class BaseController {

	@GetMapping("hello")
	public String hello(Child child) {
		return "hello";
	}

}