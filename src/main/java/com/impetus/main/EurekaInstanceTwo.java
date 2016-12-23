package com.impetus.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = { "com.impetus.controller" ,"com.impetus.service"})
public class EurekaInstanceTwo {

	public static void main(String[] args) {
		SpringApplication.run(EurekaInstanceTwo.class, args);
	}
}