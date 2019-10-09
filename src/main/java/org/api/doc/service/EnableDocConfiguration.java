package org.api.doc.service;

import java.lang.annotation.Annotation;
import java.util.List;

import javax.annotation.PostConstruct;

import org.api.ClassParseService;
import org.api.ParseObjectService;
import org.api.controller.ApiController;
import org.api.doc.CacheMapping;
import org.api.doc.bean.ApiMenu;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

@ComponentScan(basePackageClasses = { ApiController.class, ClassParseService.class, ParseObjectService.class })
@Configuration
public class EnableDocConfiguration implements ApplicationContextAware {

	private ApplicationContext applicationContext = null;

	@PostConstruct
	public void tet() {
		String[] array = this.controllers(RestController.class);
		ClassParseService classParse = new ClassParseService();
		ParseObjectService objectParse = new ParseObjectService();
		for (String arr : array) {
			Class<?> controllerClazz = applicationContext.getBean(arr).getClass();
			
			ApiMenu menu = classParse.parseController(controllerClazz);
			
			List<ApiMenu> childs = classParse.parseMethod(objectParse, controllerClazz, menu);
			menu.setChilds(childs);
			
			CacheMapping.menusCache.add(menu);
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (this.applicationContext == null) {
			this.applicationContext = applicationContext;
		}
	}

	public String[] controllers(Class<? extends Annotation> clazz) {
		return this.applicationContext.getBeanNamesForAnnotation(clazz);
	}

}
