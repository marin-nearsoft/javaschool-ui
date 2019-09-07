package com.JavaSchool.BackEnd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
public class BackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackEndApplication.class, args);
	}

	@CrossOrigin
	@RequestMapping(value = "/cities", produces = "application/json")
	public String[] cities() {
		String[] cities= {"Chihuahua", "Monterrey", "Mexico"};
		return cities;
	}

	@CrossOrigin
	@RequestMapping(value = "/size", produces = "application/json")
	public String[] size() {
		String[] size = {"Small", "Medium", "Large"};
		return size;
	}

	@CrossOrigin
	@RequestMapping(value = "/type", produces = "application/json")
	public String[] type() {
		String[] type = {"1", "2", "3"};
		return type;
	}

	@CrossOrigin
	@RequestMapping(value = "/time", produces = "application/json")
	public String[] time() {
		String[] time = {"Slow", "Fast"};
		return time;
	}
	@CrossOrigin
	@RequestMapping(value = "/transport", produces = "application/json")
	public String[] transport() {
		String[] transport = {"express", "standar"};
		return transport;
	}
}
