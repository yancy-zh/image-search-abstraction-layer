package com.homeprojs.imagesearchabstractionlayer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.homeprojs.imagesearchabstractionlayer.model"})
@EnableJpaRepositories("com.homeprojs.imagesearchabstractionlayer.repository")

@SpringBootApplication
public class ImageSearchAbstractionLayerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImageSearchAbstractionLayerApplication.class, args);
    }

}
