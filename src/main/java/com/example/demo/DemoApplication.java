package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
    public static final Logger log= LoggerFactory.getLogger("DemoApplicatiom.class");

	public static void main(String[] args) {
        log.info("Application Started");
		SpringApplication.run(DemoApplication.class, args);
        log.info("Your Application is Ready");
	}

    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(args).forEach(a->log.info(a));
    }
}
