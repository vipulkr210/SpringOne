package com.example.DemoWebRest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.example.DemoWebRest.fileUpload.FileUploadProperties;


@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableJpaAuditing
@EnableConfigurationProperties(FileUploadProperties.class)
public class DemoWebRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoWebRestApplication.class, args);
	}
}
