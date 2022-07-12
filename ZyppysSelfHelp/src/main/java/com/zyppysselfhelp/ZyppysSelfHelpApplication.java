package com.zyppysselfhelp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan
@EnableJpaRepositories
public class ZyppysSelfHelpApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZyppysSelfHelpApplication.class, args);
	}

}