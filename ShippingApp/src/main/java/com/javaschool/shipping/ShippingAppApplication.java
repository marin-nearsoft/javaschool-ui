package com.javaschool.shipping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.javaschool"})
public class ShippingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShippingAppApplication.class, args);
	}

}
