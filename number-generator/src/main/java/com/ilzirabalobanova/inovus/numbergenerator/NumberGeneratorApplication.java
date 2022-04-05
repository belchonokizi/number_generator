package com.ilzirabalobanova.inovus.numbergenerator;

import com.ilzirabalobanova.inovus.numbergenerator.service.NumberGeneratorService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class NumberGeneratorApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(NumberGeneratorApplication.class, args);
		NumberGeneratorService service = context.getBean(NumberGeneratorService.class);
		System.out.println(service.generateRandomNumber());
	}

}
