package com.example.stack;

import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableSchedulerLock(defaultLockAtMostFor = "PT30S")
public class StackApplication {

    public static void main(String[] args) {
        SpringApplication.run(StackApplication.class, args);
    }

}
