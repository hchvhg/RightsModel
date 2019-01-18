package com.bsoft;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bsoft.mapper")
public class RightsModelApplication {

	public static void main(String[] args) {
		SpringApplication.run(RightsModelApplication.class, args);
	}

}
