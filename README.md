This project demonstrates an inconsistency in [Spring Cloud Config Server's health indicator](https://github.com/spring-cloud/spring-cloud-config/blob/v4.2.0/spring-cloud-config-server/src/main/java/org/springframework/cloud/config/server/config/ConfigServerHealthIndicator.java).

With the `native` profile active, the health status according to the indicator is `UP`, even though the `Environment` that is found is empty. When `spring.cloud.config.server.accept-empty` is `false`, an API request for the same `name` and `profiles` returns `404 Not Found`.
