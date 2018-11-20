package org.api.doc.service;

import java.lang.annotation.Annotation;

import javax.annotation.PostConstruct;

import org.api.ParseMethodService;
import org.api.ParseObjectService;
import org.api.controller.ApiController;
import org.api.doc.MappingCache;
import org.api.doc.bean.ApiMenu;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

@ComponentScan(basePackageClasses = { ApiController.class, ParseMethodService.class, ParseObjectService.class })
@Configuration
public class EnableDocConfiguration implements ApplicationContextAware {

	private ApplicationContext applicationContext = null;

	@PostConstruct
	public void tet() {
		String[] array = this.controllers(RestController.class);
		ParseMethodService sc = new ParseMethodService();
		for (String arr : array) {
			Class<?> clazz = applicationContext.getBean(arr).getClass();
			ApiMenu menu = sc.parseController(clazz);
			MappingCache.menus.add(menu);
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
