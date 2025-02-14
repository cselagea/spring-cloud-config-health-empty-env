package com.example.config.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServer {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ConfigServer.class)
                .properties("spring.config.name=configserver") // Config Server jar contains configserver.yml
                .run(args);
    }

}
