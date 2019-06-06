package ht.statictrafficmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@MapperScan("ht.statictrafficmanagement.base.mapper")
public class StaticTrafficManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(StaticTrafficManagementApplication.class, args);
	}
	
}
