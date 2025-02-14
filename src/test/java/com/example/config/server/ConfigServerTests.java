package com.example.config.server;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {"spring.config.name=configserver"})
class ConfigServerTests {

    @Test
    void contextLoads() {
    }

}
