package com.example;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {"com.example.model.dao"}, annotationClass = Mapper.class)
@SpringBootApplication

public class HappyHouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(HappyHouseApplication.class, args);
	}

}
