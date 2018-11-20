package org.api.controller;

import java.util.List;

import org.api.doc.MappingCache;
import org.api.doc.bean.ApiDoc;
import org.api.doc.bean.ApiMenu;
import org.api.parse.DocPageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "api")
public class ApiController {

	@Autowired
	DocPageBuilder builder;

	String title = "医脉想成api接口文档";

	@GetMapping(value = "{parent}/{child}.html")
	public String doc(@PathVariable("parent") String pid, @PathVariable("child") String id, Model model)
			throws Exception {
		ApiDoc ab = MappingCache.modMapping.get(pid + "-" + id);
		model.addAttribute("title", title);
		model.addAttribute("v", "789");
		model.addAttribute("api", ab);

		String md = builder.doc2Md(ab);
		String html = builder.md2Html(md);
		model.addAttribute("content", html);
		return "restful/doc";
	}

	@GetMapping(value = "content.html")
	public String content(Model model, String id) {
		model.addAttribute("title", title);
		model.addAttribute("content", "欢迎使用API文档生成器");
		model.addAttribute("v", "456");
		return "restful/doc";
	}

	@GetMapping(value = "index.html")
	public String index(Model model) {
		model.addAttribute("icon", "logo-210-210.svg");
		model.addAttribute("title", "耶够 - 医脉相承API接口文档");
		model.addAttribute("docPath", "");
		model.addAttribute("v", "123");
		model.addAttribute("p", "");
		model.addAttribute("welcome", "content.html");

		List<ApiMenu> menus = MappingCache.menus;
		model.addAttribute("menus", menus);
		return "restful/home";
	}

}