package com.example.config.server;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.config.server.config.ConfigServerHealthIndicator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.assertj.MvcTestResult;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {
        "spring.config.name=configserver",
        "spring.cloud.config.server.health.repositories.test.name=myapp",
        "spring.cloud.config.server.health.repositories.test.profiles=prod",
        "spring.cloud.config.server.accept-empty=false"})
@AutoConfigureMockMvc
class ConfigServerHealthTests {

    @Autowired
    private MockMvcTester mvc;

    @Autowired
    private ConfigServerHealthIndicator healthIndicator;

    @Test
    void configServerHealthStatusShouldMatchEnvControllerResponse() {
        ConfigServerHealthIndicator.Repository healthTestRepo = healthIndicator.getRepositories().get("test");

        MvcTestResult envResult =
                mvc.get().uri("/{name}/{profiles}", healthTestRepo.getName(), healthTestRepo.getProfiles())
                        .accept(MediaType.APPLICATION_JSON)
                        .exchange();

        assertThat(envResult).hasStatus(HttpStatus.NOT_FOUND);

        Health health = healthIndicator.health();
        assertThat(health.getStatus()).as("config server health status").isEqualTo(Status.DOWN);
    }

}
