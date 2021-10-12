package com.ssafy.happyhouse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.ssafy.happyhouse.model.mapper")
public class HpaayhouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(HpaayhouseApplication.class, args);
	}
}
