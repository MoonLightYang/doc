package org.api;

import org.api.doc.annotation.EnableDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDoc
@SpringBootApplication
public class TestDocApp {

	public static void main(String[] args) {
		SpringApplication.run(RestfulDemoApp.class, args);
	}

}