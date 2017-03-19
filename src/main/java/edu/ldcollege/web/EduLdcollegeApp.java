package edu.ldcollege.web;

import org.springframework.boot.SpringApplication;

import edu.ldcollege.web.config.ProWebConfiguration;

public class EduLdcollegeApp {

	public static void main(String[] args) {
		System.setProperty("spring.resources.static-locations", 
				"file:D:/develop/java_workspace/edu-ldcollege-static/static/");
		SpringApplication.run(ProWebConfiguration.class, args);
	}

}
