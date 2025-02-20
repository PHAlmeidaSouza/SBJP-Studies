package com.erudio.SBJP_Studies.integrationtests.testcontainers;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.utility.DockerImageName;

import java.util.Map;
import java.util.stream.Stream;

@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
public class AbstractIntegrationTest {

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        static PostgreSQLContainer<?> mypostgresql = new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"));

        private static void startContainers() {
            Startables.deepStart(Stream.of(mypostgresql)).join();
        }

        private static Map<String, Object> createConnectionConfiguration() {
            return Map.of(
                    "spring.datasource.url", mypostgresql.getJdbcUrl(),
                    "spring.datasource.username", mypostgresql.getUsername(),
                    "spring.datasource.password", mypostgresql.getPassword()
            );
        }

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            startContainers();
            ConfigurableEnvironment environment = applicationContext.getEnvironment();
            MapPropertySource testcontainer = new MapPropertySource("testcontainer",
                    createConnectionConfiguration());
            environment.getPropertySources().addFirst(testcontainer);
        }
    }
}
