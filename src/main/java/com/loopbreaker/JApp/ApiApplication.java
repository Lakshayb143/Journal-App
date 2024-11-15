package com.loopbreaker.JApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement // This id done by PlatformTransactionManager which is implemented for MongoDb by MongoTransactionManager.
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Bean
	public PlatformTransactionManager transactionImplementation(MongoDatabaseFactory dbFactory){
		return new MongoTransactionManager(dbFactory);
	}

}
